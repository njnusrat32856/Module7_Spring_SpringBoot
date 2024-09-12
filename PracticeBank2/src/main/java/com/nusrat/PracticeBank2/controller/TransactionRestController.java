package com.nusrat.PracticeBank2.controller;

import com.nusrat.PracticeBank2.entity.Transaction;
import com.nusrat.PracticeBank2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionRestController {

    @Autowired
    private TransactionService transactionService;

    public List<Transaction> getTransactionByAccountId(long accountId) {
        return transactionService.getTransactionByAccountId(accountId);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }
}
