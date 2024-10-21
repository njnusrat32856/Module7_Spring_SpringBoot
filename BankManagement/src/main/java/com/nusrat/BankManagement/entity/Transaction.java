package com.nusrat.BankManagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    private double amount;
    private double balance;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;


    private double targetAccountNumber;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private Account account;




    public enum TransactionType {

        DEPOSIT,
        WITHDRAW,
        FUND_TRANSFER
//    TRANSFER
    }
}
