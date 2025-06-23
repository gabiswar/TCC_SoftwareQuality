import java.util.List;
import java.util.Scanner;
import model.*;
import service.*;
import util.GeradorRecibo;

public class MainApp {
    private static PedidoService pedidoService = new PedidoService();
    private static CozinhaService cozinhaService = new CozinhaService(pedidoService);
    private static PagamentoService pagamentoService = new PagamentoService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcao;
        
        do {
            System.out.println("\n=== SISTEMA DE PEDIDOS PARA RESTAURANTE ===");
            System.out.println("1. Criar novo pedido");
            System.out.println("2. Visualizar pedidos");
            System.out.println("3. Atualizar status de pedido");
            System.out.println("4. Integração com a cozinha");
            System.out.println("5. Processar pagamento");
            System.out.println("6. Cadastrar novo alimento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    criarPedido();
                    break;
                case 2:
                    visualizarPedidos();
                    break;
                case 3:
                    atualizarStatusPedido();
                    break;
                case 4:
                    menuCozinha();
                    break;
                case 5:
                    processarPagamento();
                    break;
                case 6:
                    cadastrarAlimento();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private static void criarPedido() {
        Pedido pedido = pedidoService.criarPedido();
        System.out.println("\nNovo pedido criado: #" + pedido.getId());
        
        int opcao;
        do {
            System.out.println("\nCardápio:");
            List<Alimento> cardapio = pedidoService.listarCardapio();
            for (Alimento alimento : cardapio) {
                System.out.println(alimento.getId() + ". " + alimento.getNome() + " - R$ " + alimento.getPreco());
            }
            
            System.out.print("\nDigite o ID do alimento (0 para finalizar): ");
            int alimentoId = scanner.nextInt();
            
            if (alimentoId == 0) break;
            
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            pedidoService.adicionarItemAoPedido(pedido.getId(), alimentoId, quantidade);
            
            System.out.println("Item adicionado ao pedido!");
        } while (true);
        
        System.out.print("Observações: ");
        String obs = scanner.nextLine();
        pedido.setObservacoes(obs);
        
        System.out.println("\nPedido #" + pedido.getId() + " criado com sucesso!");
        System.out.println(GeradorRecibo.gerarRecibo(pedido));
    }
    
    private static void visualizarPedidos() {
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        List<Pedido> pedidos = pedidoService.listarTodosPedidos();
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }
        
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
            System.out.println("Itens:");
            for (ItemPedido item : pedido.getItens()) {
                System.out.println("  " + item);
            }
            if (pedido.getObservacoes() != null && !pedido.getObservacoes().isEmpty()) {
                System.out.println("Obs: " + pedido.getObservacoes());
            }
            System.out.println();
        }
    }
    
    private static void atualizarStatusPedido() {
        System.out.print("\nDigite o ID do pedido: ");
        int pedidoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }
        
        System.out.println("\nStatus atual: " + pedido.getStatus().getDescricao());
        System.out.println("Novo status:");
        System.out.println("1. Recebido");
        System.out.println("2. Em preparo");
        System.out.println("3. Pronto");
        System.out.println("4. Entregue");
        System.out.println("5. Cancelado");
        System.out.print("Escolha: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        StatusPedido novoStatus;
        switch (opcao) {
            case 1:
                novoStatus = StatusPedido.RECEBIDO;
                break;
            case 2:
                novoStatus = StatusPedido.EM_PREPARO;
                break;
            case 3:
                novoStatus = StatusPedido.PRONTO;
                break;
            case 4:
                novoStatus = StatusPedido.ENTREGUE;
                break;
            case 5:
                novoStatus = StatusPedido.CANCELADO;
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        
        pedidoService.atualizarStatusPedido(pedidoId, novoStatus);
        System.out.println("Status atualizado para: " + novoStatus.getDescricao());
    }
    
    private static void menuCozinha() {
        int opcao;
        do {
            System.out.println("\n=== INTEGRAÇÃO COM A COZINHA ===");
            System.out.println("1. Listar pedidos recebidos");
            System.out.println("2. Listar pedidos em preparo");
            System.out.println("3. Iniciar preparo de pedido");
            System.out.println("4. Finalizar preparo de pedido");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    listarPedidosCozinha(StatusPedido.RECEBIDO);
                    break;
                case 2:
                    listarPedidosCozinha(StatusPedido.EM_PREPARO);
                    break;
                case 3:
                    iniciarPreparo();
                    break;
                case 4:
                    finalizarPreparo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private static void listarPedidosCozinha(StatusPedido status) {
        List<Pedido> pedidos;
        
        if (status == StatusPedido.RECEBIDO) {
            pedidos = cozinhaService.listarPedidosRecebidos();
            System.out.println("\n=== PEDIDOS RECEBIDOS ===");
        } else {
            pedidos = cozinhaService.listarPedidosEmPreparo();
            System.out.println("\n=== PEDIDOS EM PREPARO ===");
        }
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }
        
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
            System.out.println("Itens:");
            for (ItemPedido item : pedido.getItens()) {
                System.out.println("  " + item.getAlimento().getNome() + " x " + item.getQuantidade());
            }
            if (pedido.getObservacoes() != null && !pedido.getObservacoes().isEmpty()) {
                System.out.println("Obs: " + pedido.getObservacoes());
            }
            System.out.println();
        }
    }
    
    private static void iniciarPreparo() {
        System.out.print("\nDigite o ID do pedido para iniciar preparo: ");
        int pedidoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        cozinhaService.iniciarPreparo(pedidoId);
        System.out.println("Preparo iniciado para pedido #" + pedidoId);
    }
    
    private static void finalizarPreparo() {
        System.out.print("\nDigite o ID do pedido para finalizar preparo: ");
        int pedidoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        cozinhaService.finalizarPreparo(pedidoId);
        System.out.println("Pedido #" + pedidoId + " pronto para entrega!");
    }
    
    private static void processarPagamento() {
        System.out.print("\nDigite o ID do pedido: ");
        int pedidoId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }
        
        System.out.println("\n" + GeradorRecibo.gerarRecibo(pedido));
        System.out.print("Valor pago: R$ ");
        double valorPago = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        String resultado = pagamentoService.processarPagamento(pedido, valorPago);
        System.out.println(resultado);
        
        if (resultado.contains("sucesso")) {
            pedidoService.atualizarStatusPedido(pedidoId, StatusPedido.ENTREGUE);
        }
    }
    
    private static void cadastrarAlimento() {
        System.out.println("\n=== CADASTRAR NOVO ALIMENTO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        // Gerar novo ID
        int novoId = pedidoService.listarCardapio().size() + 1;
        
        Alimento novoAlimento = new Alimento(novoId, nome, descricao, preco);
        pedidoService.adicionarAlimento(novoAlimento);
        
        System.out.println("Alimento cadastrado com sucesso!");
    }
}