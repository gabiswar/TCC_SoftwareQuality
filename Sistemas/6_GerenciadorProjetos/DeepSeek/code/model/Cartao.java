package model;

import java.time.LocalDate;

public class Cartao {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataVencimento;
    private Lista lista;

    public Cartao(int id, String titulo, String descricao, LocalDate dataVencimento, Lista lista) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.lista = lista;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Cartao [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + 
               ", dataVencimento=" + dataVencimento + "]";
    }
}