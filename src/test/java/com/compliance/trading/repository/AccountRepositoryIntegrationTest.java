package com.compliance.trading.repository;

import com.compliance.trading.models.Account;
import com.compliance.trading.util.AccountType;
import com.compliance.trading.util.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldCreateAccount() {
        Account account = new Account("11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"));

        accountRepository.save(account);
        accountRepository.flush();

        List<Account> all = accountRepository.findAll();
        assertThat(all.size(), is(1));
        Account dbAccount = all.get(0);
        assertThat(dbAccount.getId(), is(account.getId()));
        assertThat(dbAccount.getAccountName(), is(account.getAccountName()));
        assertThat(dbAccount.getAccountNumber(), is(account.getAccountNumber()));
        assertThat(dbAccount.getAccountType(), is(account.getAccountType()));
        assertThat(dbAccount.getCurrency(), is(account.getCurrency()));
        assertThat(dbAccount.getOpeningBalance().compareTo(account.getOpeningBalance()), is(0));
    }
}