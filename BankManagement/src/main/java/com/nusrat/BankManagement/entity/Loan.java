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
    private int id;

    private String loanType;

    private Double loanAmount;

    private float interestRate;

    private Double monthlyPayment;

    private Double durationInMonths;

    private Date startDate;

    private Date endDate;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Loan loan;
}
