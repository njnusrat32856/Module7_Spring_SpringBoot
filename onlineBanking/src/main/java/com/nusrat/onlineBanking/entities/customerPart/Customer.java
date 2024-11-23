package com.nusrat.onlineBanking.entities.customerPart;

import com.nusrat.onlineBanking.entities.sharedEntities.Account;
import com.nusrat.onlineBanking.entities.sharedEntities.Loan;
import com.nusrat.onlineBanking.entities.sharedEntities.Notification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "customerId")
    private List<Account> accounts;

    @OneToMany(mappedBy = "customerId")
    private List<Loan> loans;

    @OneToMany(mappedBy = "customer")
    private List<Notification> notifications;

}
