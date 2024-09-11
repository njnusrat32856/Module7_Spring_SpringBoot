package com.nusrat.PracticeBank.service;

import com.nusrat.PracticeBank.entity.Account;
import com.nusrat.PracticeBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        Account accountSave = accountRepository.save(account);
        return accountSave;
    }

    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        Account accountFound = account.get();
        return accountFound;
    }

    public List<Account> getAllAccountDetails() {


        return accountRepository.findAll();
    }

    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> account = accountRepository.findById(accountNumber);

        if (account.isEmpty()) {
            throw new RuntimeException("Account not present");
        }
        Account accountPresent = account.get();
        Double totalBalance = accountPresent.getAccountBalance()+amount;
        accountPresent.setAccountBalance(totalBalance);

        accountRepository.save(accountPresent);

        return accountPresent;
    }

    public Account withdrawAmount(Long accountNumber, Double amount) {
        return null;
    }

    public void closeAccount(Long accountNumber) {

    }
}
