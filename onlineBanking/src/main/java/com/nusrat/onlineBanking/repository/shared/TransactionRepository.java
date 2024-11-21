package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.sharedEntities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(long accountId);

    List<Transaction> findByTransactionType(String transactionType);
}
