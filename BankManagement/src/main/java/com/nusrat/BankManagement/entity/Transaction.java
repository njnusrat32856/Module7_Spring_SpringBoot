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
    private int id;

    private LocalDateTime transationDate;

    private double amount;

    private String transactionType;

    private String description;

    private double targetAccountNumber;

    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private Account account;
}
