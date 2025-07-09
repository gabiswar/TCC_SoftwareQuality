package com.finance.service;

import com.finance.model.Expense;
import com.finance.model.Income;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    private List<Income> incomes;
    private List<Expense> expenses;

    public ReportService(List<Income> incomes, List<Expense> expenses) {
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public double calculateTotalIncome() {
        return incomes.stream().mapToDouble(Income::getValue).sum();
    }

    public double calculateTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getValue).sum();
    }

    public double calculateBalance() {
        return calculateTotalIncome() - calculateTotalExpenses();
    }

    public Map<String, List<Expense>> getExpensesByCategory() {
        return expenses.stream().collect(Collectors.groupingBy(Expense::getCategory));
    }

    public Map<String, List<Income>> getIncomesByCategory() {
        return incomes.stream().collect(Collectors.groupingBy(Income::getCategory));
    }

    public String generateFinancialReport() {
        StringBuilder report = new StringBuilder();
        report.append("Financial Report:\n");
        report.append("Total Income: ").append(calculateTotalIncome()).append("\n");
        report.append("Total Expenses: ").append(calculateTotalExpenses()).append("\n");
        report.append("Balance: ").append(calculateBalance()).append("\n");
        report.append("Expenses by Category:\n");

        getExpensesByCategory().forEach((category, expenseList) -> {
            report.append(category).append(": ").append(expenseList.stream().mapToDouble(Expense::getValue).sum()).append("\n");
        });

        report.append("Incomes by Category:\n");
        getIncomesByCategory().forEach((category, incomeList) -> {
            report.append(category).append(": ").append(incomeList.stream().mapToDouble(Income::getValue).sum()).append("\n");
        });

        return report.toString();
    }
}