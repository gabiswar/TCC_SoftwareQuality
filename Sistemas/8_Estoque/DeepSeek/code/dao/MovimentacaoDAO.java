// MovimentacaoDAO.java
package dao;

import model.Movimentacao;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private int proximoId = 1;

    // CRUD operations
    public void registrarMovimentacao(Movimentacao movimentacao) {
        movimentacao.setId(proximoId++);
        movimentacoes.add(movimentacao);
    }

    public List<Movimentacao> listarMovimentacoes() {
        return new ArrayList<>(movimentacoes);
    }

    public Movimentacao buscarMovimentacaoPorId(int id) {
        for (Movimentacao movimentacao : movimentacoes) {
            if (movimentacao.getId() == id) {
                return movimentacao;
            }
        }
        return null;
    }
}