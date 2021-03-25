package com.realtrade.reportingsystem.models;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int accountId;
    private  double balance;

    @JoinColumn(name = "client_id")
    @OneToOne
    private Client client;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Client getClient() {
        return client;
    }

    public void setClientId(Client client) {
        this.client = client;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
