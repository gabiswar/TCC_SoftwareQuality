// Movimentacao.java
package model;

import java.util.Date;

public class Movimentacao {
    private int id;
    private Produto produto;
    private Armazem armazemOrigem;
    private Armazem armazemDestino;
    private int quantidade;
    private Date data;
    private TipoMovimentacao tipo;

    public Movimentacao(int id, Produto produto, Armazem armazemOrigem, Armazem armazemDestino, 
                       int quantidade, Date data, TipoMovimentacao tipo) {
        this.id = id;
        this.produto = produto;
        this.armazemOrigem = armazemOrigem;
        this.armazemDestino = armazemDestino;
        this.quantidade = quantidade;
        this.data = data;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Armazem getArmazemOrigem() { return armazemOrigem; }
    public void setArmazemOrigem(Armazem armazemOrigem) { this.armazemOrigem = armazemOrigem; }
    public Armazem getArmazemDestino() { return armazemDestino; }
    public void setArmazemDestino(Armazem armazemDestino) { this.armazemDestino = armazemDestino; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Movimentação [ID=" + id + ", Produto=" + produto.getNome() + 
               ", Origem=" + (armazemOrigem != null ? armazemOrigem.getNome() : "N/A") +
               ", Destino=" + (armazemDestino != null ? armazemDestino.getNome() : "N/A") +
               ", Quantidade=" + quantidade + ", Data=" + data + ", Tipo=" + tipo + "]";
    }
}