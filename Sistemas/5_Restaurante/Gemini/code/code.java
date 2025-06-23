// Entidades
public class Alimento {
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    // Getters e Setters
}

public class Pedido {
    private Long id;
    private LocalDateTime dataHora;
    private String status; // Pendente, Em Preparo, Pronto, Finalizado
    private List<ItemPedido> itens;
    private double precoTotal;
    // Getters e Setters
}

public class ItemPedido {
    private Alimento alimento;
    private int quantidade;
    private double precoUnitario; // Pode ser redundante, mas útil para o recibo
    // Getters e Setters
}

public class Recibo {
    private Long id;
    private Pedido pedido;
    private LocalDateTime dataHoraPagamento;
    private double valorTotalPago;
    private List<ItemPedido> itens; // Detalhes dos itens no recibo
    // Getters e Setters
}

// Serviços (Lógica de Negócios)
public interface PedidoService {
    Pedido criarPedido(List<ItemPedido> itens);
    Pedido buscarPedido(Long id);
    List<Pedido> listarPedidos();
    void atualizarStatusPedido(Long id, String novoStatus);
    double calcularPrecoTotal(Pedido pedido);
    Recibo gerarRecibo(Pedido pedido, double valorPago);
}

public interface AlimentoService {
    Alimento cadastrarAlimento(String nome, double preco, String descricao);
    Alimento buscarAlimento(Long id);
    List<Alimento> listarAlimentos();
    void atualizarAlimento(Alimento alimento);
    void removerAlimento(Long id);
}

// Interface de Comunicação com a Cozinha (exemplo com interface)
public interface CozinhaIntegrationService {
    void enviarPedidoParaCozinha(Pedido pedido);
    void receberAtualizacaoStatus(Long pedidoId, String novoStatus);
}