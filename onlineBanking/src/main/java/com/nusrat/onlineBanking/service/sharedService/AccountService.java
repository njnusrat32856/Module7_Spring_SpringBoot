package com.nusrat.onlineBanking.service.sharedService;

import com.nusrat.onlineBanking.entities.sharedEntities.Account;
import com.nusrat.onlineBanking.repository.shared.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(long id) {
        return accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAccountByCustomerId(long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccountBalance(Long accountId, double newBalance) {
        Account account = getAccountById(accountId);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }

}
