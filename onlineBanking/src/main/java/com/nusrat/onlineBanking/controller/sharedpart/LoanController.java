package com.nusrat.onlineBanking.controller.sharedpart;

import com.nusrat.onlineBanking.entities.Employee;
import com.nusrat.onlineBanking.entities.sharedEntities.Loan;
import com.nusrat.onlineBanking.service.sharedService.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping("/applyloan")
    public Loan applyForLoan(@RequestBody Loan loan) {
        return loanService.applyForLoan(loan);
    }

    @PutMapping("/{id}/approve")
    public Loan approveLoan(
            @PathVariable Long id,
            @RequestBody Employee approvedBy) {
        return loanService.approveLoan(id, approvedBy);
    }

    @PutMapping("/{id}/reject")
    public Loan rejectLoan(@PathVariable Long id) {
        return loanService.rejectLoan(id);
    }
}
