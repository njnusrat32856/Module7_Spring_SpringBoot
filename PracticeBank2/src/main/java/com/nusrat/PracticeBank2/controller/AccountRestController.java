package com.nusrat.PracticeBank2.controller;

import com.nusrat.PracticeBank2.entity.Account;
import com.nusrat.PracticeBank2.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    private AccountService accountService;

    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account a) {
        return accountService.createAccount(a);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

//    public ResponseEntity<Account> updateAccount( @RequestBody Account account) {
//        Account a = accountService.updateAccount(account );
//        return
//    }
    @PutMapping("/update/{id}")
    public void updateAccount(@PathVariable long id, @RequestBody Account account) {
        accountService.updateAccount(id,account);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);
    }

}
