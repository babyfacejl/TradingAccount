package com.compliance.trading.service;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.repository.AccountRepository;
import com.compliance.trading.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    /**
     * Sort by Account name with sorting order
     * @param sort
     * @return
     */
    public List<Account> getAccounts(Sort sort) {
        return this.accountRepository.findAll(sort);
    }

    /**
     * Return list of account transaction for account id Sort by value date in ascending order by default with max results
     * @param accountId
     * @param pageable
     * @return
     */
    public List<AccountTransaction> getAccountTransactionsByAccountId(UUID accountId, Pageable pageable) {
        return this.accountTransactionRepository.findByAccountId(accountId, pageable);
    }
}
