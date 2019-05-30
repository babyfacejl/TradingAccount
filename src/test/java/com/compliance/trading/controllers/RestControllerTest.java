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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
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
        final Long accountId = 1L;
        final Long userId = 1L;
        final Account account = new Account(accountId,"11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"), userId);
        final AccountTransaction accountTransaction = new AccountTransaction(1L, account, new Date(),
                new BigDecimal("234234.93"), DebitCredit.CREDIT, "");
        when(mockAccountService.getAccountTransactionsByAccountId(accountId)).thenReturn(Arrays.asList(accountTransaction));
        mockMvc.perform(get("/api/"+account.getId()+"/txns"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].account.id", is(Integer.valueOf(accountTransaction.getAccount().getId().intValue()))))
                .andExpect(jsonPath("$.[0].id", is(Integer.valueOf(1))));

        verify(mockAccountService, times(1)).getAccountTransactionsByAccountId(accountId);
    }


    @Test
    public void shouldGetAllAccountsForUserId() throws Exception {
        final Long accountId = 1L;
        final Long userId = 1L;
        final Account account1 = new Account(accountId,"11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"), userId);
        when(mockAccountService.getAccountByUserId(accountId)).thenReturn(Arrays.asList(account1));
        mockMvc.perform(get("/api/1/accounts"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].accountName", containsString(account1.getAccountName())))
                .andExpect(jsonPath("$.[0].accountType", containsString(account1.getAccountType().name())))
                .andExpect(jsonPath("$.[0].currency", containsString(account1.getCurrency().name())))
                .andExpect(jsonPath("$.[0].userId", is(Integer.valueOf(account1.getUserId().intValue()))))
                .andExpect(jsonPath("$.[0].id", is(Integer.valueOf(account1.getId().intValue()))));

        verify(mockAccountService, times(1)).getAccountByUserId(userId);
    }
}