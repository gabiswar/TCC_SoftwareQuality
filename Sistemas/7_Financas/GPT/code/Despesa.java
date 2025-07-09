import java.time.LocalDate;

public class Despesa extends Transacao {
    public Despesa(double valor, LocalDate data, Categoria categoria) {
        super(valor, data, categoria);
    }

    @Override
    public String getTipo() {
        return "Despesa";
    }
}
