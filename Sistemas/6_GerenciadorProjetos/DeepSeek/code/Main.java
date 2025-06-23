import model.Quadro;
import model.Lista;
import model.Cartao;
import dao.QuadroDAO;
import dao.ListaDAO;
import dao.CartaoDAO;
import service.QuadroService;
import service.ListaService;
import service.CartaoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static QuadroService quadroService;
    private static ListaService listaService;
    private static CartaoService cartaoService;
    private static Scanner scanner;

    public static void main(String[] args) {
        inicializarServicos();
        scanner = new Scanner(System.in);

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    gerenciarQuadros();
                    break;
                case 2:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 2);

        scanner.close();
    }

    private static void inicializarServicos() {
        QuadroDAO quadroDAO = new QuadroDAO();
        ListaDAO listaDAO = new ListaDAO();
        CartaoDAO cartaoDAO = new CartaoDAO();
        
        quadroService = new QuadroService(quadroDAO);
        listaService = new ListaService(listaDAO);
        cartaoService = new CartaoService(cartaoDAO);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===== GERENCIADOR DE PROJETOS =====");
        System.out.println("1. Gerenciar Quadros");
        System.out.println("2. Sair");
    }

    private static void gerenciarQuadros() {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR QUADROS =====");
            System.out.println("1. Criar novo quadro");
            System.out.println("2. Listar quadros");
            System.out.println("3. Visualizar/Editar quadro");
            System.out.println("4. Voltar");
            
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    criarQuadro();
                    break;
                case 2:
                    listarQuadros();
                    break;
                case 3:
                    visualizarEditarQuadro();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void criarQuadro() {
        System.out.println("\n===== CRIAR QUADRO =====");
        String nome = lerString("Nome do quadro: ");
        String descricao = lerString("Descrição: ");
        
        Quadro quadro = quadroService.criarQuadro(nome, descricao);
        System.out.println("Quadro criado com sucesso! ID: " + quadro.getId());
    }

    private static void listarQuadros() {
        System.out.println("\n===== LISTA DE QUADROS =====");
        List<Quadro> quadros = quadroService.listarQuadros();
        
        if (quadros.isEmpty()) {
            System.out.println("Nenhum quadro encontrado.");
        } else {
            for (Quadro quadro : quadros) {
                System.out.println(quadro);
            }
        }
    }

    private static void visualizarEditarQuadro() {
        System.out.println("\n===== VISUALIZAR/EDITAR QUADRO =====");
        int id = lerInteiro("ID do quadro: ");
        
        Quadro quadro = quadroService.buscarQuadroPorId(id);
        if (quadro == null) {
            System.out.println("Quadro não encontrado!");
            return;
        }
        
        System.out.println("\nQuadro encontrado:");
        System.out.println(quadro);
        
        int opcao;
        do {
            System.out.println("\n1. Editar quadro");
            System.out.println("2. Gerenciar listas");
            System.out.println("3. Excluir quadro");
            System.out.println("4. Voltar");
            
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    editarQuadro(quadro);
                    break;
                case 2:
                    gerenciarListas(quadro);
                    break;
                case 3:
                    if (excluirQuadro(quadro)) {
                        return;
                    }
                    break;
                case 4:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void editarQuadro(Quadro quadro) {
        System.out.println("\n===== EDITAR QUADRO =====");
        String nome = lerString("Novo nome (" + quadro.getNome() + "): ");
        String descricao = lerString("Nova descrição (" + quadro.getDescricao() + "): ");
        
        if (quadroService.atualizarQuadro(quadro.getId(), nome, descricao)) {
            System.out.println("Quadro atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar quadro!");
        }
    }

    private static boolean excluirQuadro(Quadro quadro) {
        System.out.println("\n===== EXCLUIR QUADRO =====");
        String confirmacao = lerString("Tem certeza que deseja excluir o quadro " + quadro.getNome() + "? (s/n): ");
        
        if (confirmacao.equalsIgnoreCase("s")) {
            if (quadroService.excluirQuadro(quadro.getId())) {
                System.out.println("Quadro excluído com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao excluir quadro!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        return false;
    }

    private static void gerenciarListas(Quadro quadro) {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR LISTAS =====");
            System.out.println("Quadro: " + quadro.getNome());
            System.out.println("1. Criar nova lista");
            System.out.println("2. Listar listas");
            System.out.println("3. Visualizar/Editar lista");
            System.out.println("4. Voltar");
            
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    criarLista(quadro);
                    break;
                case 2:
                    listarListas(quadro);
                    break;
                case 3:
                    visualizarEditarLista(quadro);
                    break;
                case 4:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void criarLista(Quadro quadro) {
        System.out.println("\n===== CRIAR LISTA =====");
        String nome = lerString("Nome da lista: ");
        
        Lista lista = listaService.criarLista(nome, quadro);
        System.out.println("Lista criada com sucesso! ID: " + lista.getId());
    }

    private static void listarListas(Quadro quadro) {
        System.out.println("\n===== LISTAS DO QUADRO =====");
        List<Lista> listas = listaService.listarListasPorQuadro(quadro);
        
        if (listas.isEmpty()) {
            System.out.println("Nenhuma lista encontrada.");
        } else {
            for (Lista lista : listas) {
                System.out.println(lista);
            }
        }
    }

    private static void visualizarEditarLista(Quadro quadro) {
        System.out.println("\n===== VISUALIZAR/EDITAR LISTA =====");
        int id = lerInteiro("ID da lista: ");
        
        Lista lista = listaService.buscarListaPorId(id);
        if (lista == null || !lista.getQuadro().equals(quadro)) {
            System.out.println("Lista não encontrada neste quadro!");
            return;
        }
        
        System.out.println("\nLista encontrada:");
        System.out.println(lista);
        
        int opcao;
        do {
            System.out.println("\n1. Editar lista");
            System.out.println("2. Gerenciar cartões");
            System.out.println("3. Excluir lista");
            System.out.println("4. Voltar");
            
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    editarLista(lista);
                    break;
                case 2:
                    gerenciarCartoes(lista);
                    break;
                case 3:
                    if (excluirLista(lista)) {
                        return;
                    }
                    break;
                case 4:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void editarLista(Lista lista) {
        System.out.println("\n===== EDITAR LISTA =====");
        String nome = lerString("Novo nome (" + lista.getNome() + "): ");
        
        if (listaService.atualizarLista(lista.getId(), nome)) {
            System.out.println("Lista atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar lista!");
        }
    }

    private static boolean excluirLista(Lista lista) {
        System.out.println("\n===== EXCLUIR LISTA =====");
        String confirmacao = lerString("Tem certeza que deseja excluir a lista " + lista.getNome() + "? (s/n): ");
        
        if (confirmacao.equalsIgnoreCase("s")) {
            if (listaService.excluirLista(lista.getId())) {
                System.out.println("Lista excluída com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao excluir lista!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        return false;
    }

    private static void gerenciarCartoes(Lista lista) {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR CARTÕES =====");
            System.out.println("Lista: " + lista.getNome());
            System.out.println("1. Criar novo cartão");
            System.out.println("2. Listar cartões");
            System.out.println("3. Visualizar/Editar cartão");
            System.out.println("4. Mover cartão para outra lista");
            System.out.println("5. Voltar");
            
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    criarCartao(lista);
                    break;
                case 2:
                    listarCartoes(lista);
                    break;
                case 3:
                    visualizarEditarCartao(lista);
                    break;
                case 4:
                    moverCartao(lista);
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void criarCartao(Lista lista) {
        System.out.println("\n===== CRIAR CARTÃO =====");
        String titulo = lerString("Título: ");
        String descricao = lerString("Descrição: ");
        LocalDate dataVencimento = lerData("Data de vencimento (dd/MM/yyyy): ");
        
        Cartao cartao = cartaoService.criarCartao(titulo, descricao, dataVencimento, lista);
        System.out.println("Cartão criado com sucesso! ID: " + cartao.getId());
    }

    private static void listarCartoes(Lista lista) {
        System.out.println("\n===== CARTÕES DA LISTA =====");
        List<Cartao> cartoes = cartaoService.listarCartoesPorLista(lista);
        
        if (cartoes.isEmpty()) {
            System.out.println("Nenhum cartão encontrado.");
        } else {
            for (Cartao cartao : cartoes) {
                System.out.println(cartao);
            }
        }
    }

    private static void visualizarEditarCartao(Lista lista) {
        System.out.println("\n===== VISUALIZAR/EDITAR CARTÃO =====");
        int id = lerInteiro("ID do cartão: ");
        
        Cartao cartao = cartaoService.buscarCartaoPorId(id);
        if (cartao == null || !cartao.getLista().equals(lista)) {
            System.out.println("Cartão não encontrado nesta lista!");
            return;
        }
        
        System.out.println("\nCartão encontrado:");
        System.out.println(cartao);
        
        int opcao;
        do {
            System.out.println("\n1. Editar cartão");
            System.out.println("2. Excluir cartão");
            System.out.println("3. Voltar");
            
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    editarCartao(cartao);
                    break;
                case 2:
                    if (excluirCartao(cartao)) {
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    private static void editarCartao(Cartao cartao) {
        System.out.println("\n===== EDITAR CARTÃO =====");
        String titulo = lerString("Novo título (" + cartao.getTitulo() + "): ");
        String descricao = lerString("Nova descrição (" + cartao.getDescricao() + "): ");
        LocalDate dataVencimento = lerData("Nova data de vencimento (" + 
            cartao.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "): ");
        
        if (cartaoService.atualizarCartao(cartao.getId(), titulo, descricao, dataVencimento)) {
            System.out.println("Cartão atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar cartão!");
        }
    }

    private static boolean excluirCartao(Cartao cartao) {
        System.out.println("\n===== EXCLUIR CARTÃO =====");
        String confirmacao = lerString("Tem certeza que deseja excluir o cartão " + cartao.getTitulo() + "? (s/n): ");
        
        if (confirmacao.equalsIgnoreCase("s")) {
            if (cartaoService.excluirCartao(cartao.getId())) {
                System.out.println("Cartão excluído com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao excluir cartão!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        return false;
    }

    private static void moverCartao(Lista listaOrigem) {
        System.out.println("\n===== MOVER CARTÃO =====");
        int idCartao = lerInteiro("ID do cartão a ser movido: ");
        
        Cartao cartao = cartaoService.buscarCartaoPorId(idCartao);
        if (cartao == null || !cartao.getLista().equals(listaOrigem)) {
            System.out.println("Cartão não encontrado nesta lista!");
            return;
        }
        
        System.out.println("\nListas disponíveis no quadro:");
        List<Lista> listas = listaService.listarListasPorQuadro(listaOrigem.getQuadro());
        for (Lista l : listas) {
            if (!l.equals(listaOrigem)) {
                System.out.println(l);
            }
        }
        
        int idListaDestino = lerInteiro("\nID da lista de destino: ");
        Lista listaDestino = listaService.buscarListaPorId(idListaDestino);
        
        if (listaDestino == null || !listaDestino.getQuadro().equals(listaOrigem.getQuadro())) {
            System.out.println("Lista de destino inválida!");
            return;
        }
        
        if (cartaoService.moverCartao(idCartao, listaDestino)) {
            System.out.println("Cartão movido com sucesso para a lista " + listaDestino.getNome());
        } else {
            System.out.println("Erro ao mover cartão!");
        }
    }

    // Métodos auxiliares para entrada de dados
    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String dataStr = scanner.nextLine();
                return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                System.out.println("Formato inválido! Use dd/MM/yyyy.");
            }
        }
    }
}