package model;

public class ItemPedido {
    private Alimento alimento;
    private int quantidade;
    
    public ItemPedido(Alimento alimento, int quantidade) {
        this.alimento = alimento;
        this.quantidade = quantidade;
    }
    
    // Getters e Setters
    public Alimento getAlimento() {
        return alimento;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getSubtotal() {
        return alimento.getPreco() * quantidade;
    }
    
    @Override
    public String toString() {
        return alimento.getNome() + " x " + quantidade + " = R$ " + getSubtotal();
    }
}