// ArmazemService.java
package service;

import dao.ArmazemDAO;
import model.Armazem;
import java.util.List;

public class ArmazemService {
    private ArmazemDAO armazemDAO;

    public ArmazemService(ArmazemDAO armazemDAO) {
        this.armazemDAO = armazemDAO;
    }

    public void criarArmazem(String nome, String localizacao) {
        Armazem novoArmazem = new Armazem(0, nome, localizacao);
        armazemDAO.criarArmazem(novoArmazem);
    }

    public List<Armazem> listarArmazens() {
        return armazemDAO.listarArmazens();
    }

    public Armazem buscarArmazemPorId(int id) {
        return armazemDAO.buscarArmazemPorId(id);
    }

    public boolean atualizarArmazem(int id, String novoNome, String novaLocalizacao) {
        Armazem armazem = armazemDAO.buscarArmazemPorId(id);
        if (armazem != null) {
            armazem.setNome(novoNome);
            armazem.setLocalizacao(novaLocalizacao);
            return armazemDAO.atualizarArmazem(armazem);
        }
        return false;
    }

    public boolean excluirArmazem(int id) {
        return armazemDAO.excluirArmazem(id);
    }
}