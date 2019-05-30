package com.compliance.trading.repository;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.util.AccountType;
import com.compliance.trading.util.Currency;
import com.compliance.trading.util.DebitCredit;
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
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountTransactionRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldFindByAccountId() {
        final Account account = new Account(1L, "11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"), 1L);
        final Account account2 = new Account(2L, "11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"), 1L);

        final AccountTransaction accountTransaction1 = new AccountTransaction(1L, account, new Date(),
                new BigDecimal("234234.93"), DebitCredit.CREDIT, "");
        final AccountTransaction accountTransaction2 = new AccountTransaction(2L ,account, new Date(),
                new BigDecimal("566.93"), DebitCredit.CREDIT, "narrative");
        final AccountTransaction accountTransaction3 = new AccountTransaction(3L ,account2, new Date(),
                new BigDecimal("53366.93"), DebitCredit.CREDIT, "narrative");

        accountRepository.save(account);
        accountRepository.save(account2);
        accountRepository.flush();
        accountTransactionRepository.save(accountTransaction1);
        accountTransactionRepository.save(accountTransaction2);
        accountTransactionRepository.save(accountTransaction3);
        accountTransactionRepository.flush();

        List<AccountTransaction> accountTxns = accountTransactionRepository.findByAccountId(account.getId());

        assertThat(accountTxns.size(), is(2));
        final AccountTransaction dbAccountTxn1 = accountTxns.get(0);
        assertThat(dbAccountTxn1.getAccount().getId(), is(account.getId()));
        assertThat(dbAccountTxn1.getId(), is(accountTransaction1.getId()));

        final AccountTransaction dbAccountTxn2 = accountTxns.get(1);
        assertThat(dbAccountTxn2.getAccount().getId(), is(account.getId()));
        assertThat(dbAccountTxn2.getId(), is(accountTransaction2.getId()));
    }
}