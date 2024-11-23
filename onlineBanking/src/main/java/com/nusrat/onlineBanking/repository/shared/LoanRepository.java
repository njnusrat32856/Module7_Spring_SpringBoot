package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.sharedEntities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

//    List<Loan> findAllByLoanDateBetween(Date start, Date end);

    @Query("SELECT l FROM Loan l WHERE l.customerId.id = :customerId")
    List<Loan> findByCustomer_Id(long customerId);
}
