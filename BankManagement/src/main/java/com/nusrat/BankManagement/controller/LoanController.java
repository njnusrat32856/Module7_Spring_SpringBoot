package com.nusrat.BankManagement.controller;

import com.nusrat.BankManagement.entity.Loan;
import com.nusrat.BankManagement.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/save")
    public void saveLoan(@RequestBody Loan loan) {
        loanService.saveLoan(loan);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLoan(@PathVariable long id) {
        loanService.deleteByIdLoan(id);
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable long id) {
        return loanService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Loan> getLoanByUserId(@PathVariable long userId) {
        return loanService.getLoanByUserId(userId);
    }

    @PutMapping("/{loanId}/payment")
    public ResponseEntity<String> makeLoanPayment(@PathVariable Long loanId, @RequestParam double paymentAmount) {
        try {
            loanService.updateLoanPayment(loanId, paymentAmount);
            return ResponseEntity.ok("Payment successful and loan updated.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
