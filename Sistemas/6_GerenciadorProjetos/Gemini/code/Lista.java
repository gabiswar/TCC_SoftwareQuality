import java.util.ArrayList;
import java.util.List;

public class Lista {
    private String nome;
    private List<Cartao> cartoes;

    public Lista(String nome) {
        this.nome = nome;
        this.cartoes = new ArrayList<>();
    }

    // Getters e Setters para nome e cartoes

    public void adicionarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public void removerCartao(Cartao cartao) {
        this.cartoes.remove(cartao);
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }
}