package dao;

import model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private List<Categoria> categorias;
    private int proximoId;

    public CategoriaDAO() {
        this.categorias = new ArrayList<>();
        this.proximoId = 1;
        // Categorias padrão
        adicionarCategoria("Salário", "RECEITA");
        adicionarCategoria("Investimentos", "RECEITA");
        adicionarCategoria("Presente", "RECEITA");
        adicionarCategoria("Alimentação", "DESPESA");
        adicionarCategoria("Moradia", "DESPESA");
        adicionarCategoria("Transporte", "DESPESA");
        adicionarCategoria("Lazer", "DESPESA");
        adicionarCategoria("Saúde", "DESPESA");
    }

    public void adicionarCategoria(String nome, String tipo) {
        categorias.add(new Categoria(proximoId++, nome, tipo));
    }

    public List<Categoria> listarTodasCategorias() {
        return new ArrayList<>(categorias);
    }

    public List<Categoria> listarCategoriasPorTipo(String tipo) {
        List<Categoria> resultado = new ArrayList<>();
        for (Categoria c : categorias) {
            if (c.getTipo().equals(tipo)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public Categoria buscarCategoriaPorId(int id) {
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}