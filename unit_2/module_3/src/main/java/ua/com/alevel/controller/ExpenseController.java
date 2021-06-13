package ua.com.alevel.controller;

import ua.com.alevel.entity.Expense;
import ua.com.alevel.service.ExpenseService;

import java.util.List;

public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController() {
        expenseService = new ExpenseService();
    }

    public Expense getExpenseByType(String type) {
        return expenseService.getExpenseByType(type);
    }

    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    public void addExpense(Expense expense) {
        expenseService.addExpense(expense);
    }

    public void updateExpense(Expense expense) {
        expenseService.updateExpense(expense);
    }

    public void deleteExpenseByType(String type) {
        expenseService.deleteExpenseByType(type);
    }
}
