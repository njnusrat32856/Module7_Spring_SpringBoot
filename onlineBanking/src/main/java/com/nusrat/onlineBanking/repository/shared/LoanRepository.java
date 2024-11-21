package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.sharedEntities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByLoanDateBetween(Date start, Date end);

    List<Loan> findByCutomerId(Long cutomerId);
}
