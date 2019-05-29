package com.compliance.trading.controllers;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.service.AccountService;
import com.compliance.trading.util.AccountType;
import com.compliance.trading.util.Currency;
import com.compliance.trading.util.DebitCredit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService mockAccountService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldGetTransactionsByAccountId() throws Exception {
        Account account = new Account("11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"));
        AccountTransaction accountTransaction = new AccountTransaction(account, new Date(2019, 2, 10),
                new BigDecimal("234234.93"), DebitCredit.CREDIT, "");
        UUID accountId = any(UUID.class);
        Pageable pageable = any(Pageable.class);
        when(mockAccountService.getAccountTransactionsByAccountId(accountId, pageable)).thenReturn(Arrays.asList(accountTransaction));
        mockMvc.perform(get("/api/"+account.getId()+"/txns"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].account.id", containsString(accountTransaction.getAccount().getId().toString())))
                .andExpect(jsonPath("$.[0].id", containsString(accountTransaction.getId().toString())));

        verify(mockAccountService, times(1)).getAccountTransactionsByAccountId(eq(accountId), eq(pageable));
    }


    @Test
    public void shouldGetAllAccounts() throws Exception {
        Account account1 = new Account("11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"));
        Sort sort = Sort.by("accountName").ascending();
        when(mockAccountService.getAccounts(sort)).thenReturn(Arrays.asList(account1));
        mockMvc.perform(get("/api/accounts"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].accountName", containsString(account1.getAccountName())))
                .andExpect(jsonPath("$.[0].accountType", containsString(account1.getAccountType().name())))
                .andExpect(jsonPath("$.[0].currency", containsString(account1.getCurrency().name())))
                .andExpect(jsonPath("$.[0].id", containsString(account1.getId().toString())));

        verify(mockAccountService, times(1)).getAccounts(sort);
    }
}