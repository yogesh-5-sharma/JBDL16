package com.example.transactionservice.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String transactionId;

    @NotNull
    private String payerId;

    @NotNull
    private String payeeId;

    @Min(1)
    private int amount;

    private String purpose;

    @CreationTimestamp
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
