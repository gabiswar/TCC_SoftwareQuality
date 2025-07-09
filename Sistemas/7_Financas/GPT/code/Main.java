import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Financeiro financeiro = new Financeiro();
        int opcao;

        do {
            System.out.println("\n=== MENU FINANCEIRO ===");
            System.out.println("1. Adicionar Receita");
            System.out.println("2. Adicionar Despesa");
            System.out.println("3. Atualizar Transação");
            System.out.println("4. Remover Transação");
            System.out.println("5. Ver Saldo Atual");
            System.out.println("6. Ver Receitas por Categoria");
            System.out.println("7. Ver Despesas por Categoria");
            System.out.println("8. Gerar Relatório Financeiro");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    Transacao receita = criarTransacao(sc, "Receita");
                    financeiro.adicionarTransacao(receita);
                }
                case 2 -> {
                    Transacao despesa = criarTransacao(sc, "Despesa");
                    financeiro.adicionarTransacao(despesa);
                }
                case 3 -> {
                    listarTransacoes(financeiro);
                    System.out.print("Informe o índice da transação a atualizar: ");
                    int idx = sc.nextInt();
                    Transacao nova = criarTransacao(sc, financeiro.getTransacoes().get(idx).getTipo());
                    financeiro.atualizarTransacao(idx, nova);
                }
                case 4 -> {
                    listarTransacoes(financeiro);
                    System.out.print("Informe o índice da transação a remover: ");
                    int idx = sc.nextInt();
                    financeiro.removerTransacao(idx);
                }
                case 5 -> System.out.printf("Saldo Atual: %.2f%n", financeiro.calcularSaldo());
                case 6 -> {
                    System.out.print("Categoria (SALARIO, PRESENTE, OUTROS_RECEITA): ");
                    Categoria c = Categoria.valueOf(sc.next());
                    financeiro.listarPorCategoria(c, "Receita")
                            .forEach(t -> System.out.printf("%.2f | %s%n", t.getValor(), t.getData()));
                }
                case 7 -> {
                    System.out.print("Categoria (ALIMENTACAO, LAZER, TRANSPORTE, OUTROS_DESPESA): ");
                    Categoria c = Categoria.valueOf(sc.next());
                    financeiro.listarPorCategoria(c, "Despesa")
                            .forEach(t -> System.out.printf("%.2f | %s%n", t.getValor(), t.getData()));
                }
                case 8 -> financeiro.gerarRelatorio();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static Transacao criarTransacao(Scanner sc, String tipo) {
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(sc.next());
        System.out.print("Categoria: ");
        Categoria categoria = Categoria.valueOf(sc.next());

        return tipo.equals("Receita") ? new Receita(valor, data, categoria)
                                      : new Despesa(valor, data, categoria);
    }

    private static void listarTransacoes(Financeiro financeiro) {
        int i = 0;
        for (Transacao t : financeiro.getTransacoes()) {
            System.out.printf("[%d] [%s] %.2f | %s | %s%n", i++, t.getTipo(), t.getValor(), t.getData(), t.getCategoria());
        }
    }
}
