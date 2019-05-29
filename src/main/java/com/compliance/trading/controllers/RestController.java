package com.compliance.trading.controllers;


import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestController.class);
    private final AccountService accountService;
    @Value("${account.transaction.max.result:100}")
    private int maxResult;

    @Autowired
    private RestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts(Sort.by("accountName").ascending());
    }

    @GetMapping(value = "/{accountIdUUID}/txns")
    public List<AccountTransaction> getAccountTransaction(@PathVariable String accountIdUUID) {
        LOGGER.debug("Get account transaction for accountid: "+ accountIdUUID + " maxResult:" + maxResult);
        Pageable pageable = PageRequest.of(0, maxResult, Sort.by("valueDate").ascending());
        return accountService.getAccountTransactionsByAccountId(UUID.fromString(accountIdUUID), pageable);
    }

}