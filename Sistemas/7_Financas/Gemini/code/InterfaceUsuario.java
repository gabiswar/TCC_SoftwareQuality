import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Classe para representar uma Categoria
class Categoria {
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

// Classe abstrata para representar uma Transação (Receita ou Despesa)
abstract class Transacao {
    private double valor;
    private LocalDate data;
    private Categoria categoria;

    public Transacao(double valor, LocalDate data, Categoria categoria) {
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Valor: %.2f, Data: %s, Categoria: %s", valor, data, categoria);
    }
}

// Classe para representar uma Receita
class Receita extends Transacao {
    public Receita(double valor, LocalDate data, Categoria categoria) {
        super(valor, data, categoria);
    }

    @Override
    public String toString() {
        return "Receita - " + super.toString();
    }
}

// Classe para representar uma Despesa
class Despesa extends Transacao {
    public Despesa(double valor, LocalDate data, Categoria categoria) {
        super(valor, data, categoria);
    }

    @Override
    public String toString() {
        return "Despesa - " + super.toString();
    }
}

// Classe para gerenciar as finanças
class GerenciadorFinancas {
    private List<Receita> receitas;
    private List<Despesa> despesas;
    private List<Categoria> categorias;

    public GerenciadorFinancas() {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.categorias = new ArrayList<>();
        // Adicionando algumas categorias iniciais
        categorias.add(new Categoria("Salário"));
        categorias.add(new Categoria("Alimentação"));
        categorias.add(new Categoria("Lazer"));
        categorias.add(new Categoria("Outros"));
    }

    public void registrarReceita(double valor, LocalDate data, Categoria categoria) {
        receitas.add(new Receita(valor, data, categoria));
        System.out.println("Receita registrada com sucesso!");
    }

    public void registrarDespesa(double valor, LocalDate data, Categoria categoria) {
        despesas.add(new Despesa(valor, data, categoria));
        System.out.println("Despesa registrada com sucesso!");
    }

    public void atualizarReceita(int indice, double novoValor, LocalDate novaData, Categoria novaCategoria) {
        if (indice >= 0 && indice < receitas.size()) {
            Receita receita = receitas.get(indice);
            receita.setValor(novoValor);
            receita.setData(novaData);
            receita.setCategoria(novaCategoria);
            System.out.println("Receita atualizada com sucesso!");
        } else {
            System.out.println("Índice de receita inválido.");
        }
    }

    public void atualizarDespesa(int indice, double novoValor, LocalDate novaData, Categoria novaCategoria) {
        if (indice >= 0 && indice < despesas.size()) {
            Despesa despesa = despesas.get(indice);
            despesa.setValor(novoValor);
            despesa.setData(novaData);
            despesa.setCategoria(novaCategoria);
            System.out.println("Despesa atualizada com sucesso!");
        } else {
            System.out.println("Índice de despesa inválido.");
        }
    }

    public void excluirReceita(int indice) {
        if (indice >= 0 && indice < receitas.size()) {
            receitas.remove(indice);
            System.out.println("Receita excluída com sucesso!");
        } else {
            System.out.println("Índice de receita inválido.");
        }
    }

    public void excluirDespesa(int indice) {
        if (indice >= 0 && indice < despesas.size()) {
            despesas.remove(indice);
            System.out.println("Despesa excluída com sucesso!");
        } else {
            System.out.println("Índice de despesa inválido.");
        }
    }

    public double calcularSaldo() {
        double totalReceitas = receitas.stream().mapToDouble(Receita::getValor).sum();
        double totalDespesas = despesas.stream().mapToDouble(Despesa::getValor).sum();
        return totalReceitas - totalDespesas;
    }

    public void visualizarDespesasPorCategoria() {
        Map<Categoria, Double> despesasPorCategoria = new HashMap<>();
        for (Despesa despesa : despesas) {
            Categoria categoria = despesa.getCategoria();
            despesasPorCategoria.put(categoria, despesasPorCategoria.getOrDefault(categoria, 0.0) + despesa.getValor());
        }
        System.out.println("\n--- Despesas por Categoria ---");
        despesasPorCategoria.forEach((categoria, total) -> System.out.printf("%s: %.2f\n", categoria, total));
    }

    public void visualizarReceitasPorCategoria() {
        Map<Categoria, Double> receitasPorCategoria = new HashMap<>();
        for (Receita receita : receitas) {
            Categoria categoria = receita.getCategoria();
            receitasPorCategoria.put(categoria, receitasPorCategoria.getOrDefault(categoria, 0.0) + receita.getValor());
        }
        System.out.println("\n--- Receitas por Categoria ---");
        receitasPorCategoria.forEach((categoria, total) -> System.out.printf("%s: %.2f\n", categoria, total));
    }

    public void gerarRelatorioFinanceiro() {
        System.out.println("\n--- Relatório Financeiro ---");
        System.out.println("\n--- Receitas ---");
        if (receitas.isEmpty()) {
            System.out.println("Nenhuma receita registrada.");
        } else {
            receitas.forEach(System.out::println);
        }
        System.out.println("\n--- Despesas ---");
        if (despesas.isEmpty()) {
            System.out.println("Nenhuma despesa registrada.");
        } else {
            despesas.forEach(System.out::println);
        }
        System.out.printf("\nSaldo Atual: %.2f\n", calcularSaldo());
        visualizarDespesasPorCategoria();
        visualizarReceitasPorCategoria();
        System.out.println("---------------------------\n");
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}

// Classe para a interface do usuário
public class InterfaceUsuario {
    private GerenciadorFinancas gerenciador;
    private Scanner scanner;

