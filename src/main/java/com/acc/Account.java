package com.acc;

public class Account {

    private int accountId;
    private String accountName;
    private Bank bankDetails;

    public int accountId() {
        return accountId;
    }

    public Account setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    public String accountName() {
        return accountName;
    }

    public Account setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public Bank bankDetails() {
        return bankDetails;
    }

    public Account setBankDetails(Bank bankDetails) {
        this.bankDetails = bankDetails;
        return this;
    }
}
