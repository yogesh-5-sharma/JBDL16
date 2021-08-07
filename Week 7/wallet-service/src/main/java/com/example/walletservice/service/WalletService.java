package com.example.walletservice.service;

import com.example.walletservice.models.Wallet;
import com.example.walletservice.repository.WalletCacheRepository;
import com.example.walletservice.repository.WalletRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private static final String TOPIC_CREATE_USER = "CREATE_USER";

    private static final String TOPIC_TRANSACTION_INITIATED = "TRANSACTION_INITIATED";

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletCacheRepository walletCacheRepository;

    @Autowired
    ObjectMapper objectMapper;

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
