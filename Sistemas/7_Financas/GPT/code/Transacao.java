import java.time.LocalDate;

public abstract class Transacao {
    protected double valor;
    protected LocalDate data;
    protected Categoria categoria;

    public Transacao(double valor, LocalDate data, Categoria categoria) {
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public abstract String getTipo();
}
