package com.compliance.trading.repository;

import com.compliance.trading.models.Account;
import com.compliance.trading.util.AccountBuilder;
import com.compliance.trading.util.AccountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.compliance.trading.util.Currency.AUD;
import static com.compliance.trading.util.Currency.SGD;
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
    public void shouldFindAccountByUserId() {

        final Account account1 = AccountBuilder.anAccount()
                .withAccountName("test1")
                .withAccountNumber("11111")
                .withAccountType(AccountType.CURRENT)
                .withBalanceDate(new Date())
                .withCurrency(SGD)
                .withOpeningAvailableBalance(new BigDecimal("42342.99"))
                .withUserId(1L)
                .withId(1L)
                .build();
        final Account account2 = AccountBuilder.anAccount()
                .withAccountName("abc")
                .withAccountNumber("22222")
                .withAccountType(AccountType.SAVINGS)
                .withBalanceDate(new Date())
                .withCurrency(AUD)
                .withOpeningAvailableBalance(new BigDecimal("3432.99"))
                .withUserId(1L)
                .withId(2L)
                .build();
        final Account account3 = AccountBuilder.anAccount()
                .withAccountName("charlie")
                .withAccountNumber("33333")
                .withAccountType(AccountType.SAVINGS)
                .withBalanceDate(new Date())
                .withCurrency(AUD)
                .withOpeningAvailableBalance(new BigDecimal("267982.99"))
                .withUserId(2L)
                .withId(3L)
                .build();

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.flush();

        final List<Account> all = accountRepository.findByUserId(1L);
        assertThat(all.size(), is(2));
        Account dbAccount = all.get(0);
        assertThat(dbAccount.getId(), is(account1.getId()));
        assertThat(dbAccount.getAccountName(), is(account1.getAccountName()));
        assertThat(dbAccount.getAccountNumber(), is(account1.getAccountNumber()));
        assertThat(dbAccount.getAccountType(), is(account1.getAccountType()));
        assertThat(dbAccount.getCurrency(), is(account1.getCurrency()));
        assertThat(dbAccount.getUserId(), is(account2.getUserId()));
        assertThat(dbAccount.getOpeningAvailableBalance().compareTo(account1.getOpeningAvailableBalance()), is(0));

        dbAccount = all.get(1);
        assertThat(dbAccount.getId(), is(account2.getId()));
        assertThat(dbAccount.getAccountName(), is(account2.getAccountName()));
        assertThat(dbAccount.getAccountNumber(), is(account2.getAccountNumber()));
        assertThat(dbAccount.getAccountType(), is(account2.getAccountType()));
        assertThat(dbAccount.getCurrency(), is(account2.getCurrency()));
        assertThat(dbAccount.getUserId(), is(account2.getUserId()));
        assertThat(dbAccount.getOpeningAvailableBalance().compareTo(account2.getOpeningAvailableBalance()), is(0));

    }

    @Test
    public void shouldReturnEmptyListIfUserHasNoAccounts() {
        final List<Account> all = accountRepository.findByUserId(5L);
        assertThat(all.isEmpty(), is(true));

    }
}