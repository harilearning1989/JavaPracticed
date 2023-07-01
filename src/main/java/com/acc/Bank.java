package com.acc;

public class Bank {

    private String bankName;
    private String bankBranch;
    private String ifscCode;

    public String bankName() {
        return bankName;
    }

    public Bank setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String bankBranch() {
        return bankBranch;
    }

    public Bank setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public String ifscCode() {
        return ifscCode;
    }

    public Bank setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
        return this;
    }
}
