package dao;

import model.Quadro;
import java.util.ArrayList;
import java.util.List;

public class QuadroDAO {
    private List<Quadro> quadros = new ArrayList<>();
    private int proximoId = 1;

    // CRUD
    public Quadro criarQuadro(String nome, String descricao) {
        Quadro quadro = new Quadro(proximoId++, nome, descricao);
        quadros.add(quadro);
        return quadro;
    }

    public List<Quadro> listarQuadros() {
        return new ArrayList<>(quadros);
    }

    public Quadro buscarQuadroPorId(int id) {
        for (Quadro quadro : quadros) {
            if (quadro.getId() == id) {
                return quadro;
            }
        }
        return null;
    }

    public boolean atualizarQuadro(int id, String nome, String descricao) {
        Quadro quadro = buscarQuadroPorId(id);
        if (quadro != null) {
            quadro.setNome(nome);
            quadro.setDescricao(descricao);
            return true;
        }
        return false;
    }

    public boolean excluirQuadro(int id) {
        Quadro quadro = buscarQuadroPorId(id);
        if (quadro != null) {
            quadros.remove(quadro);
            return true;
        }
        return false;
    }
}