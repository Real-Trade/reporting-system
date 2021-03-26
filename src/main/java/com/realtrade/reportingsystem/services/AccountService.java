package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Account;
import com.realtrade.reportingsystem.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Transactional
    public Optional<Account> withdrawFunds(int accountId, double amount) {
        Optional<Account> account = getAccountById(accountId);
        if(account.isPresent()) {
            account.get().setBalance(account.get().getBalance() - amount);
            accountDao.save(account.get());
        }
        return account;
    }

    @Transactional
    public Optional<Account> depositFunds(int accountId, double amount) {
        Optional<Account> account = getAccountById(accountId);
        if(account.isPresent()) {
            account.get().setBalance(account.get().getBalance() + amount);
            accountDao.save(account.get());
        }
        return account;
    }

    public Optional<Account> getAccountById(int accountId) {
        return accountDao.findById(accountId);
    }

    public Optional<Account> getAccountByClientId(int clientId) {
        return accountDao.getAccountByClientId(clientId);
    }

    public List<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    public Account createAccount(Account account) {
        //generate account number
        //
        accountDao.save(account);
        return account;
    }

    public Account updateAccount(int accountId, Account account) {
        Optional<Account> existingAccount = accountDao.findById(accountId);
        if(existingAccount.isPresent()) {

            accountDao.save(existingAccount.get());
        }
        return account;
    }

}
