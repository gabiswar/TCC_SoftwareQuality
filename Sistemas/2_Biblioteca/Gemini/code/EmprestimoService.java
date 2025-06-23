import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private static final BigDecimal MULTA_POR_DIA = new BigDecimal("1.00");

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public void realizarEmprestimo(String isbn, int usuarioId, LocalDate dataDevolucaoPrevista) {
        Livro livro = livroService.buscarLivro(isbn);
        Usuario usuario = usuarioService.buscarUsuario(usuarioId);

        if (livro == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }
        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }
        if (livro.getQuantidadeDisponivel() <= 0) {
            System.out.println("Erro: Livro indisponível para empréstimo.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(emprestimoRepository.gerarProximoId(), livro, usuario, LocalDate.now(), dataDevolucaoPrevista, null, BigDecimal.ZERO);
        emprestimoRepository.emprestar(emprestimo);
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    public void finalizarEmprestimo(int emprestimoId, LocalDate dataDevolucaoReal) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId);
        if (emprestimo == null) {
            System.out.println("Erro: Empréstimo não encontrado.");
            return;
        }
        if (emprestimo.getDataDevolucaoReal() != null) {
            System.out.println("Erro: Este empréstimo já foi finalizado.");
            return;
        }

        BigDecimal multa = calcularMulta(emprestimo.getDataDevolucaoPrevista(), dataDevolucaoReal);
        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
        emprestimo.setMulta(multa);
        emprestimoRepository.finalizarEmprestimo(emprestimoId, dataDevolucaoReal, multa);

        Livro livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        System.out.println("Empréstimo finalizado com sucesso. Multa: R$" + multa);
    }

    public BigDecimal calcularMulta(LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal) {
        if (dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
            return MULTA_POR_DIA.multiply(new BigDecimal(diasAtraso));
        }
        return BigDecimal.ZERO;
    }

    // Outros métodos conforme necessário
}