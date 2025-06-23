import java.time.LocalDate;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LivroRepository livroRepository = new LivroRepositoryEmMemoria();
        UsuarioRepository usuarioRepository = new UsuarioRepositoryEmMemoria();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepositoryEmMemoria();

        LivroService livroService = new LivroService(livroRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, livroService, usuarioService);

        int opcao;

        do {
            System.out.println("\n--- Sistema de Gerenciamento de Biblioteca ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Deletar Livro");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Finalizar Empréstimo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    livroService.cadastrarLivro(titulo, autor, isbn, quantidade);
                    break;
                case 2:
                    System.out.print("ISBN do livro a deletar: ");
                    String isbnDeletar = scanner.nextLine();
                    livroService.deletarLivro(isbnDeletar);
                    break;
                case 3:
                    System.out.print("Nome do usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("CPF do usuário: ");
                    String cpfUsuario = scanner.nextLine();
                    System.out.print("Endereço do usuário: ");
                    String enderecoUsuario = scanner.nextLine();
                    System.out.print("Telefone do usuário: ");
                    String telefoneUsuario = scanner.nextLine();
                    usuarioService.cadastrarUsuario(nomeUsuario, cpfUsuario, enderecoUsuario, telefoneUsuario);
                    break;
                case 4:
                    System.out.print("ISBN do livro para empréstimo: ");
                    String isbnEmprestimo = scanner.nextLine();
                    System.out.print("ID do usuário: ");
                    int usuarioIdEmprestimo = scanner.nextInt();
                    System.out.print("Data de devolução prevista (AAAA-MM-DD): ");
                    String dataDevolucaoStr = scanner.next();
                    LocalDate dataDevolucaoPrevista = LocalDate.parse(dataDevolucaoStr);
                    scanner.nextLine();
                    emprestimoService.realizarEmprestimo(isbnEmprestimo, usuarioIdEmprestimo, dataDevolucaoPrevista);
                    break;
                case 5:
                    System.out.print("ID do empréstimo a finalizar: ");
                    int emprestimoIdFinalizar = scanner.nextInt();
                    System.out.print("Data de devolução real (AAAA-MM-DD): ");
                    String dataDevolucaoRealStr = scanner.next();
                    LocalDate dataDevolucaoReal = LocalDate.parse(dataDevolucaoRealStr);
                    scanner.nextLine();
                    emprestimoService.finalizarEmprestimo(emprestimoIdFinalizar, dataDevolucaoReal);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}