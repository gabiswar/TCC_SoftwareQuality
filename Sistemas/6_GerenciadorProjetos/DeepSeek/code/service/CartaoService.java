package service;

import dao.CartaoDAO;
import model.Cartao;
import model.Lista;
import java.time.LocalDate;
import java.util.List;

public class CartaoService {
    private CartaoDAO cartaoDAO;

    public CartaoService(CartaoDAO cartaoDAO) {
        this.cartaoDAO = cartaoDAO;
    }

    public Cartao criarCartao(String titulo, String descricao, LocalDate dataVencimento, Lista lista) {
        return cartaoDAO.criarCartao(titulo, descricao, dataVencimento, lista);
    }

    public List<Cartao> listarCartoesPorLista(Lista lista) {
        return cartaoDAO.listarCartoesPorLista(lista);
    }

    public Cartao buscarCartaoPorId(int id) {
        return cartaoDAO.buscarCartaoPorId(id);
    }

    public boolean atualizarCartao(int id, String titulo, String descricao, LocalDate dataVencimento) {
        return cartaoDAO.atualizarCartao(id, titulo, descricao, dataVencimento);
    }

    public boolean moverCartao(int idCartao, Lista novaLista) {
        return cartaoDAO.moverCartao(idCartao, novaLista);
    }

    public boolean excluirCartao(int id) {
        return cartaoDAO.excluirCartao(id);
    }
}