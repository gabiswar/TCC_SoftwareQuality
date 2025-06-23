import java.time.LocalDate;

public class Cartao {
    private String titulo;
    private String descricao;
    private LocalDate dataVencimento;

    public Cartao(String titulo, String descricao, LocalDate dataVencimento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
    }

    // Getters e Setters para titulo, descricao e dataVencimento
}