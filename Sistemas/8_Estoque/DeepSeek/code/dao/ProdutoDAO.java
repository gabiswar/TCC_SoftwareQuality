// ProdutoDAO.java
package dao;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    // CRUD operations
    public void criarProduto(Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public boolean atualizarProduto(Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produtoAtualizado.getId()) {
                produtos.set(i, produtoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean excluirProduto(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }
}