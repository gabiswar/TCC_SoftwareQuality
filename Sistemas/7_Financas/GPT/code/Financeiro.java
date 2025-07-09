import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Financeiro {
    private List<Transacao> transacoes;

    public Financeiro() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao t) {
        transacoes.add(t);
    }

    public void atualizarTransacao(int index, Transacao t) {
        if (index >= 0 && index < transacoes.size()) {
            transacoes.set(index, t);
        }
    }

    public void removerTransacao(int index) {
        if (index >= 0 && index < transacoes.size()) {
            transacoes.remove(index);
        }
    }

    public double calcularSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            if (t instanceof Receita) saldo += t.getValor();
            else saldo -= t.getValor();
        }
        return saldo;
    }

    public List<Transacao> listarPorCategoria(Categoria categoria, String tipo) {
        return transacoes.stream()
                .filter(t -> t.getCategoria() == categoria && t.getTipo().equals(tipo))
                .collect(Collectors.toList());
    }

    public List<Transacao> listarPorTipo(String tipo) {
        return transacoes.stream()
                .filter(t -> t.getTipo().equals(tipo))
                .collect(Collectors.toList());
    }

    public void gerarRelatorio() {
        System.out.println("=== RELATÓRIO FINANCEIRO ===");
        for (Transacao t : transacoes) {
            System.out.printf("[%s] %.2f | %s | %s%n", t.getTipo(), t.getValor(), t.getData(), t.getCategoria());
        }
        System.out.printf("Saldo atual: %.2f%n", calcularSaldo());
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
