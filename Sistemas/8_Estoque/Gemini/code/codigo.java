import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Armazem {
    private String nome;
    private String localizacao;
    private Map<Produto, Integer> estoque;

    public Armazem(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.estoque = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Map<Produto, Integer> getEstoque() {
        return estoque;
    }

    public int getQuantidadeProduto(Produto produto) {
        return estoque.getOrDefault(produto, 0);
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        estoque.put(produto, estoque.getOrDefault(produto, 0) + quantidade);
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (estoque.containsKey(produto)) {
            int quantidadeAtual = estoque.get(produto);
            if (quantidadeAtual >= quantidade) {
                estoque.put(produto, quantidadeAtual - quantidade);
            } else {
                System.out.println("Quantidade insuficiente em estoque no armazém " + nome + " para o produto " + produto.getNome() + ".");
            }
        } else {
            System.out.println("Produto não encontrado no armazém " + nome + ".");
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Localização: " + localizacao;
    }
}

class Produto {
    private String nome;
    private String descricao;
    private double preco;

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Descrição: " + descricao + ", Preço: R$" + String.format("%.2f", preco);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}

class EntradaProduto {
    private Armazem armazem;
    private Produto produto;
    private int quantidade;
    private LocalDate data;

    public EntradaProduto(Armazem armazem, Produto produto, int quantidade, LocalDate data) {
        this.armazem = armazem;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Entrada de " + quantidade + " unidades de " + produto.getNome() + " no armazém " + armazem.getNome() + " em " + data;
    }
}

class SaidaProduto {
    private Armazem armazem;
    private Produto produto;
    private int quantidade;
    private LocalDate data;

    public SaidaProduto(Armazem armazem, Produto produto, int quantidade, LocalDate data) {
        this.armazem = armazem;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Saída de " + quantidade + " unidades de " + produto.getNome() + " do armazém " + armazem.getNome() + " em " + data;
    }
}

class TransferenciaProduto {
    private Armazem armazemOrigem;
    private Armazem armazemDestino;
    private Produto produto;
    private int quantidade;
    private LocalDate data;

    public TransferenciaProduto(Armazem armazemOrigem, Armazem armazemDestino, Produto produto, int quantidade, LocalDate data) {
        this.armazemOrigem = armazemOrigem;
        this.armazemDestino = armazemDestino;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Transferência de " + quantidade + " unidades de " + produto.getNome() + " do armazém " + armazemOrigem.getNome() + " para " + armazemDestino.getNome() + " em " + data;
    }
}

public class SistemaControleEstoque {
    private List<Armazem> armazens = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<EntradaProduto> entradas = new ArrayList<>();
    private List<SaidaProduto> saidas = new ArrayList<>();
    private List<TransferenciaProduto> transferencias = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaControleEstoque sistema = new SistemaControleEstoque();
        sistema.executar();
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n--- Sistema de Controle de Estoque ---");
            System.out.println("1. Criar Armazém");
            System.out.println("2. Visualizar Armazéns");
            System.out.println("3. Atualizar Armazém");
            System.out.println("4. Excluir Armazém");
            System.out.println("5. Registrar Produto");
            System.out.println("6. Atualizar Produto");
            System.out.println("7. Excluir Produto");
            System.out.println("8. Entrada de Produtos");
            System.out.println("9. Saída de Produtos");
            System.out.println("10. Transferência de Produtos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    criarArmazem();
                    break;
                case 2:
                    visualizarArmazens();
                    break;
                case 3:
                    atualizarArmazem();
                    break;
                case 4:
                    excluirArmazem();
                    break;
                case 5:
                    registrarProduto();
                    break;
                case 6:
                    atualizarProduto();
                    break;
                case 7:
                    excluirProduto();
                    break;
                case 8:
                    entradaProduto();
                    break;
                case 9:
                    saidaProduto();
                    break;
                case 10:
                    transferenciaProduto();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void criarArmazem() {
        System.out.print("Nome do armazém: ");
        String nome = scanner.nextLine();
        System.out.print("Localização do armazém: ");
        String localizacao = scanner.nextLine();
        Armazem novoArmazem = new Armazem(nome, localizacao);
        armazens.add(novoArmazem);
        System.out.println("Armazém '" + nome + "' criado com sucesso.");
    }

    private void visualizarArmazens() {
        if (armazens.isEmpty()) {
            System.out.println("Nenhum armazém cadastrado.");
            return;
        }
        System.out.println("\n--- Armazéns Cadastrados ---");
        for (int i = 0; i < armazens.size(); i++) {
            System.out.println((i + 1) + ". " + armazens.get(i));
        }
    }

    private void atualizarArmazem() {
        visualizarArmazens();
        if (armazens.isEmpty()) return;
        System.out.print("Selecione o número do armazém para atualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (indice > 0 && indice <= armazens.size()) {
            Armazem armazem = armazens.get(indice - 1);
            System.out.print("Novo nome (deixe em branco para manter): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                armazem.setNome(novoNome);
            }
            System.out.print("Nova localização (deixe em branco para manter): ");
            String novaLocalizacao = scanner.nextLine();
            if (!novaLocalizacao.isEmpty()) {
                armazem.setLocalizacao(novaLocalizacao);
            }
            System.out.println("Armazém '" + armazem.getNome() + "' atualizado com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void excluirArmazem() {
        visualizarArmazens();
        if (armazens.isEmpty()) return;
        System.out.print("Selecione o número do armazém para excluir: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (indice > 0 && indice <= armazens.size()) {
            Armazem armazemRemovido = armazens.remove(indice - 1);
            System.out.println("Armazém '" + armazemRemovido.getNome() + "' excluído com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void registrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço do produto: R$");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha
        Produto novoProduto = new Produto(nome, descricao, preco);
        produtos.add(novoProduto);
        System.out.println("Produto '" + nome + "' registrado com sucesso.");
    }

    private void visualizarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- Produtos Cadastrados ---");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + ". " + produtos.get(i));
        }
    }

    private Produto selecionarProduto() {
        visualizarProdutos();
        if (produtos.isEmpty()) return null;
        System.out.print("Selecione o número do produto: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        if (indice > 0 && indice <= produtos.size()) {
            return produtos.get(indice - 1);
        } else {
            System.out.println("Índice inválido.");
            return null;
        }
    }

    private Armazem selecionarArmazem() {
        visualizarArmazens();
        if (armazens.isEmpty()) return null;
        System.out.print("Selecione o número do armazém: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        if (indice > 0 && indice <= armazens.size()) {
            return armazens.get(indice - 1);
        } else {
            System.out.println("Índice inválido.");
            return null;
        }
    }

    private void atualizarProduto() {
        visualizarProdutos();
        if (produtos.isEmpty()) return;
        System.out.print("Selecione o número do produto para atualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (indice > 0 && indice <= produtos.size()) {
            Produto produto = produtos.get(indice - 1);
            System.out.print("Novo nome (deixe em branco para manter): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                produto.setNome(novoNome);
            }
            System.out.print("Nova descrição (deixe em branco para manter): ");
            String novaDescricao = scanner.nextLine();
            if (!novaDescricao.isEmpty()) {
                produto.setDescricao(novaDescricao);
            }
            System.out.print("Novo preço (deixe em branco para manter): R$");
            String novoPrecoStr = scanner.nextLine();
            if (!novoPrecoStr.isEmpty()) {
                try {
                    double novoPreco = Double.parseDouble(novoPrecoStr);
                    produto.setPreco(novoPreco);
                } catch (NumberFormatException e) {
                    System.out.println("Formato de preço inválido.");
                }
            }
            System.out.println("Produto '" + produto.getNome() + "' atualizado com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void excluirProduto() {
        visualizarProdutos();
        if (produtos.isEmpty()) return;
        System.out.print("Selecione o número do produto para excluir: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (indice > 0 && indice <= produtos.size()) {
            Produto produtoRemovido = produtos.remove(indice - 1);
            // Remover o produto dos estoques dos armazéns também
            for (Armazem armazem : armazens) {
                armazem.getEstoque().remove(produtoRemovido);
            }
            System.out.println("Produto '" + produtoRemovido.getNome() + "' excluído com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void entradaProduto() {
        Armazem armazem = selecionarArmazem();
        if (armazem == null) return;
        Produto produto = selecionarProduto();
        if (produto == null) return;
        System.out.print("Quantidade a adicionar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        if (quantidade > 0) {
            armazem.adicionarProduto(produto, quantidade);
            LocalDate dataEntrada = LocalDate.now();
            entradas.add(new EntradaProduto(armazem, produto, quantidade, dataEntrada));
            System.out.println(quantidade + " unidades de '" + produto.getNome() + "' adicionadas ao armazém '" + armazem.getNome() + "'.");
        } else {
            System.out.println("Quantidade inválida.");
        }
    }

    private void saidaProduto() {
        Armazem armazem = selecionarArmazem();
        if (armazem == null) return;
        Produto produto = selecionarProduto();
        if (produto == null) return;
        System.out.print("Quantidade a remover: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        if (quantidade > 0) {
            if (armazem.getQuantidadeProduto(produto) >= quantidade) {
                armazem.removerProduto(produto, quantidade);
                LocalDate dataSaida = LocalDate.now();
                saidas.add(new SaidaProduto(armazem, produto,