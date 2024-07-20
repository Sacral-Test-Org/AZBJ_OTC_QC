package com.azbj.otcqc.model;

public class BankDetailsModel {
    private String ifscCode;
    private String bankName;
    private String bankBranch;
    private String micrCode;

    // Constructor
    public BankDetailsModel(String ifscCode, String bankName, String bankBranch, String micrCode) {
        this.ifscCode = ifscCode;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.micrCode = micrCode;
    }

    // Getters and Setters
    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }
}
