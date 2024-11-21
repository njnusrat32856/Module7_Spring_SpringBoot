package com.nusrat.onlineBanking.entities.sharedEntities;

import com.nusrat.onlineBanking.entities.Employee;
import com.nusrat.onlineBanking.entities.customerPart.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double loanAmount;

    @Column(nullable = false)
    private double interestRate;

    @Column(nullable = false)
    private int durationInMonths;

    @Column(nullable = false)
    private double balanceRemaining;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Employee approvedBy; // Employee approving the loan

    @Column(nullable = false)
    private String status; // E.g., "Approved", "Pending", "Rejected"
}
