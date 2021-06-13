package ua.com.alevel.controller;

import ua.com.alevel.entity.Transaction;
import ua.com.alevel.service.TransactionService;

import java.util.List;

public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController() {
        transactionService = new TransactionService();
    }

    public Transaction getTransactionById(Long id) {
        return transactionService.getTransactionById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactionService.addTransaction(transaction);
    }
}
