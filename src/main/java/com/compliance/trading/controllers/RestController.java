package com.compliance.trading.controllers;


import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.repository.AccountRepository;
import com.compliance.trading.repository.AccountTransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final static Logger logger = LogManager.getLogger(RestController.class);
    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private RestController(AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @GetMapping(value = "/accounts")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping(value = "/{accountId}/txns")
    public List<AccountTransaction> getAccountTransaction(@PathVariable UUID accountId) {
        return accountTransactionRepository.findByAccountId(accountId);
    }

}