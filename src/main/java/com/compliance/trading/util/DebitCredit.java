package com.compliance.trading.util;

public enum DebitCredit {
    DEBIT("Debit"),
    CREDIT("Credit");

    private String desc;
    DebitCredit(String desc) {
        this.desc = desc;
    }
}
