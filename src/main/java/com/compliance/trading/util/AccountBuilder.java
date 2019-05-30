package com.compliance.trading.util;

import com.compliance.trading.models.Account;

import java.math.BigDecimal;
import java.util.Date;

public final class AccountBuilder {
    private Long id;
    private Long userId;
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private Date balanceDate;
    private Currency currency;
    private BigDecimal openingAvailableBalance;

    private AccountBuilder() {
    }

    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    public AccountBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public AccountBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder withAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public AccountBuilder withAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountBuilder withBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
        return this;
    }

    public AccountBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public AccountBuilder withOpeningAvailableBalance(BigDecimal openingAvailableBalance) {
        this.openingAvailableBalance = openingAvailableBalance;
        return this;
    }

    public Account build() {
        return new Account(id, accountNumber, accountName, accountType, balanceDate, currency, openingAvailableBalance, userId);
    }
}
