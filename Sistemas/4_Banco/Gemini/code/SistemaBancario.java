import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaBancario {
    private List<Cliente> clientes;
    private Scanner scanner;

    public SistemaBancario() {
        this.clientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.print("Digite sua escolha: ");
        int tipoConta = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        Conta conta = null;
        if (tipoConta == 1) {
            conta = new ContaCorrente();
            System.out.println("Conta Corrente criada com sucesso!");
        } else if (tipoConta == 2) {
            System.out.print("Digite a taxa de juros mensal da conta poupança (ex: 0.01 para 1%): ");
            double taxaJuros = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            conta = new ContaPoupanca(taxaJuros);
            System.out.println("Conta Poupança criada com sucesso!");
        } else {
            System.out.println("Opção de tipo de conta inválida.");
            return;
        }

        Cliente novoCliente = new Cliente(nome, cpf, conta);
        this.clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println(novoCliente);
    }

    public void consultarSaldo() {
        System.out.println("\n--- Consultar Saldo ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarCliente(cpf);
        if (cliente != null) {
            System.out.println("Saldo da conta " + cliente.getConta().getNumero() + ": R$" + String.format("%.2f", cliente.getConta().getSaldo()));
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarSaque() {
        System.out.println("\n--- Realizar Saque ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarCliente(cpf);
        if (cliente != null) {
            System.out.print("Digite o valor a sacar: R$");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            cliente.getConta().sacar(valor);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarTransferencia() {
        System.out.println("\n--- Realizar Transferência ---");
        System.out.print("Digite o CPF do cliente remetente: ");
        String cpfRemetente = scanner.nextLine();

        Cliente clienteRemetente = buscarCliente(cpfRemetente);
        if (clienteRemetente != null && clienteRemetente.getConta() instanceof ContaCorrente) {
            System.out.print("Digite o número da conta do cliente destinatário: ");
            int numeroContaDestinatario = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            Cliente clienteDestinatario = buscarClientePorNumeroConta(numeroContaDestinatario);
            if (clienteDestinatario != null) {
                System.out.print("Digite o valor a transferir: R$");
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Consumir a quebra de linha
                ((ContaCorrente) clienteRemetente.getConta()).transferir(clienteDestinatario.getConta(), valor);
            } else {
                System.out.println("Conta destinatária não encontrada.");
            }
        } else if (clienteRemetente == null) {
            System.out.println("Cliente remetente não encontrado.");
        } else {
            System.out.println("Transferências são permitidas apenas para Contas Correntes.");
        }
    }

    public void simularRendimentoPoupanca() {
        System.out.println("\n--- Simular Rendimento Poupança ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarCliente(cpf);
        if (cliente != null && cliente.getConta() instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) cliente.getConta();
            System.out.println("Saldo atual da conta poupança: R$" + String.format("%.2f", contaPoupanca.getSaldo()));
            System.out.println("Taxa de juros mensal: " + String.format("%.2f%%", contaPoupanca.getTaxaJurosMensal() * 100));
            System.out.println("Rendimento mensal simulado: R$" + String.format("%.2f", contaPoupanca.simularRendimentoMensal()));
            System.out.println("Rendimento anual simulado (juros simples): R$" + String.format("%.2f", contaPoupanca.simularRendimentoAnual()));
        } else if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("O cliente não possui uma conta poupança.");
        }
    }

    private Cliente buscarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private Cliente buscarClientePorNumeroConta(int numeroConta) {
        for (Cliente cliente : clientes) {
            if (cliente.getConta().getNumero() == numeroConta) {
                return cliente;
            }
        }
        return null;
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n--- Sistema Bancário ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Consultar Saldo");
            System.out.println("3 - Realizar Saque");
            System.out.println("4 - Realizar Transferência");
            System.out.println("5 - Simular Rendimento Poupança");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    simularRendimentoPoupanca();
                    break;
                case 0:
                    System.out.println("Saindo do sistema bancário. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        SistemaBancario sistema = new SistemaBancario();
        sistema.executar();
    }
}