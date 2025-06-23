package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void deletarLivro(String isbn) {
        livros.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        } else {
            System.out.println("Usuário já cadastrado.");
        }
    }

    public void realizarEmprestimo(String cpf, String isbn) {
        Usuario usuario = usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
        Livro livro = livros.stream().filter(l -> l.getIsbn().equals(isbn) && l.isDisponivel()).findFirst().orElse(null);

        if (usuario != null && livro != null) {
            livro.setDisponivel(false);
            emprestimos.add(new Emprestimo(usuario, livro, LocalDate.now()));
        } else {
            System.out.println("Usuário ou livro não disponível.");
        }
    }

    public void finalizarEmprestimo(String cpf, String isbn) {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getUsuario().getCpf().equals(cpf) && e.getLivro().getIsbn().equals(isbn) && e.getDataDevolucao() == null)
                .findFirst().orElse(null);

        if (emprestimo != null) {
            emprestimo.finalizarEmprestimo(LocalDate.now());
            emprestimo.getLivro().setDisponivel(true);
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public void calcularMulta(String cpf, String isbn) {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getUsuario().getCpf().equals(cpf) && e.getLivro().getIsbn().equals(isbn))
                .findFirst().orElse(null);

        if (emprestimo != null) {
            long multa = emprestimo.calcularMulta(LocalDate.now());
            System.out.println("Multa calculada: R$ " + multa);
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }
}
