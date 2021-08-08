package com.example.transactionservice.service;

import com.example.transactionservice.models.Transaction;
import com.example.transactionservice.models.TransactionStatus;
import com.example.transactionservice.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    private static final String TOPIC_TRANSACTION_INITIATED = "TRANSACTION_INITIATED";

    private static final String TOPIC_WALLET_UPDATED = "WALLET_UPDATED";

    private static final String TOPIC_TRANSACTION_COMPLETED = "TRANSACTION_COMPLETED";

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void createTransaction(Transaction transaction) throws JsonProcessingException {

        Transaction newTransaction = Transaction.builder()
                .payerId(transaction.getPayerId())
                .payeeId(transaction.getPayeeId())
                .amount(transaction.getAmount())
                .purpose(transaction.getPurpose())
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        newTransaction = transactionRepository.save(newTransaction);

        JSONObject walletUpdateRequest = new JSONObject();
        walletUpdateRequest.put("payerId", newTransaction.getPayerId());
        walletUpdateRequest.put("payeeId", newTransaction.getPayeeId());
        walletUpdateRequest.put("amount", newTransaction.getAmount());
        walletUpdateRequest.put("transactionId", newTransaction.getTransactionId());


        kafkaTemplate.send(TOPIC_TRANSACTION_INITIATED,
                newTransaction.getTransactionId(),
                objectMapper.writeValueAsString(walletUpdateRequest));
    }

    @KafkaListener(topics = TOPIC_WALLET_UPDATED, groupId = "transaction_group")
    public void transactionCompleted(String message) throws JsonProcessingException {
        JSONObject transactionCompleteRequest = objectMapper.readValue(message, JSONObject.class);

        String transactionId = transactionCompleteRequest.get("transactionId").toString();
        String transactionStatus = transactionCompleteRequest.get("transactionStatus").toString();

        // 1. update transaction with given transactionStatus
        // 2. send a message to Notification Service

        transactionRepository.updateTransactionStatus(transactionId, TransactionStatus.valueOf(transactionStatus));


        // 2.

        Transaction updatedTransaction = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow();

        String payerId = updatedTransaction.getPayerId();
        String payeeId = updatedTransaction.getPayeeId();
        int amount = updatedTransaction.getAmount();

        JSONObject payerNotificationRequest = new JSONObject();
        payerNotificationRequest.put("userId", payerId);
        payerNotificationRequest.put("emailMessage",
                String.format("Hey %s, your transfer of amount %d with transaction id %s is %s",
                        payerId,
                        amount,
                        transactionId,
                        transactionStatus));

        kafkaTemplate.send(TOPIC_TRANSACTION_COMPLETED, objectMapper.writeValueAsString(payerNotificationRequest));

        if (transactionStatus.equals("SUCCESS")) {
            JSONObject payeeNotficationRequest = new JSONObject();
            payeeNotficationRequest.put("userId", payeeId);
            payeeNotficationRequest.put("emailMessage",
                    String.format("Hey %s, you received an amount of %d with transaction id %s",
                            payeeId,
                            amount,
                            transactionId));

            kafkaTemplate.send(TOPIC_TRANSACTION_COMPLETED, objectMapper.writeValueAsString(payeeNotficationRequest));
        }
    }
}
