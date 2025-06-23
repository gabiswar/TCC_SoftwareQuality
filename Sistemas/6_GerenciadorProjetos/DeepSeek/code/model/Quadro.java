package model;

import java.util.ArrayList;
import java.util.List;

public class Quadro {
    private int id;
    private String nome;
    private String descricao;
    private List<Lista> listas;

    public Quadro(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.listas = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void adicionarLista(Lista lista) {
        this.listas.add(lista);
    }

    public void removerLista(Lista lista) {
        this.listas.remove(lista);
    }

    @Override
    public String toString() {
        return "Quadro [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
    }
}