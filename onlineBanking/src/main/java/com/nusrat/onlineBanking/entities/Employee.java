package com.nusrat.onlineBanking.entities;

import com.nusrat.onlineBanking.entities.sharedEntities.Branch;
import com.nusrat.onlineBanking.entities.sharedEntities.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // E.g., "Manager", "Support", "Loan Officer"

    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch; // Relationship with Branch entity

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department; // Relationship with Department entity

    @Column(nullable = false)
    private boolean active; // Indicates if the employee is active
}
