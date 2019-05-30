package com.compliance.trading.service;

import com.compliance.trading.repository.AccountRepository;
import com.compliance.trading.repository.AccountTransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountTransactionRepository accountTransactionRepository;
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountService(accountRepository, accountTransactionRepository);
    }

    @Test
    public void getAccounts() {
        accountService.getAccountByUserId(1L);
        verify(accountRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void getAccountTransactionsByAccountId() {
        accountService.getAccountTransactionsByAccountId(1L);
        verify(accountTransactionRepository, times(1)).findByAccountId(1L);
    }
}