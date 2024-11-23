package com.nusrat.onlineBanking.entities.sharedEntities;

import com.nusrat.onlineBanking.entities.Employee;
import com.nusrat.onlineBanking.entities.adminPart.Admin;
import com.nusrat.onlineBanking.entities.customerPart.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String transactionType; // E.g., "Deposit", "Withdrawal", "Transfer"

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account; // Relationship with Account entity

//    @ManyToOne
//    private Employee initiatedBy; // Relationship with Employee entity

//    @ManyToOne
//    private Admin approvedBy; // Relationship with Admin entity (if required)

    @Column(nullable = false)
    private String status; // E.g., "Completed", "Failed", "Pending"

    @Column(nullable = false)
    private LocalDateTime transactionDate;

//    @ManyToOne
//    private Customer customer;
}
