package service;

import model.Pedido;
import model.StatusPedido;

import java.util.List;
import java.util.stream.Collectors;

public class CozinhaService {
    private PedidoService pedidoService;
    
    public CozinhaService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    public List<Pedido> listarPedidosRecebidos() {
        return pedidoService.listarTodosPedidos().stream()
                .filter(p -> p.getStatus() == StatusPedido.RECEBIDO)
                .collect(Collectors.toList());
    }
    
    public List<Pedido> listarPedidosEmPreparo() {
        return pedidoService.listarTodosPedidos().stream()
                .filter(p -> p.getStatus() == StatusPedido.EM_PREPARO)
                .collect(Collectors.toList());
    }
    
    public void iniciarPreparo(int pedidoId) {
        pedidoService.atualizarStatusPedido(pedidoId, StatusPedido.EM_PREPARO);
    }
    
    public void finalizarPreparo(int pedidoId) {
        pedidoService.atualizarStatusPedido(pedidoId, StatusPedido.PRONTO);
    }
}