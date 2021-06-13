package ua.com.alevel.controller;

import ua.com.alevel.entity.Income;
import ua.com.alevel.service.IncomeService;

import java.util.List;

public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController() {
        incomeService = new IncomeService();
    }

    public Income getIncomeByType(String type) {
        return incomeService.getIncomeByType(type);
    }

    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    public void addIncome(Income income) {
        incomeService.addIncome(income);
    }

    public void updateIncome(Income income) {
        incomeService.updateIncome(income);
    }

    public void deleteIncomeByType(String type) {
        incomeService.deleteIncomeByType(type);
    }
}
