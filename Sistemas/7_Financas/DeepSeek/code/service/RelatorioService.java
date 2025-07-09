package service;

import dao.TransacaoDAO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Categoria;
import model.Transacao;

public class RelatorioService {
    private TransacaoDAO transacaoDAO;

    public RelatorioService(TransacaoDAO transacaoDAO) {
        this.transacaoDAO = transacaoDAO;
    }

    public void gerarRelatorioCompleto() {
        System.out.println("===== RELATÓRIO FINANCEIRO =====");
        System.out.printf("Saldo Atual: R$ %.2f\n", transacaoDAO.calcularSaldo());
        System.out.println("\n--- Receitas por Categoria ---");
        gerarRelatorioPorCategoria("RECEITA");
        System.out.println("\n--- Despesas por Categoria ---");
        gerarRelatorioPorCategoria("DESPESA");
        System.out.println("\n==============================");
    }

    public void gerarRelatorioPorCategoria(String tipo) {
        Map<Categoria, Double> totalPorCategoria = new HashMap<>();

        for (Transacao t : transacaoDAO.listarTodasTransacoes()) {
            if (t.getTipo().equals(tipo)) {
                Categoria cat = t.getCategoria();
                double valorAtual = totalPorCategoria.getOrDefault(cat, 0.0);
                if (t.getTipo().equals("RECEITA")) {
                    totalPorCategoria.put(cat, valorAtual + t.getValor());
                } else {
                    totalPorCategoria.put(cat, valorAtual - t.getValor());
                }
            }
        }

        for (Map.Entry<Categoria, Double> entry : totalPorCategoria.entrySet()) {
            System.out.printf("%s: R$ %.2f\n", entry.getKey().getNome(), entry.getValue());
        }
    }

    public void gerarRelatorioPeriodo(Date inicio, Date fim) {
        System.out.println("===== RELATORIO POR PERIODO =====");
        System.out.printf("De %s até %s\n", inicio, fim);
        
        double totalReceitas = 0;
        double totalDespesas = 0;
        
        for (Transacao t : transacaoDAO.listarTodasTransacoes()) {
            if (!t.getData().before(inicio) && !t.getData().after(fim)) {
                if (t.getTipo().equals("RECEITA")) {
                    totalReceitas += t.getValor();
                } else {
                    totalDespesas += t.getValor();
                }
            }
        }
        
        System.out.printf("Total de Receitas: R$ %.2f\n", totalReceitas);
        System.out.printf("Total de Despesas: R$ %.2f\n", totalDespesas);
        System.out.printf("Saldo no Período: R$ %.2f\n", (totalReceitas - totalDespesas));
        System.out.println("================================");
    }
}