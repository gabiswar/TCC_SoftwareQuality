import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Quadro> quadros = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Gerenciador de Projetos ---");
            System.out.println("1. Gerenciar Quadros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    gerenciarQuadros();
                    break;
                case "0":
                    System.out.println("Saindo do gerenciador de projetos.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerenciarQuadros() {
        while (true) {
            System.out.println("\n--- Gerenciar Quadros ---");
            System.out.println("1. Criar Quadro");
            System.out.println("2. Visualizar Quadros");
            System.out.println("3. Atualizar Quadro");
            System.out.println("4. Excluir Quadro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    criarQuadro();
                    break;
                case "2":
                    visualizarQuadros();
                    break;
                case "3":
                    atualizarQuadro();
                    break;
                case "4":
                    excluirQuadro();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarQuadro() {
        System.out.print("Digite o nome do novo quadro: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a descrição do novo quadro: ");
        String descricao = scanner.nextLine();
        Quadro novoQuadro = new Quadro(nome, descricao);
        quadros.add(novoQuadro);
        System.out.println("Quadro '" + nome + "' criado com sucesso.");
    }

    private static void visualizarQuadros() {
        if (quadros.isEmpty()) {
            System.out.println("Nenhum quadro criado ainda.");
            return;
        }
        System.out.println("\n--- Quadros Existentes ---");
        for (int i = 0; i < quadros.size(); i++) {
            System.out.println((i + 1) + ". " + quadros.get(i).getNome());
        }
        System.out.print("Digite o número do quadro para visualizar detalhes (ou 0 para voltar): ");
        String escolha = scanner.nextLine();
        try {
            int indice = Integer.parseInt(escolha);
            if (indice > 0 && indice <= quadros.size()) {
                visualizarDetalhesQuadro(quadros.get(indice - 1));
            } else if (!escolha.equals("0")) {
                System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void visualizarDetalhesQuadro(Quadro quadro) {
        System.out.println("\n--- Detalhes do Quadro '" + quadro.getNome() + "' ---");
        System.out.println("Descrição: " + quadro.getDescricao());
        if (quadro.getListas().isEmpty()) {
            System.out.println("Nenhuma lista neste quadro ainda.");
        } else {
            System.out.println("\n--- Listas no Quadro ---");
            for (int i = 0; i < quadro.getListas().size(); i++) {
                System.out.println((i + 1) + ". " + quadro.getListas().get(i).getNome());
            }
            // Aqui você poderia adicionar a opção de gerenciar as listas do quadro
            System.out.println("\nOpções para este quadro:");
            System.out.println("1. Criar Lista");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            if (opcao.equals("1")) {
                criarLista(quadro);
            }
        }
    }

    private static void criarLista(Quadro quadro) {
        System.out.print("Digite o nome da nova lista: ");
        String nome = scanner.nextLine();
        Lista novaLista = new Lista(nome);
        quadro.adicionarLista(novaLista);
        System.out.println("Lista '" + nome + "' criada no quadro '" + quadro.getNome() + "'.");
    }

    private static void atualizarQuadro() {
        if (quadros.isEmpty()) {
            System.out.println("Nenhum quadro criado ainda.");
            return;
        }
        System.out.println("\n--- Quadros Existentes ---");
        for (int i = 0; i < quadros.size(); i++) {
            System.out.println((i + 1) + ". " + quadros.get(i).getNome());
        }
        System.out.print("Digite o número do quadro para atualizar (ou 0 para voltar): ");
        String escolha = scanner.nextLine();
        try {
            int indice = Integer.parseInt(escolha);
            if (indice > 0 && indice <= quadros.size()) {
                Quadro quadroParaAtualizar = quadros.get(indice - 1);
                System.out.print("Digite o novo nome (deixe em branco para manter o atual): ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) {
                    quadroParaAtualizar.setNome(novoNome);
                }
                System.out.print("Digite a nova descrição (deixe em branco para manter a atual): ");
                String novaDescricao = scanner.nextLine();
                if (!novaDescricao.isEmpty()) {
                    quadroParaAtualizar.setDescricao(novaDescricao);
                }
                System.out.println("Quadro '" + quadroParaAtualizar.getNome() + "' atualizado.");
            } else if (!escolha.equals("0")) {
                System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void excluirQuadro() {
        if (quadros.isEmpty()) {
            System.out.println("Nenhum quadro criado ainda.");
            return;
        }
        System.out.println("\n--- Quadros Existentes ---");
        for (int i = 0; i < quadros.size(); i++) {
            System.out.println((i + 1) + ". " + quadros.get(i).getNome());
        }
        System.out.print("Digite o número do quadro para excluir (ou 0 para voltar): ");
        String escolha = scanner.nextLine();
        try {
            int indice = Integer.parseInt(escolha);
            if (indice > 0 && indice <= quadros.size()) {
                Quadro quadroRemovido = quadros.remove(indice - 1);
                System.out.println("Quadro '" + quadroRemovido.getNome() + "' excluído.");
            } else if (!escolha.equals("0")) {
                System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    // As outras funcionalidades (gerenciar listas, cartões, mover cartões)
    // seguiriam uma estrutura similar, com métodos para criar, visualizar,
    // atualizar e excluir, interagindo com as listas e cartões dentro dos quadros.

    // Por exemplo, para gerenciar listas dentro de um quadro, você precisaria
    // de um método `gerenciarListas(Quadro quadro)` chamado a partir de
    // `visualizarDetalhesQuadro`.

    // Da mesma forma, para gerenciar cartões dentro de uma lista, um método
    // `gerenciarCartoes(Lista lista)` seria necessário.

    // O método para mover cartões precisaria receber o quadro, o cartão a ser movido,
    // a lista de origem e a lista de destino.
}