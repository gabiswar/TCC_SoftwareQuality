// EstoqueService.java
package service;

import dao.ArmazemDAO;
import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import model.Movimentacao;
import model.TipoMovimentacao;
import java.util.Date;
import java.util.List;

public class EstoqueService {
    private MovimentacaoDAO movimentacaoDAO;
    private ArmazemDAO armazemDAO;
    private ProdutoDAO produtoDAO;

    public EstoqueService(MovimentacaoDAO movimentacaoDAO, ArmazemDAO armazemDAO, ProdutoDAO produtoDAO) {
        this.movimentacaoDAO = movimentacaoDAO;
        this.armazemDAO = armazemDAO;
        this.produtoDAO = produtoDAO;
    }

    public void registrarEntrada(int produtoId, int armazemId, int quantidade, Date data) {
        Movimentacao entrada = new Movimentacao(
            0,
            produtoDAO.buscarProdutoPorId(produtoId),
            null,
            armazemDAO.buscarArmazemPorId(armazemId),
            quantidade,
            data,
            TipoMovimentacao.ENTRADA
        );
        movimentacaoDAO.registrarMovimentacao(entrada);
    }

    public void registrarSaida(int produtoId, int armazemId, int quantidade, Date data) {
        Movimentacao saida = new Movimentacao(
            0,
            produtoDAO.buscarProdutoPorId(produtoId),
            armazemDAO.buscarArmazemPorId(armazemId),
            null,
            quantidade,
            data,
            TipoMovimentacao.SAIDA
        );
        movimentacaoDAO.registrarMovimentacao(saida);
    }

    public void transferirProduto(int produtoId, int armazemOrigemId, int armazemDestinoId, int quantidade, Date data) {
        Movimentacao transferencia = new Movimentacao(
            0,
            produtoDAO.buscarProdutoPorId(produtoId),
            armazemDAO.buscarArmazemPorId(armazemOrigemId),
            armazemDAO.buscarArmazemPorId(armazemDestinoId),
            quantidade,
            data,
            TipoMovimentacao.TRANSFERENCIA
        );
        movimentacaoDAO.registrarMovimentacao(transferencia);
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoDAO.listarMovimentacoes();
    }

    public int calcularEstoqueDisponivel(int produtoId, int armazemId) {
        int estoque = 0;
        for (Movimentacao mov : movimentacaoDAO.listarMovimentacoes()) {
            if (mov.getProduto().getId() == produtoId) {
                if (mov.getTipo() == TipoMovimentacao.ENTRADA && 
                    mov.getArmazemDestino().getId() == armazemId) {
                    estoque += mov.getQuantidade();
                } else if (mov.getTipo() == TipoMovimentacao.SAIDA && 
                           mov.getArmazemOrigem().getId() == armazemId) {
                    estoque -= mov.getQuantidade();
                } else if (mov.getTipo() == TipoMovimentacao.TRANSFERENCIA) {
                    if (mov.getArmazemOrigem().getId() == armazemId) {
                        estoque -= mov.getQuantidade();
                    } else if (mov.getArmazemDestino().getId() == armazemId) {
                        estoque += mov.getQuantidade();
                    }
                }
            }
        }
        return estoque;
    }
}