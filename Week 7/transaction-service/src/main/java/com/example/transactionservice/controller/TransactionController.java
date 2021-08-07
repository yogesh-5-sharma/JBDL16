package com.example.transactionservice.controller;

import com.example.transactionservice.models.Transaction;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transfer")
    public void createNewTransaction(@RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
    }
}
