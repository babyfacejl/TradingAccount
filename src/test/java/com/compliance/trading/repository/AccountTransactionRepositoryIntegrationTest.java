package com.compliance.trading.repository;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;
import com.compliance.trading.util.AccountBuilder;
import com.compliance.trading.util.AccountTransactionBuilder;
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
import static com.compliance.trading.util.DebitCredit.CREDIT;
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
        final Account account = AccountBuilder.anAccount()
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

        final AccountTransaction accountTransaction1 = AccountTransactionBuilder.anAccountTransaction()
                .withId(1L)
                .withAccount(account)
                .withValueDate(new Date())
                .withAmount(new BigDecimal("234234.93"))
                .withDebitCredit(CREDIT)
                .withTransactionNarrative("")
                .build();
        final AccountTransaction accountTransaction2 = AccountTransactionBuilder.anAccountTransaction()
                .withId(2L)
                .withAccount(account)
                .withValueDate(new Date())
                .withAmount(new BigDecimal("234.93"))
                .withDebitCredit(CREDIT)
                .withTransactionNarrative("narrative1")
                .build();
        final AccountTransaction accountTransaction3 = AccountTransactionBuilder.anAccountTransaction()
                .withId(3L)
                .withAccount(account2)
                .withValueDate(new Date())
                .withAmount(new BigDecimal("234331.3"))
                .withDebitCredit(CREDIT)
                .withTransactionNarrative("narrative2")
                .build();

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

    @Test
    public void shouldReturnEmptyListIfAccountHasNoTransactions() {
        List<AccountTransaction> accountTxns = accountTransactionRepository.findByAccountId(34545L);
        assertThat(accountTxns.isEmpty(), is(true));

    }
}