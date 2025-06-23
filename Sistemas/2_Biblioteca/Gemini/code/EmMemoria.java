import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LivroRepositoryEmMemoria implements LivroRepository {
    private final Map<String, Livro> livros = new HashMap<>();

    @Override
    public void cadastrar(Livro livro) {
        livros.put(livro.getIsbn(), livro);
    }

    @Override
    public void deletar(String isbn) {
        livros.remove(isbn);
    }

    @Override
    public Livro buscarPorIsbn(String isbn) {
        return livros.get(isbn);
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(livros.values());
    }
}

class UsuarioRepositoryEmMemoria implements UsuarioRepository {
    private final Map<Integer, Usuario> usuarios = new HashMap<>();
    private int proximoId = 1;

    @Override
    public void cadastrar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    @Override
    public Usuario buscarPorCpf(String cpf) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorId(int id) {
        return usuarios.get(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    public int gerarProximoId() {
        return proximoId++;
    }
}

class EmprestimoRepositoryEmMemoria implements EmprestimoRepository {
    private final Map<Integer, Emprestimo> emprestimos = new HashMap<>();
    private int proximoId = 1;

    @Override
    public void emprestar(Emprestimo emprestimo) {
        emprestimos.put(emprestimo.getId(), empre