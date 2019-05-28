package com.compliance.trading.util;

public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current");

    private String desc;
    AccountType(String desc) {
        this.desc = desc;
    }


}
