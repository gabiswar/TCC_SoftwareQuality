// Main.java
import model.Armazem;
import model.Produto;
import model.Movimentacao;
import model.TipoMovimentacao;
import dao.ArmazemDAO;
import dao.ProdutoDAO;
import dao.MovimentacaoDAO;
import service.ArmazemService;
import service.ProdutoService;
import service.EstoqueService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArmazemDAO armazemDAO = new ArmazemDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private static ArmazemService armazemService = new ArmazemService(armazemDAO);
    private static ProdutoService produtoService = new ProdutoService(produtoDAO);
    private static EstoqueService estoqueService = new EstoqueService(movimentacaoDAO, armazemDAO, produtoDAO);

    public static void main(String[] args) {
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n=== Sistema de Controle de Estoque ===");
            System.out.println("1. Gerenciar Armazéns");
            System.out.println("2. Gerenciar Produtos");
            System.out.println("3. Movimentações de Estoque");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    gerenciarArmazens();
                    break;
                case 2:
                    gerenciarProdutos();
                    break;
                case 3:
                    gerenciarMovimentacoes();
                    break;
                case 4:
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void gerenciarArmazens() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n=== Gerenciamento de Armazéns ===");
            System.out.println("1. Criar Armazém");
            System.out.println("2. Listar Armazéns");
            System.out.println("3. Atualizar Armazém");
            System.out.println("4. Excluir Armazém");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    criarArmazem();
                    break;
                case 2:
                    listarArmazens();
                    break;
                case 3:
                    atualizarArmazem();
                    break;
                case 4:
                    excluirArmazem();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarArmazem() {
        System.out.print("Nome do Armazém: ");
        String nome = scanner.nextLine();
        System.out.print("Localização: ");
        String localizacao = scanner.nextLine();
        
        armazemService.criarArmazem(nome, localizacao);
        System.out.println("Armazém criado com sucesso!");
    }

    private static void listarArmazens() {
        List<Armazem> armazens = armazemService.listarArmazens();
        if (armazens.isEmpty()) {
            System.out.println("Nenhum armazém cadastrado.");
        } else {
            System.out.println("\n=== Lista de Armazéns ===");
            for (Armazem armazem : armazens) {
                System.out.println(armazem);
            }
        }
    }

    private static void atualizarArmazem() {
        listarArmazens();
        System.out.print("ID do Armazém a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Nova Localização: ");
        String novaLocalizacao = scanner.nextLine();
        
        boolean sucesso = armazemService.atualizarArmazem(id, novoNome, novaLocalizacao);
        if (sucesso) {
            System.out.println("Armazém atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar armazém. Verifique o ID.");
        }
    }

    private static void excluirArmazem() {
        listarArmazens();
        System.out.print("ID do Armazém a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        boolean sucesso = armazemService.excluirArmazem(id);
        if (sucesso) {
            System.out.println("Armazém excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir armazém. Verifique o ID.");
        }
    }

    private static void gerenciarProdutos() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n=== Gerenciamento de Produtos ===");
            System.out.println("1. Criar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    criarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarProduto() {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        produtoService.criarProduto(nome, descricao, preco);
        System.out.println("Produto criado com sucesso!");
    }

    private static void listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\n=== Lista de Produtos ===");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private static void atualizarProduto() {
        listarProdutos();
        System.out.print("ID do Produto a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Nova Descrição: ");
        String novaDescricao = scanner.nextLine();
        System.out.print("Novo Preço: ");
        double novoPreco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        boolean sucesso = produtoService.atualizarProduto(id, novoNome, novaDescricao, novoPreco);
        if (sucesso) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar produto. Verifique o ID.");
        }
    }

    private static void excluirProduto() {
        listarProdutos();
        System.out.print("ID do Produto a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        boolean sucesso = produtoService.excluirProduto(id);
        if (sucesso) {
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir produto. Verifique o ID.");
        }
    }

    private static void gerenciarMovimentacoes() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n=== Gerenciamento de Movimentações ===");
            System.out.println("1. Registrar Entrada");
            System.out.println("2. Registrar Saída");
            System.out.println("3. Transferir Produto");
            System.out.println("4. Consultar Estoque");
            System.out.println("5. Listar Movimentações");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    registrarEntrada();
                    break;
                case 2:
                    registrarSaida();
                    break;
                case 3:
                    transferirProduto();
                    break;
                case 4:
                    consultarEstoque();
                    break;
                case 5:
                    listarMovimentacoes();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void registrarEntrada() {
        listarProdutos();
        System.out.print("ID do Produto: ");
        int produtoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        listarArmazens();
        System.out.print("ID do Armazém de Destino: ");
        int armazemId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        Date data = new Date(); // Data atual
        
        estoqueService.registrarEntrada(produtoId, armazemId, quantidade, data);
        System.out.println("Entrada registrada com sucesso!");
    }

    private static void registrarSaida() {
        listarProdutos();
        System.out.print("ID do Produto: ");
        int produtoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        listarArmazens();
        System.out.print("ID do Armazém de Origem: ");
        int armazemId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        // Verificar se há estoque suficiente
        int estoqueDisponivel = estoqueService.calcularEstoqueDisponivel(produtoId, armazemId);
        if (quantidade > estoqueDisponivel) {
            System.out.println("Estoque insuficiente! Disponível: " + estoqueDisponivel);
            return;
        }
        
        Date data = new Date(); // Data atual
        
        estoqueService.registrarSaida(produtoId, armazemId, quantidade, data);
        System.out.println("Saída registrada com sucesso!");
    }

    private static void transferirProduto() {
        listarProdutos();
        System.out.print("ID do Produto: ");
        int produtoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        listarArmazens();
        System.out.print("ID do Armazém de Origem: ");
        int armazemOrigemId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        listarArmazens();
        System.out.print("ID do Armazém de Destino: ");
        int armazemDestinoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        // Verificar se há estoque suficiente
        int estoqueDisponivel = estoqueService.calcularEstoqueDisponivel(produtoId, armazemOrigemId);
        if (quantidade > estoqueDisponivel) {
            System.out.println("Estoque insuficiente! Disponível: " + estoqueDisponivel);
            return;
        }
        
        Date data = new Date(); // Data atual
        
        estoqueService.transferirProduto(produtoId, armazemOrigemId, armazemDestinoId, quantidade, data);
        System.out.println("Transferência registrada com sucesso!");
    }

    private static void consultarEstoque() {
        listarProdutos();
        System.out.print("ID do Produto: ");
        int produtoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        listarArmazens();
        System.out.print("ID do Armazém: ");
        int armazemId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        int estoque = estoqueService.calcularEstoqueDisponivel(produtoId, armazemId);
        System.out.println("Estoque disponível: " + estoque);
    }

    private static void listarMovimentacoes() {
        List<Movimentacao> movimentacoes = estoqueService.listarMovimentacoes();
        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            System.out.println("\n=== Lista de Movimentações ===");
            for (Movimentacao mov : movimentacoes) {
                System.out.println(mov);
            }
        }
    }
}