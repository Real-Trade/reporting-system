package com.realtrade.reportingsystem.controllers;

import com.realtrade.reportingsystem.models.Account;
import com.realtrade.reportingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(path = "/{accountId}")
    public Account getAccountById(@PathVariable(name = "accountId") int accountId) {
        return accountService.getAccountById(accountId).orElseThrow();
    }

    @GetMapping(path = "/client/{clientId}")
    public Account getAccountByClientId(@PathVariable(name = "clientId") int clientId) {
        return accountService.getAccountByClientId(clientId).orElseThrow();
    }

    @PutMapping(path = "/{accountId}/update")
    public Account updateAccount(@PathVariable(name = "accountId") int accountId, @RequestBody Account account) {
        return accountService.updateAccount(accountId, account);
    }

    @PutMapping(path = "/{accountId}/withdraw")
    public Account withdrawFunds(int accountId, double amount) {
        return accountService.withdrawFunds(accountId, amount).orElseThrow();
    }

    @PutMapping(path = "/accountId/deposit")
    public Account depositFunds(int accountId, double amount) {
        return accountService.depositFunds(accountId, amount).orElseThrow();
    }

}
