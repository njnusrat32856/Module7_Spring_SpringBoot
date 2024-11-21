package com.nusrat.onlineBanking.service.sharedService;

import com.nusrat.onlineBanking.entities.sharedEntities.Loan;
import com.nusrat.onlineBanking.repository.shared.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(long id) {
        return loanRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No loan found with id: " + id));
    }

    public List<Loan> getLoanByCustomerId(long customerId) {
        return loanRepository.findByCutomerId(customerId);
    }

    public Loan applyForLoan(Loan loan) {
        loan.getStatus("Pending");
        return loanRepository.save(loan);
    }
}
