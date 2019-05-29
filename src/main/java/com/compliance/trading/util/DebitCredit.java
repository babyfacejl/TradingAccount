package com.compliance.trading.util;

public enum DebitCredit {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String desc;
    DebitCredit(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
