import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProjectManager manager = new ProjectManager();

        while (true) {
            System.out.println("\n*** GERENCIADOR DE PROJETOS ***");
            System.out.println("1. Criar Quadro");
            System.out.println("2. Ver Quadros");
            System.out.println("3. Atualizar Quadro");
            System.out.println("4. Remover Quadro");
            System.out.println("5. Gerenciar Listas de um Quadro");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome do quadro: ");
                    String nomeQuadro = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    manager.addBoard(new Board(nomeQuadro, descricao));
                    System.out.println("Quadro criado.");
                    break;

                case 2:
                    for (Board b : manager.getBoards()) {
                        System.out.println(b);
                    }
                    break;

                case 3:
                    System.out.print("Nome do quadro a atualizar: ");
                    String oldName = sc.nextLine();
                    Board bAtualizar = manager.findBoard(oldName);
                    if (bAtualizar != null) {
                        System.out.print("Novo nome: ");
                        bAtualizar.setName(sc.nextLine());
                        System.out.print("Nova descrição: ");
                        bAtualizar.setDescription(sc.nextLine());
                        System.out.println("Quadro atualizado.");
                    } else {
                        System.out.println("Quadro não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Nome do quadro a remover: ");
                    String nomeRemover = sc.nextLine();
                    Board bRemover = manager.findBoard(nomeRemover);
                    if (bRemover != null) {
                        manager.removeBoard(bRemover);
                        System.out.println("Quadro removido.");
                    } else {
                        System.out.println("Quadro não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Nome do quadro: ");
                    String nomeQuadroListas = sc.nextLine();
                    Board board = manager.findBoard(nomeQuadroListas);
                    if (board != null) {
                        gerenciarListas(board, manager, sc);
                    } else {
                        System.out.println("Quadro não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void gerenciarListas(Board board, ProjectManager manager, Scanner sc) {
        while (true) {
            System.out.println("\n*** GERENCIAR LISTAS NO QUADRO: " + board.getName() + " ***");
            System.out.println("1. Adicionar Lista");
            System.out.println("2. Ver Listas");
            System.out.println("3. Atualizar Lista");
            System.out.println("4. Remover Lista");
            System.out.println("5. Gerenciar Cartões");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome da lista: ");
                    board.addList(new ProjectList(sc.nextLine()));
                    System.out.println("Lista adicionada.");
                    break;

                case 2:
                    for (ProjectList l : board.getLists()) {
                        System.out.println(l);
                    }
                    break;

                case 3:
                    System.out.print("Nome da lista a atualizar: ");
                    ProjectList lAtualizar = manager.findList(board, sc.nextLine());
                    if (lAtualizar != null) {
                        System.out.print("Novo nome: ");
                        lAtualizar.setName(sc.nextLine());
                        System.out.println("Lista atualizada.");
                    } else {
                        System.out.println("Lista não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Nome da lista a remover: ");
                    ProjectList lRemover = manager.findList(board, sc.nextLine());
                    if (lRemover != null) {
                        board.removeList(lRemover);
                        System.out.println("Lista removida.");
                    } else {
                        System.out.println("Lista não encontrada.");
                    }
                    break;

                case 5:
                    System.out.print("Nome da lista: ");
                    ProjectList lista = manager.findList(board, sc.nextLine());
                    if (lista != null) {
                        gerenciarCartoes(lista, board, manager, sc);
                    } else {
                        System.out.println("Lista não encontrada.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void gerenciarCartoes(ProjectList lista, Board board, ProjectManager manager, Scanner sc) {
        while (true) {
            System.out.println("\n*** GERENCIAR CARTÕES NA LISTA: " + lista.getName() + " ***");
            System.out.println("1. Adicionar Cartão");
            System.out.println("2. Ver Cartões");
            System.out.println("3. Atualizar Cartão");
            System.out.println("4. Remover Cartão");
            System.out.println("5. Mover Cartão para outra Lista");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Data de vencimento (YYYY-MM-DD): ");
                    LocalDate data = LocalDate.parse(sc.nextLine());
                    lista.addCard(new Card(titulo, descricao, data));
                    System.out.println("Cartão adicionado.");
                    break;

                case 2:
                    for (Card c : lista.getCards()) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    System.out.print("Título do cartão a atualizar: ");
                    Card cAtualizar = manager.findCard(lista, sc.nextLine());
                    if (cAtualizar != null) {
                        System.out.print("Novo título: ");
                        cAtualizar.setTitle(sc.nextLine());
                        System.out.print("Nova descrição: ");
                        cAtualizar.setDescription(sc.nextLine());
                        System.out.print("Nova data de vencimento (YYYY-MM-DD): ");
                        cAtualizar.setDueDate(LocalDate.parse(sc.nextLine()));
                        System.out.println("Cartão atualizado.");
                    } else {
                        System.out.println("Cartão não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Título do cartão a remover: ");
                    Card cRemover = manager.findCard(lista, sc.nextLine());
                    if (cRemover != null) {
                        lista.removeCard(cRemover);
                        System.out.println("Cartão removido.");
                    } else {
                        System.out.println("Cartão não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Título do cartão a mover: ");
                    Card cMover = manager.findCard(lista, sc.nextLine());
                    if (cMover != null) {
                        System.out.print("Nome da lista de destino: ");
                        ProjectList destino = manager.findList(board, sc.nextLine());
                        if (destino != null) {
                            lista.removeCard(cMover);
                            destino.addCard(cMover);
                            System.out.println("Cartão movido.");
                        } else {
                            System.out.println("Lista de destino não encontrada.");
                        }
                    } else {
                        System.out.println("Cartão não encontrado.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
