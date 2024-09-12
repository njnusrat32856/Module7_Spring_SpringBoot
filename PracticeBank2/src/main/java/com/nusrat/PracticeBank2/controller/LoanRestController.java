package com.nusrat.PracticeBank2.controller;

import com.nusrat.PracticeBank2.entity.Loan;
import com.nusrat.PracticeBank2.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/{userid}")
    public List<Loan> getLoansBYUserId(int userId) {
        return loanService.getLoanByUserId(userId);
    }

    public Loan createLoan(Loan loan) {
        return loanService.createLoan(loan);
    }
}
