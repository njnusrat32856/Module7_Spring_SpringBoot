package com.nusrat.PracticeBank.controller;

import com.nusrat.PracticeBank.entity.Account;
import com.nusrat.PracticeBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable long accountNumber) {
        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    @GetMapping("/all")
    public List<Account> getAllAccountDetails() {
        return  accountService.getAllAccountDetails();
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        Account account = accountService.depositAmount(accountNumber,amount);
        return account;
    }
}
