import java.util.*;

// Produto
class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
}

// Item de Pedido
class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return produto.getPreco() * quantidade;
    }

    public String toString() {
        return quantidade + "x " + produto.getNome() + " - R$ " + produto.getPreco() + " cada";
    }
}

// Pedido
class Pedido {
    private static int contador = 1;
    private int numero;
    private List<ItemPedido> itens;
    private String status;

    public Pedido() {
        this.numero = contador++;
        this.itens = new ArrayList<>();
        this.status = "Recebido";
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getTotal();
        }
        return total;
    }

    public int getNumero() { return numero; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<ItemPedido> getItens() { return itens; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido Nº ").append(numero).append(" - Status: ").append(status).append("\n");
        for (ItemPedido item : itens) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("Total: R$ ").append(calcularTotal()).append("\n");
        return sb.toString();
    }
}

// Cozinha
class Cozinha {
    public void receberPedido(Pedido pedido) {
        System.out.println("Cozinha recebeu o pedido Nº " + pedido.getNumero());
        pedido.setStatus("Em preparo");
    }

    public void pedidoPronto(Pedido pedido) {
        pedido.setStatus("Pronto");
        System.out.println("Pedido Nº " + pedido.getNumero() + " está pronto!");
    }
}

// Pagamento
class Pagamento {
    public void realizarPagamento(Pedido pedido) {
        System.out.println("Pagamento realizado: R$ " + pedido.calcularTotal());
        pedido.setStatus("Pago");
    }
}

// Recibo
class Recibo {
    public void gerarRecibo(Pedido pedido) {
        System.out.println("===== RECIBO =====");
        System.out.println(pedido);
        System.out.println("Obrigado pela preferência!");
    }
}

// Sistema principal
public class RestauranteApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Produto> cardapio = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        Cozinha cozinha = new Cozinha();
        Pagamento pagamento = new Pagamento();
        Recibo recibo = new Recibo();

        while (true) {
            System.out.println("\n=== Sistema de Restaurante ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Criar pedido");
            System.out.println("3. Ver pedidos");
            System.out.println("4. Enviar pedido para cozinha");
            System.out.println("5. Atualizar pedido para pronto");
            System.out.println("6. Realizar pagamento");
            System.out.println("0. Sair");

            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("Nome do produto: ");
                String nome = sc.nextLine();
                System.out.print("Preço: ");
                double preco = sc.nextDouble();
                cardapio.add(new Produto(nome, preco));
                System.out.println("Produto adicionado!");

            } else if (opcao == 2) {
                Pedido pedido = new Pedido();
                System.out.println("Cardápio:");
                for (int i = 0; i < cardapio.size(); i++) {
                    Produto p = cardapio.get(i);
                    System.out.println((i+1) + ". " + p.getNome() + " - R$ " + p.getPreco());
                }

                while (true) {
                    System.out.print("Digite o nº do produto (0 para finalizar): ");
                    int numProd = sc.nextInt();
                    if (numProd == 0) break;
                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();
                    pedido.adicionarItem(new ItemPedido(cardapio.get(numProd - 1), qtd));
                }
                pedidos.add(pedido);
                System.out.println("Pedido criado: Nº " + pedido.getNumero());

            } else if (opcao == 3) {
                for (Pedido p : pedidos) {
                    System.out.println(p);
                }

            } else if (opcao == 4) {
                System.out.print("Número do pedido: ");
                int num = sc.nextInt();
                Pedido pedido = encontrarPedido(pedidos, num);
                if (pedido != null) {
                    cozinha.receberPedido(pedido);
                } else {
                    System.out.println("Pedido não encontrado.");
                }

            } else if (opcao == 5) {
                System.out.print("Número do pedido: ");
                int num = sc.nextInt();
                Pedido pedido = encontrarPedido(pedidos, num);
                if (pedido != null) {
                    cozinha.pedidoPronto(pedido);
                } else {
                    System.out.println("Pedido não encontrado.");
                }

            } else if (opcao == 6) {
                System.out.print("Número do pedido: ");
                int num = sc.nextInt();
                Pedido pedido = encontrarPedido(pedidos, num);
                if (pedido != null) {
                    pagamento.realizarPagamento(pedido);
                    recibo.gerarRecibo(pedido);
                } else {
                    System.out.println("Pedido não encontrado.");
                }

            } else if (opcao == 0) {
                break;

            } else {
                System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }

    private static Pedido encontrarPedido(List<Pedido> pedidos, int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumero() == numero) return p;
        }
        return null;
    }
}
