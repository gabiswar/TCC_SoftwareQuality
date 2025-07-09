// ArmazemDAO.java
package dao;

import model.Armazem;
import java.util.ArrayList;
import java.util.List;

public class ArmazemDAO {
    private List<Armazem> armazens = new ArrayList<>();
    private int proximoId = 1;

    // CRUD operations
    public void criarArmazem(Armazem armazem) {
        armazem.setId(proximoId++);
        armazens.add(armazem);
    }

    public List<Armazem> listarArmazens() {
        return new ArrayList<>(armazens);
    }

    public Armazem buscarArmazemPorId(int id) {
        for (Armazem armazem : armazens) {
            if (armazem.getId() == id) {
                return armazem;
            }
        }
        return null;
    }

    public boolean atualizarArmazem(Armazem armazemAtualizado) {
        for (int i = 0; i < armazens.size(); i++) {
            if (armazens.get(i).getId() == armazemAtualizado.getId()) {
                armazens.set(i, armazemAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean excluirArmazem(int id) {
        return armazens.removeIf(armazem -> armazem.getId() == id);
    }
}