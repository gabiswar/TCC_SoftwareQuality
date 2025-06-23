package dao;

import model.Lista;
import model.Quadro;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO {
    private List<Lista> listas = new ArrayList<>();
    private int proximoId = 1;

    // CRUD
    public Lista criarLista(String nome, Quadro quadro) {
        Lista lista = new Lista(proximoId++, nome, quadro);
        listas.add(lista);
        quadro.getListas().add(lista);
        return lista;
    }

    public List<Lista> listarListasPorQuadro(Quadro quadro) {
        List<Lista> result = new ArrayList<>();
        for (Lista lista : listas) {
            if (lista.getQuadro().equals(quadro)) {
                result.add(lista);
            }
        }
        return result;
    }

    public Lista buscarListaPorId(int id) {
        for (Lista lista : listas) {
            if (lista.getId() == id) {
                return lista;
            }
        }
        return null;
    }

    public boolean atualizarLista(int id, String nome) {
        Lista lista = buscarListaPorId(id);
        if (lista != null) {
            lista.setNome(nome);
            return true;
        }
        return false;
    }

    public boolean excluirLista(int id) {
        Lista lista = buscarListaPorId(id);
        if (lista != null) {
            lista.getQuadro().getListas().remove(lista);
            listas.remove(lista);
            return true;
        }
        return false;
    }
}