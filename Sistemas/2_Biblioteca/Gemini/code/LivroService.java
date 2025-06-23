import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void cadastrarLivro(String titulo, String autor, String isbn, int quantidade) {
        if (livroRepository.buscarPorIsbn(isbn) != null) {
            System.out.println("Erro: ISBN já cadastrado.");
            return;
        }
        if (quantidade < 0) {
            System.out.println("Erro: Quantidade inválida.");
            return;
        }
        Livro livro = new Livro(titulo, autor, isbn, quantidade, quantidade);
        livroRepository.cadastrar(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void deletarLivro(String isbn) {
        if (livroRepository.buscarPorIsbn(isbn) == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }
        livroRepository.deletar(isbn);
        System.out.println("Livro deletado com sucesso!");
    }

    public Livro buscarLivro(String isbn) {
        return livroRepository.buscarPorIsbn(isbn);
    }

    // Outros métodos conforme necessário
}