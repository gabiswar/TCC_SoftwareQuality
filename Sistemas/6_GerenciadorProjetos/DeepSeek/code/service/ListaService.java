package service;

import dao.ListaDAO;
import model.Lista;
import model.Quadro;
import java.util.List;

public class ListaService {
    private ListaDAO listaDAO;

    public ListaService(ListaDAO listaDAO) {
        this.listaDAO = listaDAO;
    }

    public Lista criarLista(String nome, Quadro quadro) {
        return listaDAO.criarLista(nome, quadro);
    }

    public List<Lista> listarListasPorQuadro(Quadro quadro) {
        return listaDAO.listarListasPorQuadro(quadro);
    }

    public Lista buscarListaPorId(int id) {
        return listaDAO.buscarListaPorId(id);
    }

    public boolean atualizarLista(int id, String nome) {
        return listaDAO.atualizarLista(id, nome);
    }

    public boolean excluirLista(int id) {
        return listaDAO.excluirLista(id);
    }
}