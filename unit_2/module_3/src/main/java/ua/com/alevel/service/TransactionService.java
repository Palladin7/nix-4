package ua.com.alevel.service;

import ua.com.alevel.entity.Transaction;
import ua.com.alevel.repository.TransactionRepository;

import java.util.List;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }

    public Transaction getTransactionById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return transactionRepository.getTransactionById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllTransactions().size();
    }
}
