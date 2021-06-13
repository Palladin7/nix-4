package ua.com.alevel.service;

import ua.com.alevel.entity.Income;
import ua.com.alevel.repository.IncomeRepository;

import java.util.List;

public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService() {
        this.incomeRepository = new IncomeRepository();
    }

    public Income getIncomeByType(String type) {
        return incomeRepository.getIncomeByType(type);
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.getAllIncomes();
    }

    public void addIncome(Income income) {
        incomeRepository.addIncome(income);
    }

    public void updateIncome(Income income) {
        incomeRepository.updateIncome(income);
    }

    public void deleteIncomeByType(String type) {
        incomeRepository.deleteIncomeByType(type);
    }
}
