package model;

public enum StatusPedido {
    RECEBIDO("Recebido"),
    EM_PREPARO("Em preparo"),
    PRONTO("Pronto"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");
    
    private String descricao;
    
    StatusPedido(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}