package model;

import java.util.Date;

public class Despesa extends Transacao {
    public Despesa(int id, double valor, Date data, Categoria categoria, String descricao) {
        super(id, valor, data, categoria, descricao);
    }

    @Override
    public String getTipo() {
        return "DESPESA";
    }
}