package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.sharedEntities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(Long customerId);

    List<Account> findBYBranchId(Long branchId);

    List<Account> findByAccountType(String accountType);

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId AND a.balance > :minBalance")
    List<Account> findHighValueAccounts(@Param("customerId") Long customerId, @Param("minBalance") double minBalance);
}
