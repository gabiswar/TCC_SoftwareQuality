package src;

import java.time.LocalDate;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void finalizarEmprestimo(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public long calcularMulta(LocalDate dataAtual) {
        if (dataDevolucao == null) {
            long diasAtraso = dataAtual.toEpochDay() - dataEmprestimo.plusDays(14).toEpochDay();
            return diasAtraso > 0 ? diasAtraso * 2 : 0;
        }
        return 0;
    }
}
