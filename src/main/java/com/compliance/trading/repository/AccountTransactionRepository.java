package com.compliance.trading.repository;

import com.compliance.trading.models.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
    public List<AccountTransaction> findByAccountId(UUID accountId);
}
