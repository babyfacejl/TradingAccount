package com.compliance.trading.models;

import com.compliance.trading.util.DebitCredit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable=false)
    private Date valueDate;

    @Column(nullable=false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private DebitCredit debitCredit;

    private String transactionNarrative;

    protected AccountTransaction() {}

    public AccountTransaction(Long id, Account account, Date valueDate, BigDecimal amount, DebitCredit debitCredit, String transactionNarrative) {
        this.valueDate = valueDate;
        this.amount = amount;
        this.debitCredit = debitCredit;
        this.transactionNarrative = transactionNarrative;
        this.account = account;
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DebitCredit getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(DebitCredit debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getTransactionNarrative() {
        return transactionNarrative;
    }

    public void setTransactionNarrative(String transactionNarrative) {
        this.transactionNarrative = transactionNarrative;
    }

    public Long getId() {
        return id;
    }
}