    public InterfaceUsuario() {
        this.gerenciador = new GerenciadorFinancas();
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n--- Sistema de Finanças Pessoais ---");
            System.out.println("1. Registrar Receita");
            System.out.println("2. Registrar Despesa");
            System.out.println("3. Atualizar Receita");
            System.out.println("4. Atualizar Despesa");
            System.out.println("5. Excluir Receita");
            System.out.println("6. Excluir Despesa");
            System.out.println("7. Visualizar Saldo Atual");
            System.out.println("8. Visualizar Despesas por Categoria");
            System.out.println("9. Visualizar Receitas por Categoria");
            System.out.println("10. Gerar Relatório Financeiro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    registrarReceita();
                    break;
                case 2:
                    registrarDespesa();
                    break;
                case 3:
                    atualizarReceita();
                    break;
                case 4:
                    atualizarDespesa();
                    break;
                case 5:
                    excluirReceita();
                    break;
                case 6:
                    excluirDespesa();
                    break;
                case 7:
                    visualizarSaldoAtual();
                    break;
                case 8:
                    gerenciador.visualizarDespesasPorCategoria();
                    break;
                case 9:
                    gerenciador.visualizarReceitasPorCategoria();
                    break;
                case 10:
                    gerenciador.gerarRelatorioFinanceiro();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private void registrarReceita() {
        System.out.print("Valor da receita: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Data da receita (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        Categoria categoria = escolherCategoria();
        gerenciador.registrarReceita(valor, data, categoria);
    }

    private void registrarDespesa() {
        System.out.print("Valor da despesa: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Data da despesa (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        Categoria categoria = escolherCategoria();
        gerenciador.registrarDespesa(valor, data, categoria);
    }

    private void atualizarReceita() {
        if (gerenciador.getReceitas().isEmpty()) {
            System.out.println("Nenhuma receita registrada para atualizar.");
            return;
        }
        System.out.println("--- Receitas Registradas ---");
        for (int i = 0; i < gerenciador.getReceitas().size(); i++) {
            System.out.printf("%d. %s\n", i, gerenciador.getReceitas().get(i));
        }
        System.out.print("Digite o número da receita para atualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo valor da receita: ");
        double novoValor = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nova data da receita (AAAA-MM-DD): ");
        LocalDate novaData = LocalDate.parse(scanner.nextLine());
        Categoria novaCategoria = escolherCategoria();
        gerenciador.atualizarReceita(indice, novoValor, novaData, novaCategoria);
    }

    private void atualizarDespesa() {
        if (gerenciador.getDespesas().isEmpty()) {
            System.out.println("Nenhuma despesa registrada para atualizar.");
            return;
        }
        System.out.println("--- Despesas Registradas ---");
        for (int i = 0; i < gerenciador.getDespesas().size(); i++) {
            System.out.printf("%d. %s\n", i, gerenciador.getDespesas().get(i));
        }
        System.out.print("Digite o número da despesa para atualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo valor da despesa: ");
        double novoValor = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nova data da despesa (AAAA-MM-DD): ");
        LocalDate novaData = LocalDate.parse(scanner.nextLine());
        Categoria novaCategoria = escolherCategoria();
        gerenciador.atualizarDespesa(indice, novoValor, novaData, novaCategoria);
    }

    private void excluirReceita() {
        if (gerenciador.getReceitas().isEmpty()) {
            System.out.println("Nenhuma receita registrada para excluir.");
            return;
        }
        System.out.println("--- Receitas Registradas ---");
        for (int i = 0; i < gerenciador.getReceitas().size(); i++) {
            System.out.printf("%d. %s\n", i, gerenciador.getReceitas().get(i));
        }
        System.out.print("Digite o número da receita para excluir: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        gerenciador.excluirReceita(indice);
    }

    private void excluirDespesa() {
        if (gerenciador.getDespesas().isEmpty()) {
            System.out.println("Nenhuma despesa registrada para excluir.");
            return;
        }
        System.out.println("--- Despesas Registradas ---");
        for (int i = 0; i < gerenciador.getDespesas().size(); i++) {
            System.out.printf("%d. %s\n", i, gerenciador.getDespesas().get(i));
        }
        System.out.print("Digite o número da despesa para excluir: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        gerenciador.excluirDespesa(indice);
    }

    private void visualizarSaldoAtual() {
        System.out.printf("Saldo Atual: %.2f\n", gerenciador.calcularSaldo());
    }

    private Categoria escolherCategoria() {
        List<Categoria> categorias = gerenciador.getCategorias();
        System.out.println("--- Categorias ---");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.printf("%d. %s\n", i, categorias.get(i));
        }
        System.out.print("Escolha o número da categoria: ");
        int indiceCategoria = scanner.nextInt();
        scanner.nextLine();
        if (indiceCategoria >= 0 && indiceCategoria < categorias.size()) {
            return categorias.get(indiceCategoria);
        } else {
            System.out.println("Categoria inválida. Usando a categoria 'Outros'.");
            return gerenciador.getCategorias().stream().filter(c -> c.getNome().equals("Outros")).findFirst().orElse(new Categoria("Outros"));
        }
    }

    public static void main(String[] args) {
        InterfaceUsuario ui = new InterfaceUsuario();
        ui.executar();
    }
}