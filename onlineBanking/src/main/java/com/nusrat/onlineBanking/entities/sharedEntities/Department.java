package com.nusrat.onlineBanking.entities.sharedEntities;

import com.nusrat.onlineBanking.entities.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String departmentName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch; // Relationship with Branch entity

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
