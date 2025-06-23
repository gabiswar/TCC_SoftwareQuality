package dao;

import model.Cartao;
import model.Lista;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {
    private List<Cartao> cartoes = new ArrayList<>();
    private int proximoId = 1;

    // CRUD
    public Cartao criarCartao(String titulo, String descricao, LocalDate dataVencimento, Lista lista) {
        Cartao cartao = new Cartao(proximoId++, titulo, descricao, dataVencimento, lista);
        cartoes.add(cartao);
        lista.getCartoes().add(cartao);
        return cartao;
    }

    public List<Cartao> listarCartoesPorLista(Lista lista) {
        List<Cartao> result = new ArrayList<>();
        for (Cartao cartao : cartoes) {
            if (cartao.getLista().equals(lista)) {
                result.add(cartao);
            }
        }
        return result;
    }

    public Cartao buscarCartaoPorId(int id) {
        for (Cartao cartao : cartoes) {
            if (cartao.getId() == id) {
                return cartao;
            }
        }
        return null;
    }

    public boolean atualizarCartao(int id, String titulo, String descricao, LocalDate dataVencimento) {
        Cartao cartao = buscarCartaoPorId(id);
        if (cartao != null) {
            cartao.setTitulo(titulo);
            cartao.setDescricao(descricao);
            cartao.setDataVencimento(dataVencimento);
            return true;
        }
        return false;
    }

    public boolean moverCartao(int idCartao, Lista novaLista) {
        Cartao cartao = buscarCartaoPorId(idCartao);
        if (cartao != null) {
            Lista listaAntiga = cartao.getLista();
            listaAntiga.getCartoes().remove(cartao);
            cartao.setLista(novaLista);
            novaLista.getCartoes().add(cartao);
            return true;
        }
        return false;
    }

    public boolean excluirCartao(int id) {
        Cartao cartao = buscarCartaoPorId(id);
        if (cartao != null) {
            cartao.getLista().getCartoes().remove(cartao);
            cartoes.remove(cartao);
            return true;
        }
        return false;
    }
}