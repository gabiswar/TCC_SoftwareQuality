package service;

import model.Alimento;
import model.ItemPedido;
import model.Pedido;
import model.StatusPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoService {
    private Map<Integer, Pedido> pedidos;
    private Map<Integer, Alimento> cardapio;
    private int proximoId;
    
    public PedidoService() {
        this.pedidos = new HashMap<>();
        this.cardapio = new HashMap<>();
        this.proximoId = 1;
        inicializarCardapio();
    }
    
    private void inicializarCardapio() {
        adicionarAlimento(new Alimento(1, "Hambúrguer", "Pão, carne, queijo e salada", 25.90));
        adicionarAlimento(new Alimento(2, "Batata Frita", "Porção de batatas fritas", 12.50));
        adicionarAlimento(new Alimento(3, "Refrigerante", "Lata 350ml", 5.50));
        adicionarAlimento(new Alimento(4, "Pizza", "Fatia de pizza mussarela", 18.00));
    }
    
    public void adicionarAlimento(Alimento alimento) {
        cardapio.put(alimento.getId(), alimento);
    }
    
    public List<Alimento> listarCardapio() {
        return new ArrayList<>(cardapio.values());
    }
    
    public Alimento buscarAlimentoPorId(int id) {
        return cardapio.get(id);
    }
    
    public Pedido criarPedido() {
        Pedido pedido = new Pedido(proximoId++);
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }
    
    public void adicionarItemAoPedido(int pedidoId, int alimentoId, int quantidade) {
        Pedido pedido = pedidos.get(pedidoId);
        Alimento alimento = cardapio.get(alimentoId);
        
        if (pedido != null && alimento != null) {
            ItemPedido item = new ItemPedido(alimento, quantidade);
            pedido.adicionarItem(item);
        }
    }
    
    public Pedido buscarPedidoPorId(int id) {
        return pedidos.get(id);
    }
    
    public List<Pedido> listarTodosPedidos() {
        return new ArrayList<>(pedidos.values());
    }
    
    public void atualizarStatusPedido(int pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidos.get(pedidoId);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
        }
    }
}