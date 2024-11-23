package com.nusrat.onlineBanking.entities.sharedEntities;

import com.nusrat.onlineBanking.entities.Employee;
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
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean isRead; // Indicates if the notification has been read

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
