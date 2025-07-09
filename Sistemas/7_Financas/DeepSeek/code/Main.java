import model.Categoria;
import model.Transacao;
import service.FinancasService;
import service.RelatorioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static FinancasService financasService = new FinancasService();
    private static RelatorioService relatorioService = new RelatorioService(financasService.getTransacaoDAO());
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    adicionarReceita();
                    break;
                case 2:
                    adicionarDespesa();
                    break;
                case 3:
                    listarTransacoes();
                    break;
                case 4:
                    atualizarTransacao();
                    break;
                case 5:
                    removerTransacao();
                    break;
                case 6:
                    exibirSaldo();
                    break;
                case 7:
                    relatorioService.gerarRelatorioCompleto();
                    break;
                case 8:
                    gerarRelatorioPorPeriodo();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE FINANÇAS PESSOAIS =====");
        System.out.println("1. Adicionar Receita");
        System.out.println("2. Adicionar Despesa");
        System.out.println("3. Listar Transações");
        System.out.println("4. Atualizar Transação");
        System.out.println("5. Remover Transação");
        System.out.println("6. Exibir Saldo Atual");
        System.out.println("7. Gerar Relatorio Completo");
        System.out.println("8. Gerar Relatorio por Periodo");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarReceita() {
        try {
            System.out.println("\n--- Adicionar Receita ---");
            
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Data (dd/MM/yyyy): ");
            Date data = dateFormat.parse(scanner.nextLine());
            
            System.out.println("Categorias disponíveis:");
            List<Categoria> categorias = financasService.listarCategoriasReceita();
            for (Categoria cat : categorias) {
                System.out.println(cat.getId() + " - " + cat.getNome());
            }
            
            System.out.print("ID da Categoria: ");
            int categoriaId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            
            financasService.adicionarReceita(valor, data, categoriaId, descricao);
            System.out.println("Receita adicionada com sucesso!");
        } catch (ParseException e) {
            System.out.println("Formato de data inválido!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar receita: " + e.getMessage());
        }
    }

    private static void adicionarDespesa() {
        try {
            System.out.println("\n--- Adicionar Despesa ---");
            
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Data (dd/MM/yyyy): ");
            Date data = dateFormat.parse(scanner.nextLine());
            
            System.out.println("Categorias disponíveis:");
            List<Categoria> categorias = financasService.listarCategoriasDespesa();
            for (Categoria cat : categorias) {
                System.out.println(cat.getId() + " - " + cat.getNome());
            }
            
            System.out.print("ID da Categoria: ");
            int categoriaId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            
            financasService.adicionarDespesa(valor, data, categoriaId, descricao);
            System.out.println("Despesa adicionada com sucesso!");
        } catch (ParseException e) {
            System.out.println("Formato de data inválido!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar despesa: " + e.getMessage());
        }
    }

    private static void listarTransacoes() {
        System.out.println("\n--- Lista de Transações ---");
        List<Transacao> transacoes = financasService.listarTodasTransacoes();
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação cadastrada.");
        } else {
            for (Transacao t : transacoes) {
                System.out.printf("[%d] %s - %s - R$ %.2f - %s - %s\n",
                        t.getId(),
                        t.getTipo(),
                        dateFormat.format(t.getData()),
                        t.getValor(),
                        t.getCategoria().getNome(),
                        t.getDescricao());
            }
        }
    }

    private static void atualizarTransacao() {
        try {
            System.out.println("\n--- Atualizar Transação ---");
            listarTransacoes();
            
            System.out.print("ID da Transação: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Novo Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Nova Data (dd/MM/yyyy): ");
            Date data = dateFormat.parse(scanner.nextLine());
            
            System.out.println("Categorias disponíveis:");
            List<Categoria> categorias = financasService.listarCategoriasReceita();
            categorias.addAll(financasService.listarCategoriasDespesa());
            for (Categoria cat : categorias) {
                System.out.println(cat.getId() + " - " + cat.getNome() + " (" + cat.getTipo() + ")");
            }
            
            System.out.print("Novo ID da Categoria: ");
            int categoriaId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nova Descrição: ");
            String descricao = scanner.nextLine();
            
            boolean sucesso = financasService.atualizarTransacao(id, valor, data, categoriaId, descricao);
            if (sucesso) {
                System.out.println("Transação atualizada com sucesso!");
            } else {
                System.out.println("Transação não encontrada!");
            }
        } catch (ParseException e) {
            System.out.println("Formato de data inválido!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar transação: " + e.getMessage());
        }
    }

    private static void removerTransacao() {
        System.out.println("\n--- Remover Transação ---");
        listarTransacoes();
        
        System.out.print("ID da Transação: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        boolean sucesso = financasService.removerTransacao(id);
        if (sucesso) {
            System.out.println("Transação removida com sucesso!");
        } else {
            System.out.println("Transação não encontrada!");
        }
    }

    private static void exibirSaldo() {
        double saldo = financasService.calcularSaldo();
        System.out.printf("\nSaldo Atual: R$ %.2f\n", saldo);
    }

    private static void gerarRelatorioPorPeriodo() {
        try {
            System.out.println("\n--- Relatório por Período ---");
            System.out.print("Data Inicial (dd/MM/yyyy): ");
            Date inicio = dateFormat.parse(scanner.nextLine());
            
            System.out.print("Data Final (dd/MM/yyyy): ");
            Date fim = dateFormat.parse(scanner.nextLine());
            
            relatorioService.gerarRelatorioPeriodo(inicio, fim);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido!");
        }
    }
}