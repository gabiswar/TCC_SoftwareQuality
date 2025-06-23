package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private List<ItemPedido> itens;
    private StatusPedido status;
    private Date dataHora;
    private String observacoes;
    
    public Pedido(int id) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.RECEBIDO;
        this.dataHora = new Date();
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public List<ItemPedido> getItens() {
        return itens;
    }
    
    public StatusPedido getStatus() {
        return status;
    }
    
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    
    public Date getDataHora() {
        return dataHora;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }
    
    public void removerItem(ItemPedido item) {
        itens.remove(item);
    }
    
    public double getTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "Pedido #" + id + " - " + status.getDescricao() + " - Total: R$ " + getTotal();
    }
}