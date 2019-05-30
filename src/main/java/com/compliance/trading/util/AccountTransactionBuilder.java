package com.compliance.trading.util;

import com.compliance.trading.models.Account;
import com.compliance.trading.models.AccountTransaction;

import java.math.BigDecimal;
import java.util.Date;

public final class AccountTransactionBuilder {
    private Long id;
    private Account account;
    private Date valueDate;
    private BigDecimal amount;
    private DebitCredit debitCredit;
    private String transactionNarrative;

    private AccountTransactionBuilder() {
    }

    public static AccountTransactionBuilder anAccountTransaction() {
        return new AccountTransactionBuilder();
    }

    public AccountTransactionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AccountTransactionBuilder withAccount(Account account) {
        this.account = account;
        return this;
    }

    public AccountTransactionBuilder withValueDate(Date valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public AccountTransactionBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public AccountTransactionBuilder withDebitCredit(DebitCredit debitCredit) {
        this.debitCredit = debitCredit;
        return this;
    }

    public AccountTransactionBuilder withTransactionNarrative(String transactionNarrative) {
        this.transactionNarrative = transactionNarrative;
        return this;
    }

    public AccountTransaction build() {
        return new AccountTransaction(id, account, valueDate, amount, debitCredit, transactionNarrative);
    }
}
