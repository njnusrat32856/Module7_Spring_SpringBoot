package com.nusrat.onlineBanking.service.sharedService;

import com.nusrat.onlineBanking.entities.sharedEntities.Account;
import com.nusrat.onlineBanking.entities.sharedEntities.Loan;
import com.nusrat.onlineBanking.entities.sharedEntities.Transaction;
import com.nusrat.onlineBanking.repository.shared.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction createTransaction(Transaction transaction) {
        Account account = accountService.getAccountById(transaction.getAccount().getId());

        if ("Withdrawal".equalsIgnoreCase(transaction.getTransactionType()) && account.getBalance() < transaction.getAmount()) {
            throw new RuntimeException("Insufficient funds");
        }

        if ("Withdrawal".equalsIgnoreCase(transaction.getTransactionType())) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if ("Deposit".equalsIgnoreCase(transaction.getTransactionType())) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }

        accountService.updateAccountBalance(account.getId(), account.getBalance());
        return transactionRepository.save(transaction);
    }

}
