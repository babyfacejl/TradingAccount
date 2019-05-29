package com.compliance.trading.util;

public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current");

    private final String desc;
    AccountType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
