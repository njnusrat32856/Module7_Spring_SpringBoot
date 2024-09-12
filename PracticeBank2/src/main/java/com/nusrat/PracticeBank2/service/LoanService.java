package com.nusrat.PracticeBank2.service;

import com.nusrat.PracticeBank2.entity.Loan;
import com.nusrat.PracticeBank2.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private LoanRepository loanRepository;

    public List<Loan> getLoanByUserId(long userId) {
        return loanRepository.findByUserId(userId);
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

}
