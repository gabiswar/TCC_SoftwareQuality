package model;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    private int id;
    private String nome;
    private Quadro quadro;
    private List<Cartao> cartoes;

    public Lista(int id, String nome, Quadro quadro) {
        this.id = id;
        this.nome = nome;
        this.quadro = quadro;
        this.cartoes = new ArrayList<>();
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

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void adicionarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public void removerCartao(Cartao cartao) {
        this.cartoes.remove(cartao);
    }

    @Override
    public String toString() {
        return "Lista [id=" + id + ", nome=" + nome + "]";
    }
}