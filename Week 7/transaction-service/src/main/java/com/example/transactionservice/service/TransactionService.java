package com.example.transactionservice.service;

import com.example.transactionservice.models.Transaction;
import com.example.transactionservice.models.TransactionStatus;
import com.example.transactionservice.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    private static final String TOPIC_TRANSACTION_INITIATED = "TRANSACTION_INITIATED";

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


        kafkaTemplate.send(TOPIC_TRANSACTION_INITIATED,
                newTransaction.getTransactionId(),
                objectMapper.writeValueAsString(walletUpdateRequest));
    }
}
