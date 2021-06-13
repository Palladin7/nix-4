package ua.com.alevel.service;

import ua.com.alevel.entity.Expense;
import ua.com.alevel.repository.ExpenseRepository;

import java.util.List;

public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService() {
        this.expenseRepository = new ExpenseRepository();
    }

    public Expense getExpenseByType(String type) {
        return expenseRepository.getExpenseByType(type);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
    }

    public void addExpense(Expense expense) {
        expenseRepository.addExpense(expense);
    }

    public void updateExpense(Expense expense) {
        expenseRepository.updateExpense(expense);
    }

    public void deleteExpenseByType(String type) {
        expenseRepository.deleteExpenseByType(type);
    }
}
