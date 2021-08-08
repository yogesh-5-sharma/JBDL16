package com.example.transactionservice.repository;

import com.example.transactionservice.models.Transaction;
import com.example.transactionservice.models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.transactionStatus = :transactionStatus WHERE t.transactionId = :transactionId")
    void updateTransactionStatus(String transactionId, TransactionStatus transactionStatus);

    Optional<Transaction> findByTransactionId(String transactionId);
}
