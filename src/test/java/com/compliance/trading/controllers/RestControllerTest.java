package com.compliance.trading.controllers;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.service.AccountService;
import com.compliance.trading.util.AccountBuilder;
import com.compliance.trading.util.AccountTransactionBuilder;
import com.compliance.trading.util.AccountType;
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

import static com.compliance.trading.util.Currency.SGD;
import static com.compliance.trading.util.DebitCredit.CREDIT;
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
        final Account account = AccountBuilder.anAccount()
                .withAccountName("test1")
                .withAccountNumber("11111")
                .withAccountType(AccountType.CURRENT)
                .withBalanceDate(new Date())
                .withCurrency(SGD)
                .withOpeningAvailableBalance(new BigDecimal("42342.99"))
                .withUserId(userId)
                .withId(accountId)
                .build();

        final AccountTransaction accountTransaction = AccountTransactionBuilder.anAccountTransaction()
                .withId(1L)
                .withAccount(account)
                .withValueDate(new Date())
                .withAmount(new BigDecimal("234234.93"))
                .withDebitCredit(CREDIT)
                .withTransactionNarrative("")
                .build();
        when(mockAccountService.getAccountTransactionsByAccountId(accountId)).thenReturn(Arrays.asList(accountTransaction));
        mockMvc.perform(get("/api/accounts/" + account.getId() + "/txns"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].account.id", is(Integer.valueOf(accountTransaction.getAccount().getId().intValue()))))
                .andExpect(jsonPath("$.[0].id", is(Integer.valueOf(1))));

        verify(mockAccountService, times(1)).getAccountTransactionsByAccountId(accountId);
    }

    @Test
    public void shouldReturnEmptyListWhenAccountHasNoTransactions() throws Exception {
        final Long accountId = 2345L;
        when(mockAccountService.getAccountTransactionsByAccountId(accountId)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/accounts/" + accountId + "/txns"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"))
                .andExpect(status().isOk());

        verify(mockAccountService, times(1)).getAccountTransactionsByAccountId(accountId);
    }


    @Test
    public void shouldGetAllAccountsForUserId() throws Exception {
        final Long accountId = 100L;
        final Long userId = 2345L;
        final Account account = AccountBuilder.anAccount()
                .withAccountName("test1")
                .withAccountNumber("11111")
                .withAccountType(AccountType.CURRENT)
                .withBalanceDate(new Date())
                .withCurrency(SGD)
                .withOpeningAvailableBalance(new BigDecimal("42342.99"))
                .withUserId(userId)
                .withId(accountId)
                .build();

        when(mockAccountService.getAccountByUserId(userId)).thenReturn(Arrays.asList(account));
        mockMvc.perform(get("/api/users/" + userId + "/accounts"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].accountName", containsString(account.getAccountName())))
                .andExpect(jsonPath("$.[0].accountType", containsString(account.getAccountType().name())))
                .andExpect(jsonPath("$.[0].currency", containsString(account.getCurrency().name())))
                .andExpect(jsonPath("$.[0].userId", is(Integer.valueOf(account.getUserId().intValue()))))
                .andExpect(jsonPath("$.[0].id", is(Integer.valueOf(account.getId().intValue()))));

        verify(mockAccountService, times(1)).getAccountByUserId(userId);
    }

    @Test
    public void shouldReturnEmptyListWhenUserIdHasNoAccounts() throws Exception {
        final Long userId = 2345L;
        when(mockAccountService.getAccountByUserId(userId)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/users/" + userId + "/accounts"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"))
                .andExpect(status().isOk());

        verify(mockAccountService, times(1)).getAccountByUserId(userId);
    }

}