import java.util.*;
import java.time.*;

// Classe Livro
class Livro {
    String titulo;
    String autor;
    String isbn;
    boolean emprestado;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.emprestado = false;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn + " | Emprestado: " + (emprestado ? "Sim" : "Não");
    }
}

// Classe Usuario
class Usuario {
    String nome;
    String cpf;

    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | CPF: " + cpf;
    }
}

// Classe Emprestimo
class Emprestimo {
    Livro livro;
    Usuario usuario;
    LocalDate dataEmprestimo;
    LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
    }

    public long calcularMulta() {
        if (dataDevolucao == null) return 0;
        long diasAtraso = ChronoUnit.DAYS.between(dataEmprestimo.plusDays(7), dataDevolucao);
        return diasAtraso > 0 ? diasAtraso * 2 : 0;
    }

    @Override
    public String toString() {
        return "Livro: " + livro.titulo + " | Usuário: " + usuario.nome + 
               " | Data Empréstimo: " + dataEmprestimo +
               " | Devolvido: " + (dataDevolucao != null ? dataDevolucao : "Não");
    }
}

// Sistema Biblioteca
public class Biblioteca {
    static Scanner sc = new Scanner(System.in);
    static List<Livro> livros = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static List<Emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: cadastrarLivro(); break;
                case 2: deletarLivro(); break;
                case 3: cadastrarUsuario(); break;
                case 4: realizarEmprestimo(); break;
                case 5: finalizarEmprestimo(); break;
                case 6: listarLivros(); break;
                case 7: listarUsuarios(); break;
                case 8: listarEmprestimos(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void menu() {
        System.out.println("\n===== Biblioteca =====");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Deletar Livro");
        System.out.println("3. Cadastrar Usuário");
        System.out.println("4. Realizar Empréstimo");
        System.out.println("5. Finalizar Empréstimo");
        System.out.println("6. Listar Livros");
        System.out.println("7. Listar Usuários");
        System.out.println("8. Listar Empréstimos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        livros.add(new Livro(titulo, autor, isbn));
        System.out.println("Livro cadastrado com sucesso.");
    }

    public static void deletarLivro() {
        System.out.print("Informe o ISBN do livro a deletar: ");
        String isbn = sc.nextLine();
        livros.removeIf(l -> l.isbn.equals(isbn));
        System.out.println("Livro removido (se existia).");
    }

    public static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        boolean existe = usuarios.stream().anyMatch(u -> u.cpf.equals(cpf));
        if (existe) {
            System.out.println("Usuário já cadastrado.");
        } else {
            usuarios.add(new Usuario(nome, cpf));
            System.out.println("Usuário cadastrado com sucesso.");
        }
    }

    public static void realizarEmprestimo() {
        System.out.print("ISBN do livro: ");
        String isbn = sc.nextLine();
        Optional<Livro> livroOpt = livros.stream().filter(l -> l.isbn.equals(isbn) && !l.emprestado).findFirst();

        if (livroOpt.isEmpty()) {
            System.out.println("Livro não encontrado ou já emprestado.");
            return;
        }

        System.out.print("CPF do usuário: ");
        String cpf = sc.nextLine();
        Optional<Usuario> usuarioOpt = usuarios.stream().filter(u -> u.cpf.equals(cpf)).findFirst();

        if (usuarioOpt.isEmpty()) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Livro livro = livroOpt.get();
        livro.emprestado = true;
        Emprestimo emprestimo = new Emprestimo(livro, usuarioOpt.get());
        emprestimos.add(emprestimo);
        System.out.println("Empréstimo realizado com sucesso.");
    }

    public static void finalizarEmprestimo() {
        System.out.print("ISBN do livro: ");
        String isbn = sc.nextLine();
        Optional<Emprestimo> empOpt = emprestimos.stream()
            .filter(e -> e.livro.isbn.equals(isbn) && e.dataDevolucao == null)
            .findFirst();

        if (empOpt.isEmpty()) {
            System.out.println("Empréstimo não encontrado.");
            return;
        }

        Emprestimo emprestimo = empOpt.get();
        emprestimo.dataDevolucao = LocalDate.now();
        emprestimo.livro.emprestado = false;
        long multa = emprestimo.calcularMulta();
        System.out.println("Devolução registrada.");
        if (multa > 0) {
            System.out.println("Multa por atraso: R$" + multa);
        } else {
            System.out.println("Devolução no prazo.");
        }
    }

    public static void listarLivros() {
        livros.forEach(System.out::println);
    }

    public static void listarUsuarios() {
        usuarios.forEach(System.out::println);
    }

    public static void listarEmprestimos() {
        emprestimos.forEach(System.out::println);
    }
}
