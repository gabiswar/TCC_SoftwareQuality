package dao;

import model.Transacao;
import model.Receita;
import model.Despesa;
import model.Categoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransacaoDAO {
    private List<Transacao> transacoes;
    private int proximoId;

    public TransacaoDAO() {
        this.transacoes = new ArrayList<>();
        this.proximoId = 1;
    }

    public void adicionarReceita(double valor, Date data, Categoria categoria, String descricao) {
        Receita receita = new Receita(proximoId++, valor, data, categoria, descricao);
        transacoes.add(receita);
    }

    public void adicionarDespesa(double valor, Date data, Categoria categoria, String descricao) {
        Despesa despesa = new Despesa(proximoId++, valor, data, categoria, descricao);
        transacoes.add(despesa);
    }

    public List<Transacao> listarTodasTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public List<Receita> listarReceitas() {
        List<Receita> receitas = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t instanceof Receita) {
                receitas.add((Receita) t);
            }
        }
        return receitas;
    }

    public List<Despesa> listarDespesas() {
        List<Despesa> despesas = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t instanceof Despesa) {
                despesas.add((Despesa) t);
            }
        }
        return despesas;
    }

    public Transacao buscarPorId(int id) {
        for (Transacao t : transacoes) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public boolean atualizarTransacao(int id, double valor, Date data, Categoria categoria, String descricao) {
        Transacao t = buscarPorId(id);
        if (t != null) {
            t.setValor(valor);
            t.setData(data);
            t.setCategoria(categoria);
            t.setDescricao(descricao);
            return true;
        }
        return false;
    }

    public boolean removerTransacao(int id) {
        Transacao t = buscarPorId(id);
        if (t != null) {
            transacoes.remove(t);
            return true;
        }
        return false;
    }

    public double calcularSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            if (t instanceof Receita) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }
}