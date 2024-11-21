package com.nusrat.onlineBanking.service.sharedService;

import com.nusrat.onlineBanking.entities.Employee;
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
        loan.setStatus("Pending");
        return loanRepository.save(loan);
    }

    public Loan approveLoan(Long loanId, Employee approvedBy) {
        Loan loan = getLoanById(loanId);
        loan.setStatus("Approved");
        loan.setApprovedBy(approvedBy);
        return loanRepository.save(loan);
    }

    public Loan rejectLoan(Long loanId) {
        Loan loan = getLoanById(loanId);
        loan.setStatus("Rejected");
        return loanRepository.save(loan);
    }

}
