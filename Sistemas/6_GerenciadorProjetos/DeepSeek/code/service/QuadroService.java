package service;

import dao.QuadroDAO;
import model.Quadro;
import java.util.List;

public class QuadroService {
    private QuadroDAO quadroDAO;

    public QuadroService(QuadroDAO quadroDAO) {
        this.quadroDAO = quadroDAO;
    }

    public Quadro criarQuadro(String nome, String descricao) {
        return quadroDAO.criarQuadro(nome, descricao);
    }

    public List<Quadro> listarQuadros() {
        return quadroDAO.listarQuadros();
    }

    public Quadro buscarQuadroPorId(int id) {
        return quadroDAO.buscarQuadroPorId(id);
    }

    public boolean atualizarQuadro(int id, String nome, String descricao) {
        return quadroDAO.atualizarQuadro(id, nome, descricao);
    }

    public boolean excluirQuadro(int id) {
        return quadroDAO.excluirQuadro(id);
    }
}