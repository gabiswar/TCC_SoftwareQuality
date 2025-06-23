public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(String nome, String cpf, String endereco, String telefone) {
        if (usuarioRepository.buscarPorCpf(cpf) != null) {
            System.out.println("Erro: CPF já cadastrado.");
            return;
        }
        Usuario usuario = new Usuario(usuarioRepository.gerarProximoId(), nome, cpf, endereco, telefone);
        usuarioRepository.cadastrar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public Usuario buscarUsuario(String cpf) {
        return usuarioRepository.buscarPorCpf(cpf);
    }

    // Outros métodos conforme necessário
}