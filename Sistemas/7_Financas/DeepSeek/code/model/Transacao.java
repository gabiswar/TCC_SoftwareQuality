package model;

import java.util.Date;

public abstract class Transacao {
    private int id;
    private double valor;
    private Date data;
    private Categoria categoria;
    private String descricao;

    public Transacao(int id, double valor, Date data, Categoria categoria, String descricao) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public abstract String getTipo();
}