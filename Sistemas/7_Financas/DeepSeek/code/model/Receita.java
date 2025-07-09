package model;

import java.util.Date;

public class Receita extends Transacao {
    public Receita(int id, double valor, Date data, Categoria categoria, String descricao) {
        super(id, valor, data, categoria, descricao);
    }

    @Override
    public String getTipo() {
        return "RECEITA";
    }
}