package com.nusrat.onlineBanking.entities.customerPart;

import com.nusrat.onlineBanking.entities.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SupportTickets")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String issue;

    @Column(nullable = false)
    private String status; // E.g., "Open", "In Progress", "Closed"

    @Column(nullable = false)
    private LocalDateTime createdAt;

//    @Column(nullable = false)
//    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne
    private Employee assignedTo; // Employee handling the ticket
}
