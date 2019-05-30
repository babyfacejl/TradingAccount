package com.compliance.trading.controllers;


import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestController.class);
    private final AccountService accountService;

    @Autowired
    private RestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/users/{userId}/accounts")
    public List<Account> getAccounts(@PathVariable Long userId) {
        return accountService.getAccountByUserId(userId);
    }

    @GetMapping(value = "/accounts/{accountId}/txns")
    public List<AccountTransaction> getAccountTransaction(@PathVariable Long accountId) {
        return accountService.getAccountTransactionsByAccountId(accountId);
    }

}