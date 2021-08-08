package com.example.walletservice.service;

import com.example.walletservice.models.Wallet;
import com.example.walletservice.repository.WalletCacheRepository;
import com.example.walletservice.repository.WalletRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class WalletService {

    private static final String TOPIC_CREATE_USER = "CREATE_USER";

    private static final String TOPIC_TRANSACTION_INITIATED = "TRANSACTION_INITIATED";

    private static final String TOPIC_WALLET_UPDATED = "WALLET_UPDATED";

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletCacheRepository walletCacheRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = TOPIC_CREATE_USER, groupId = "wallet_group")
    public void createWallet(String message) throws JsonProcessingException {
        JSONObject walletCreateRequest = objectMapper.readValue(message, JSONObject.class);

        String userId = walletCreateRequest.get("userId").toString();
        int balance = (Integer) walletCreateRequest.get("balance");

        Wallet newWallet = Wallet.builder()
                .balance(balance)
                .userId(userId)
                .build();

        walletRepository.save(newWallet);
        walletCacheRepository.saveWalletByUserid(newWallet);
    }

    @KafkaListener(topics = TOPIC_TRANSACTION_INITIATED, groupId = "wallet_group")
    public void updateWalletForTransaction(String message) throws JsonProcessingException {
        JSONObject walletUpdateRequest = objectMapper.readValue(message, JSONObject.class);

        // get payer wallet and payee wallet and check whether they exist
        // payer should have balance>=amount
        // update both wallets
        // push a message to topic that is listened by TransactionService

        String payerId = walletUpdateRequest.get("payerId").toString();
        String payeeId = walletUpdateRequest.get("payeeId").toString();
        int amount = (Integer) walletUpdateRequest.get("amount");
        String transactionId = walletUpdateRequest.get("transactionId").toString();

        Wallet payerWallet = walletCacheRepository.getWalletByUserId(payerId);
        if(payerWallet == null) {
            Optional<Wallet> payerWalletOptional = walletRepository.findByUserId(payerId);
            if (payerWalletOptional.isPresent()) {
                payerWallet = payerWalletOptional.get();
                walletCacheRepository.saveWalletByUserid(payerWallet);
            }
        }

        Wallet payeeWallet = walletCacheRepository.getWalletByUserId(payeeId);
        if(payeeWallet == null) {
            Optional<Wallet> payeeWalletOptional = walletRepository.findByUserId(payeeId);
            if (payeeWalletOptional.isPresent()) {
                payeeWallet = payeeWalletOptional.get();
                walletCacheRepository.saveWalletByUserid(payeeWallet);
            }
        }

        JSONObject transactionCompleteRequest = new JSONObject();
        transactionCompleteRequest.put("transactionId", transactionId);

        if(payerWallet == null || payeeWallet == null || payerWallet.getBalance() < amount) {
            transactionCompleteRequest.put("transactionStatus", "FAILED");
        } else {
            payerWallet.setBalance(payerWallet.getBalance() - amount);
            payeeWallet.setBalance(payeeWallet.getBalance() + amount);

            walletRepository.saveAll(Arrays.asList(payerWallet, payeeWallet));
            walletCacheRepository.saveWalletByUserid(payerWallet);
            walletCacheRepository.saveWalletByUserid(payeeWallet);

            transactionCompleteRequest.put("transactionStatus", "SUCCESS");
        }

        kafkaTemplate.send(TOPIC_WALLET_UPDATED,
                transactionId,
                objectMapper.writeValueAsString(transactionCompleteRequest));
    }

    public Wallet getWalletByUserid(String userId) {
        Wallet wallet = walletCacheRepository.getWalletByUserId(userId);

        if(wallet == null) {
            wallet = walletRepository.findByUserId(userId)
                    .orElseThrow();
            walletCacheRepository.saveWalletByUserid(wallet);
        }

        return wallet;
    }
}
