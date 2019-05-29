package com.compliance.trading.repository;

import com.compliance.trading.models.Account;
import com.compliance.trading.util.AccountType;
import com.compliance.trading.util.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldCreateAccount() {
        Account account1 = new Account("11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"));
        Account account2 = new Account("11111", "abc", AccountType.SAVINGS,
                new Date(), Currency.AUD, new BigDecimal("3432.99"));

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.flush();

        List<Account> all = accountRepository.findAll(Sort.by("accountName").ascending());
        assertThat(all.size(), is(2));
        Account dbAccount = all.get(0);
        assertThat(dbAccount.getId(), is(account2.getId()));
        assertThat(dbAccount.getAccountName(), is(account2.getAccountName()));
        assertThat(dbAccount.getAccountNumber(), is(account2.getAccountNumber()));
        assertThat(dbAccount.getAccountType(), is(account2.getAccountType()));
        assertThat(dbAccount.getCurrency(), is(account2.getCurrency()));
        assertThat(dbAccount.getOpeningBalance().compareTo(account2.getOpeningBalance()), is(0));

        dbAccount = all.get(1);
        assertThat(dbAccount.getId(), is(account1.getId()));
        assertThat(dbAccount.getAccountName(), is(account1.getAccountName()));
        assertThat(dbAccount.getAccountNumber(), is(account1.getAccountNumber()));
        assertThat(dbAccount.getAccountType(), is(account1.getAccountType()));
        assertThat(dbAccount.getCurrency(), is(account1.getCurrency()));
        assertThat(dbAccount.getOpeningBalance().compareTo(account1.getOpeningBalance()), is(0));
    }
}