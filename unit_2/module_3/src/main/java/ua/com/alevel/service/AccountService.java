package ua.com.alevel.service;

import ua.com.alevel.entity.Account;
import ua.com.alevel.repository.AccountRepository;

import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    public Account getAccountById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return accountRepository.getAccountById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }

    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    public void deleteAccountById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        accountRepository.deleteAccountById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllAccounts().size();
    }
}
