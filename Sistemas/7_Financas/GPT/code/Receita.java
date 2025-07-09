import java.time.LocalDate;

public class Receita extends Transacao {
    public Receita(double valor, LocalDate data, Categoria categoria) {
        super(valor, data, categoria);
    }

    @Override
    public String getTipo() {
        return "Receita";
    }
}
