// ProdutoService.java
package service;

import dao.ProdutoDAO;
import model.Produto;
import java.util.List;

public class ProdutoService {
    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void criarProduto(String nome, String descricao, double preco) {
        Produto novoProduto = new Produto(0, nome, descricao, preco);
        produtoDAO.criarProduto(novoProduto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarProdutoPorId(id);
    }

    public boolean atualizarProduto(int id, String novoNome, String novaDescricao, double novoPreco) {
        Produto produto = produtoDAO.buscarProdutoPorId(id);
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setDescricao(novaDescricao);
            produto.setPreco(novoPreco);
            return produtoDAO.atualizarProduto(produto);
        }
        return false;
    }

    public boolean excluirProduto(int id) {
        return produtoDAO.excluirProduto(id);
    }
}