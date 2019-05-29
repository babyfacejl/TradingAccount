package com.compliance.trading.service;

import com.compliance.trading.repository.AccountRepository;
import com.compliance.trading.repository.AccountTransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

import static org.mockito.Mockito.*;

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
        Sort sort = mock(Sort.class);
        accountService.getAccounts(sort);
        verify(accountRepository, times(1)).findAll(sort);
    }

    @Test
    public void getAccountTransactionsByAccountId() {
        UUID accountId = UUID.randomUUID();
        Pageable pageable = mock(Pageable.class);
        accountService.getAccountTransactionsByAccountId(accountId, pageable);
        verify(accountTransactionRepository, times(1)).findByAccountId(accountId, pageable);
    }
}