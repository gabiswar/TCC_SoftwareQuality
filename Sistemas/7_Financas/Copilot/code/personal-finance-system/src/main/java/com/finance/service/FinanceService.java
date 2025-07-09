package com.finance.service;

import com.finance.model.Expense;
import com.finance.model.Income;

import java.util.ArrayList;
import java.util.List;

public class FinanceService {
    private List<Income> incomes;
    private List<Expense> expenses;

    public FinanceService() {
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void updateIncome(int index, Income income) {
        if (index >= 0 && index < incomes.size()) {
            incomes.set(index, income);
        }
    }

    public void updateExpense(int index, Expense expense) {
        if (index >= 0 && index < expenses.size()) {
            expenses.set(index, expense);
        }
    }

    public void deleteIncome(int index) {
        if (index >= 0 && index < incomes.size()) {
            incomes.remove(index);
        }
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
    }

    public double getCurrentBalance() {
        double totalIncome = incomes.stream().mapToDouble(Income::getValue).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getValue).sum();
        return totalIncome - totalExpense;
    }

    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> filteredExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().getName().equalsIgnoreCase(category)) {
                filteredExpenses.add(expense);
            }
        }
        return filteredExpenses;
    }

    public List<Income> getIncomesByCategory(String category) {
        List<Income> filteredIncomes = new ArrayList<>();
        for (Income income : incomes) {
            if (income.getCategory().getName().equalsIgnoreCase(category)) {
                filteredIncomes.add(income);
            }
        }
        return filteredIncomes;
    }
}