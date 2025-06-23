package util;

import model.ItemPedido;
import model.Pedido;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradorRecibo {
    public static String gerarRecibo(Pedido pedido) {
        StringBuilder recibo = new StringBuilder();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        recibo.append("================================\n");
        recibo.append("          RESTAURANTE           \n");
        recibo.append("================================\n");
        recibo.append("Pedido #").append(pedido.getId()).append("\n");
        recibo.append("Data: ").append(sdf.format(pedido.getDataHora())).append("\n");
        recibo.append("--------------------------------\n");
        recibo.append("ITENS:\n");
        
        for (ItemPedido item : pedido.getItens()) {
            recibo.append("- ").append(item.toString()).append("\n");
        }
        
        recibo.append("--------------------------------\n");
        recibo.append("TOTAL: R$ ").append(String.format("%.2f", pedido.getTotal())).append("\n");
        recibo.append("================================\n");
        
        return recibo.toString();
    }
}