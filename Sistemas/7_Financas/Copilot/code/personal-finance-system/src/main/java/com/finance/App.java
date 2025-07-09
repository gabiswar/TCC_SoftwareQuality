package com.finance;

import java.util.Scanner;

public class App {
    private static final FinanceService financeService = new FinanceService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Finanças Pessoais!");
        boolean running = true;

        while (running) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Registrar Receita");
            System.out.println("2. Registrar Despesa");
            System.out.println("3. Atualizar Receita/Despesa");
            System.out.println("4. Excluir Receita/Despesa");
            System.out.println("5. Visualizar Saldo Atual");
            System.out.println("6. Visualizar Despesas por Categoria");
            System.out.println("7. Visualizar Receitas por Categoria");
            System.out.println("8. Gerar Relatórios Financeiros");
            System.out.println("9. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (choice) {
                case 1:
                    financeService.registerIncome(scanner);
                    break;
                case 2:
                    financeService.registerExpense(scanner);
                    break;
                case 3:
                    financeService.updateTransaction(scanner);
                    break;
                case 4:
                    financeService.deleteTransaction(scanner);
                    break;
                case 5:
                    financeService.viewCurrentBalance();
                    break;
                case 6:
                    financeService.viewExpensesByCategory(scanner);
                    break;
                case 7:
                    financeService.viewIncomesByCategory(scanner);
                    break;
                case 8:
                    financeService.generateFinancialReport();
                    break;
                case 9:
                    running = false;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}