package com.nusrat.BankManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String loanType;

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private float interestRate;

    @Column(nullable = false)
    private Double monthlyPayment;

    @Column(nullable = false)
    private int durationInMonths;

    @Column(nullable = false)
    private double balanceRemaining;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String status;

    private double paymentsMade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
}
