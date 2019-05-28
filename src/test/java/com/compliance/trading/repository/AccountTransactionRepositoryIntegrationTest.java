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
    public void shouldCreateAccountTransaction() {
        Account account = new Account("11111", "test", AccountType.CURRENT,
                new Date(), Currency.SGD, new BigDecimal("42342.99"));
        AccountTransaction accountTransaction1 = new AccountTransaction(account, new Date(),
                new BigDecimal("234234.93"), DebitCredit.CREDIT, "");
        AccountTransaction accountTransaction2 = new AccountTransaction(account, new Date(),
                new BigDecimal("566.93"), DebitCredit.CREDIT, "narrative");

        accountRepository.saveAndFlush(account);
        accountTransactionRepository.save(accountTransaction1);
        accountTransactionRepository.save(accountTransaction2);
        accountTransactionRepository.flush();

        List<AccountTransaction> accountTxns = accountTransactionRepository.findByAccountId(account.getId());
        assertThat(accountTxns.size(), is(2));
        AccountTransaction dbAccountTxn1 = accountTxns.get(0);
        assertThat(dbAccountTxn1.getAccount().getId(), is(account.getId()));
        AccountTransaction dbAccountTxn2 = accountTxns.get(1);
        assertThat(dbAccountTxn2.getAccount().getId(), is(account.getId()));
    }
}