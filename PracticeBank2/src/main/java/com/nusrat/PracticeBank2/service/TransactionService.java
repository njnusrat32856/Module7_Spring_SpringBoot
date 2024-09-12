package com.nusrat.PracticeBank2.service;

import com.nusrat.PracticeBank2.entity.Transaction;
import com.nusrat.PracticeBank2.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
