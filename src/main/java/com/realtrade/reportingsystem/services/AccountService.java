package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Account;
import com.realtrade.reportingsystem.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Optional<Account> withdrawFunds(int accountId, double amount) {
        Optional<Account> account = getAccountById(accountId);
        if(account.isPresent()) {
            account.get().setBalance(account.get().getBalance() - amount);
            account.get().setUpdatedAt(OffsetDateTime.now());
            accountDao.save(account.get());
        }
        return account;
    }

    public Optional<Account> depositFunds(int accountId, double amount) {
        Optional<Account> account = getAccountById(accountId);
        if(account.isPresent()) {
            account.get().setBalance(account.get().getBalance() + amount);
            account.get().setUpdatedAt(OffsetDateTime.now());
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

    public Optional<Account> updateAccount(int accountId, Account account) {
        Optional<Account> existingAccount = accountDao.findById(accountId);
        if(existingAccount.isPresent()) {
            existingAccount.get().setAccountNumber(account.getAccountNumber());
            existingAccount.get().setBalance(account.getBalance());
            existingAccount.get().setUpdatedAt(OffsetDateTime.now());
            accountDao.save(existingAccount.get());
        }
        return existingAccount;
    }

}
