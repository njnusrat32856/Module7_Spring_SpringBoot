package com.nusrat.PracticeBank2.service;

import com.nusrat.PracticeBank2.entity.Account;
import com.nusrat.PracticeBank2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account a) {
        return accountRepository.save(a);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account findById(long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void updateAccount(long id, Account a) {
        accountRepository.save(a);
    }

    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }


}
