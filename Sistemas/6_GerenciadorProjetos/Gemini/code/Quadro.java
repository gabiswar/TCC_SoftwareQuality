import java.util.ArrayList;
import java.util.List;

public class Quadro {
    private String nome;
    private String descricao;
    private List<Lista> listas;

    public Quadro(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.listas = new ArrayList<>();
    }

    // Getters e Setters para nome, descricao e listas

    public void adicionarLista(Lista lista) {
        this.listas.add(lista);
    }

    public void removerLista(Lista lista) {
        this.listas.remove(lista);
    }

    public List<Lista> getListas() {
        return listas;
    }
}