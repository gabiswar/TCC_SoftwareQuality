import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SistemaBiblioteca {
    private static List<Livro> livros = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Deletar Livro");
            System.out.println("4. Cadastrar Usuário");
            System.out.println("5. Listar Usuários");
            System.out.println("6. Realizar Empréstimo");
            System.out.println("7. Finalizar Empréstimo");
            System.out.println("8. Listar Empréstimos Ativos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    deletarLivro();
                    break;
                case 4:
                    cadastrarUsuario();
                    break;
                case 5:
                    listarUsuarios();
                    break;
                case 6:
                    realizarEmprestimo();
                    break;
                case 7:
                    finalizarEmprestimo();
                    break;
                case 8:
                    listarEmprestimosAtivos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro() {
        System.out.println("\n=== CADASTRO DE LIVRO ===");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        System.out.print("Ano de Publicação: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Quantidade de Exemplares: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        
        Livro livro = new Livro(isbn, titulo, autor, anoPublicacao, quantidade);
        livros.add(livro);
        
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void listarLivros() {
        System.out.println("\n=== LISTA DE LIVROS ===");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static void deletarLivro() {
        System.out.println("\n=== DELETAR LIVRO ===");
        System.out.print("Digite o ISBN do livro a ser deletado: ");
        String isbn = scanner.nextLine();
        
        boolean removido = false;
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getIsbn().equals(isbn)) {
                // Verificar se há exemplares emprestados
                if (livros.get(i).getQuantidadeDisponivel() < livros.get(i).getQuantidadeTotal()) {
                    System.out.println("Não é possível deletar o livro. Há exemplares emprestados.");
                    return;
                }
                livros.remove(i);
                removido = true;
                break;
            }
        }
        
        if (removido) {
            System.out.println("Livro deletado com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        // Verificar se usuário já existe
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                System.out.println("Usuário já cadastrado com este CPF!");
                return;
            }
        }
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        Usuario usuario = new Usuario(cpf, nome, email, telefone);
        usuarios.add(usuario);
        
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    private static void realizarEmprestimo() {
        System.out.println("\n=== REALIZAR EMPRÉSTIMO ===");
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();
        
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                usuario = u;
                break;
            }
        }
        
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();
        
        Livro livro = null;
        for (Livro l : livros) {
            if (l.getIsbn().equals(isbn)) {
                livro = l;
                break;
            }
        }
        
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }
        
        if (livro.getQuantidadeDisponivel() <= 0) {
            System.out.println("Não há exemplares disponíveis deste livro!");
            return;
        }
        
        // Verificar se usuário já tem este livro emprestado
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getCpf().equals(cpf) && e.getLivro().getIsbn().equals(isbn) && !e.isFinalizado()) {
                System.out.println("Usuário já possui um exemplar deste livro emprestado!");
                return;
            }
        }
        
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(14); // Prazo de 14 dias
        
        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucaoPrevista);
        emprestimos.add(emprestimo);
        livro.emprestar();
        
        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("Data de devolução prevista: " + dataDevolucaoPrevista);
    }

    private static void finalizarEmprestimo() {
        System.out.println("\n=== FINALIZAR EMPRÉSTIMO ===");
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();
        
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();
        
        Emprestimo emprestimo = null;
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getCpf().equals(cpf) && 
                e.getLivro().getIsbn().equals(isbn) && 
                !e.isFinalizado()) {
                emprestimo = e;
                break;
            }
        }
        
        if (emprestimo == null) {
            System.out.println("Empréstimo ativo não encontrado!");
            return;
        }
        
        LocalDate dataDevolucao = LocalDate.now();
        emprestimo.finalizarEmprestimo(dataDevolucao);
        emprestimo.getLivro().devolver();
        
        // Calcular multa se houver atraso
        if (dataDevolucao.isAfter(emprestimo.getDataDevolucaoPrevista())) {
            Period periodo = Period.between(emprestimo.getDataDevolucaoPrevista(), dataDevolucao);
            int diasAtraso = periodo.getDays();
            double multa = diasAtraso * 2.50; // R$ 2,50 por dia de atraso
            
            System.out.println("Devolução com atraso de " + diasAtraso + " dias.");
            System.out.printf("Multa a ser paga: R$ %.2f\n", multa);
        } else {
            System.out.println("Devolução realizada dentro do prazo.");
        }
        
        System.out.println("Empréstimo finalizado com sucesso!");
    }

    private static void listarEmprestimosAtivos() {
        System.out.println("\n=== EMPRÉSTIMOS ATIVOS ===");
        boolean encontrado = false;
        
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isFinalizado()) {
                System.out.println(emprestimo);
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        }
    }
}

class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(String isbn, String titulo, String autor, int anoPublicacao, int quantidadeTotal) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeTotal;
    }

    public void emprestar() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        }
    }

    public void devolver() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
    }

    // Getters
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public int getQuantidadeTotal() { return quantidadeTotal; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Ano: %d | Disponíveis: %d/%d",
                isbn, titulo, autor, anoPublicacao, quantidadeDisponivel, quantidadeTotal);
    }
}

class Usuario {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;

    public Usuario(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    @Override
    public String toString() {
        return String.format("CPF: %s | Nome: %s | Email: %s | Telefone: %s",
                cpf, nome, email, telefone);
    }
}

class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private boolean finalizado;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.finalizado = false;
    }

    public void finalizarEmprestimo(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.finalizado = true;
    }

    // Getters
    public Usuario getUsuario() { return usuario; }
    public Livro getLivro() { return livro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public LocalDate getDataDevolucaoReal() { return dataDevolucaoReal; }
    public boolean isFinalizado() { return finalizado; }

    @Override
    public String toString() {
        return String.format("Usuário: %s | Livro: %s | Empréstimo: %s | Devolução Prevista: %s | Status: %s",
                usuario.getNome(), livro.getTitulo(), dataEmprestimo, dataDevolucaoPrevista,
                finalizado ? "Finalizado" : "Ativo");
    }
}