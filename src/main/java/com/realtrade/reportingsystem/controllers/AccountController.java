package com.realtrade.reportingsystem.controllers;

import com.realtrade.reportingsystem.dto.AccountFund;
import com.realtrade.reportingsystem.models.Account;
import com.realtrade.reportingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
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
        return accountService.updateAccount(accountId, account).orElseThrow();
    }

    @PutMapping(path = "/{accountId}/withdraw")
    public Account withdrawFunds(@PathVariable(name = "accountId") int accountId,@RequestBody AccountFund amount) {
        return accountService.withdrawFunds(accountId, amount.getAmount()).orElseThrow();
    }

    @PutMapping(path = "/{accountId}/deposit")
    public Account depositFunds(@PathVariable(name = "accountId") int accountId,@RequestBody AccountFund accountFund) {
        return accountService.depositFunds(accountId, accountFund.getAmount()).orElseThrow();
    }
}
