package com.compliance.trading.service;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.repository.AccountRepository;
import com.compliance.trading.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * Return list of accounts for user id
     * @param userId
     * @return
     */
    public List<Account> getAccountByUserId(Long userId) {
        return this.accountRepository.findByUserId(userId);
    }

    /**
     * Return list of account transactions for account id
     * @param accountId
     * @return
     */
    public List<AccountTransaction> getAccountTransactionsByAccountId(Long accountId) {
        return this.accountTransactionRepository.findByAccountId(accountId);
    }
}
