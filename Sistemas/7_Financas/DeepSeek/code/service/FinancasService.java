package service;

import dao.CategoriaDAO;
import dao.TransacaoDAO;
import model.Categoria;
import model.Transacao;

import java.util.Date;
import java.util.List;

public class FinancasService {
    private TransacaoDAO transacaoDAO;
    private CategoriaDAO categoriaDAO;

    public FinancasService() {
        this.transacaoDAO = new TransacaoDAO();
        this.categoriaDAO = new CategoriaDAO();
    }

    public void adicionarReceita(double valor, Date data, int categoriaId, String descricao) {
        Categoria categoria = categoriaDAO.buscarCategoriaPorId(categoriaId);
        if (categoria != null && categoria.getTipo().equals("RECEITA")) {
            transacaoDAO.adicionarReceita(valor, data, categoria, descricao);
        }
    }

    public void adicionarDespesa(double valor, Date data, int categoriaId, String descricao) {
        Categoria categoria = categoriaDAO.buscarCategoriaPorId(categoriaId);
        if (categoria != null && categoria.getTipo().equals("DESPESA")) {
            transacaoDAO.adicionarDespesa(valor, data, categoria, descricao);
        }
    }

    public List<Transacao> listarTodasTransacoes() {
        return transacaoDAO.listarTodasTransacoes();
    }

    public boolean atualizarTransacao(int id, double valor, Date data, int categoriaId, String descricao) {
        Categoria categoria = categoriaDAO.buscarCategoriaPorId(categoriaId);
        if (categoria != null) {
            return transacaoDAO.atualizarTransacao(id, valor, data, categoria, descricao);
        }
        return false;
    }

    public boolean removerTransacao(int id) {
        return transacaoDAO.removerTransacao(id);
    }

    public double calcularSaldo() {
        return transacaoDAO.calcularSaldo();
    }

    public List<Categoria> listarCategoriasReceita() {
        return categoriaDAO.listarCategoriasPorTipo("RECEITA");
    }

    public List<Categoria> listarCategoriasDespesa() {
        return categoriaDAO.listarCategoriasPorTipo("DESPESA");
    }
}