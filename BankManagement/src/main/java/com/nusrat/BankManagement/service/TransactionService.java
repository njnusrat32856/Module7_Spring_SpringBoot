package com.nusrat.BankManagement.service;

import com.nusrat.BankManagement.entity.Transaction;
import com.nusrat.BankManagement.entity.User;
import com.nusrat.BankManagement.repository.TransactionRepository;
import com.nusrat.BankManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository; // Assuming you have a user repository for account balance checks

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(long id) {
        return transactionRepository.findById(id).get();
    }

    public List<Transaction> getTransactionByUserId(long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Transaction deposit(String accountNumber, double amount, String description) {
        // Update the account balance in the user repository
        User user = (User) userRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("User not found"));

        // Update user's account balance
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);  // Save updated user balance

        // Create a new transaction and save it in the transaction repository
//        Transaction transaction = new Transaction();
//        transaction.setAccountNumber(accountNumber);
//        transaction.setAmount(amount);
//        transaction.setDescription(description);
//        transaction.setTransactionType("Deposit");
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setTransactionType(Transaction.TransactionType.DEPOSIT);
        transaction.setDescription(description);
//        transaction.setTargetAccountNumber(user.getAccountNumber());  // Assuming User has an accountNumber
        transaction.setStatus("PENDING");  // Assuming deposit is always successful
//        transaction.setUser(user);
        transaction.setBalance(user.getBalance());  // Set updated balance in the transaction

        return transactionRepository.save(transaction);


//        return transactionRepository.save(transaction);
    }
}
