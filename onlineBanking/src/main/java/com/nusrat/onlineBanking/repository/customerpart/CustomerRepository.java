package com.nusrat.onlineBanking.repository.customerpart;

import com.nusrat.onlineBanking.entities.customerPart.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByCustomerId(Long customerId);
}
