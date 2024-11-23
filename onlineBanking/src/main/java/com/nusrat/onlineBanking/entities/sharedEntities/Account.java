package com.nusrat.onlineBanking.entities.sharedEntities;

import com.nusrat.onlineBanking.entities.customerPart.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType; // E.g., "Savings", "Checking"

    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Employee managedBy; // Employee managing this account

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

}
