package com.nusrat.BankManagement.service;

import com.nusrat.BankManagement.entity.Loan;
import com.nusrat.BankManagement.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getById(long id) {
        return loanRepository.findById(id).get();
    }

    public List<Loan> getLoanByUserId(long userId) {
        return loanRepository.findByUserId(userId);
    }

    //    public Loan saveLoan(Loan loan) {
//        return loanRepository.save(loan);
//    }
    public void saveLoan(Loan loan) {
        // calculate the monthly payment using the formula: (P * r) / (1 - (1 + r)^-n)
        double principal = loan.getLoanAmount();
        double annualInterestRate = loan.getInterestRate() / 100;
        int numberOfMonths = loan.getDurationInMonths();

        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyPayment = (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));

        loan.setMonthlyPayment(monthlyPayment);
        loan.setBalanceRemaining(principal);

        loanRepository.save(loan);
    }

    //    public Loan updateLoan(Loan loan) {
//        Loan existingLoan = loanRepository.findById(loan.getId()).get();
//        existingLoan.setLoanAmount(loan.getLoanAmount());
//        existingLoan.setInterestRate(loan.getInterestRate());
//        existingLoan.setDurationInMonths(loan.getDurationInMonths());
//
//        return loanRepository.save(existingLoan);
//    }
    public void updateLoanPayment(Long loanId, double paymentAmount) {
        // Retrieve the loan details from the repository
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            throw new IllegalArgumentException("Loan not found");
        }

        Loan loan = loanOpt.get();

        // Check if the payment is more than the remaining balance
        if (paymentAmount > loan.getBalanceRemaining()) {
            throw new IllegalArgumentException("Payment amount exceeds remaining balance");
        }

        // Update the remaining balance
        double newBalance = loan.getBalanceRemaining() - paymentAmount;
        loan.setBalanceRemaining(newBalance);

        // Increment the number of payments made
        loan.setPaymentsMade(loan.getPaymentsMade() + 1);

        // If the balance reaches zero, mark the loan as fully paid
        if (newBalance <= 0) {
            loan.setStatus("PAID");
            loan.setBalanceRemaining(0); // Ensures balance doesn't go negative
        }

        // Save the updated loan details
        loanRepository.save(loan);
    }

    public void deleteByIdLoan(long id) {
        loanRepository.deleteById(id);
    }
}
