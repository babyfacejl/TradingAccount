package com.compliance.trading.models;

import com.compliance.trading.util.AccountType;
import com.compliance.trading.util.Currency;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Account {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id = UUID.randomUUID();
    @Column(nullable=false)
    private String accountNumber;
    @Column(nullable=false)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private AccountType accountType;
    private Date balanceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Currency currency;
    private BigDecimal openingBalance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<AccountTransaction> transactionList;

    protected Account() {}
    public Account(String accountNumber, String accountName, AccountType accountType, Date balanceDate, Currency currency, BigDecimal openingBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balanceDate = balanceDate;
        this.currency = currency;
        this.openingBalance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public UUID getId() {
        return id;
    }
}
