package service;

import model.Pedido;

public class PagamentoService {
    public double calcularTotal(Pedido pedido) {
        return pedido.getTotal();
    }
    
    public String processarPagamento(Pedido pedido, double valorPago) {
        double total = calcularTotal(pedido);
        if (valorPago >= total) {
            double troco = valorPago - total;
            return "Pagamento realizado com sucesso! Troco: R$ " + troco;
        } else {
            return "Valor insuficiente. Faltam R$ " + (total - valorPago);
        }
    }
}