package ua.com.alevel.controller;

import ua.com.alevel.entity.Account;
import ua.com.alevel.service.AccountService;

import java.util.List;

public class AccountController {

    private final AccountService accountService;

    public AccountController() {
        accountService = new AccountService();
    }

    public Account getAccountById(Long id) {
        return accountService.getAccountById(id);
    }

    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    public void addAccount(Account account) {
        accountService.addAccount(account);
    }

    public void updateAccount(Account account) {
        accountService.updateAccount(account);
    }

    public void deleteAccountById(Long id) {
        accountService.deleteAccountById(id);
    }
}
