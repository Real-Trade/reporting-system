package com.realtrade.reportingsystem.dto;

public class AccountFund {
    private double amount;

    public AccountFund(){}

    public AccountFund(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
