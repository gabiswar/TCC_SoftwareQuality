import java.util.*;

abstract class Conta {
    protected String numeroConta;
    protected Cliente cliente;
    protected double saldo;

    public Conta(String numeroConta, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void sacar(double valor);
    public abstract void depositar(double valor);
}

class ContaCorrente extends Conta {

    public ContaCorrente(String numeroConta, Cliente cliente) {
        super(numeroConta, cliente);
    }

    @Override
    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    public void transferir(Conta destino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para a transferência.");
        }
    }
}

class ContaPoupanca extends Conta {
    private double taxaJuros;

    public ContaPoupanca(String numeroConta, Cliente cliente, double taxaJuros) {
        super(numeroConta, cliente);
        this.taxaJuros = taxaJuros;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    public double simularRendimentoMensal() {
        return saldo * taxaJuros;
    }

    public double simularRendimentoAnual() {
        return saldo * Math.pow(1 + taxaJuros, 12) - saldo;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }
}

class Cliente {
    private String nome;
    private String cpf;
    private Conta conta;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
}

class Banco {
    private List<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarClientePorCPF(String cpf) {
        for (Cliente c : clientes) {
            if (c.getConta() != null && c.getConta().cliente.cpf.equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}

public class SistemaBancario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("6. Simular rendimento (Conta Poupança)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    Cliente cliente = new Cliente(nome, cpf);

                    System.out.print("Tipo de Conta (1-Corrente | 2-Poupança): ");
                    int tipoConta = sc.nextInt();

                    if (tipoConta == 1) {
                        ContaCorrente cc = new ContaCorrente("CC" + cpf, cliente);
                        cliente.setConta(cc);
                    } else {
                        System.out.print("Informe a taxa de juros (ex: 0.005 para 0.5% ao mês): ");
                        double taxa = sc.nextDouble();
                        ContaPoupanca cp = new ContaPoupanca("CP" + cpf, cliente, taxa);
                        cliente.setConta(cp);
                    }
                    banco.cadastrarCliente(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfConsulta = sc.nextLine();
                    Cliente clienteConsulta = banco.buscarClientePorCPF(cpfConsulta);
                    if (clienteConsulta != null) {
                        System.out.println("Saldo: R$" + clienteConsulta.getConta().getSaldo());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfDeposito = sc.nextLine();
                    Cliente clienteDeposito = banco.buscarClientePorCPF(cpfDeposito);
                    if (clienteDeposito != null) {
                        System.out.print("Valor: R$");
                        double valor = sc.nextDouble();
                        clienteDeposito.getConta().depositar(valor);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfSaque = sc.nextLine();
                    Cliente clienteSaque = banco.buscarClientePorCPF(cpfSaque);
                    if (clienteSaque != null) {
                        System.out.print("Valor: R$");
                        double valor = sc.nextDouble();
                        clienteSaque.getConta().sacar(valor);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("CPF Remetente: ");
                    String cpfRemetente = sc.nextLine();
                    Cliente clienteRemetente = banco.buscarClientePorCPF(cpfRemetente);
                    if (clienteRemetente != null && clienteRemetente.getConta() instanceof ContaCorrente) {
                        System.out.print("CPF Destinatário: ");
                        String cpfDestinatario = sc.nextLine();
                        Cliente clienteDestinatario = banco.buscarClientePorCPF(cpfDestinatario);
                        if (clienteDestinatario != null) {
                            System.out.print("Valor: R$");
                            double valor = sc.nextDouble();
                            ((ContaCorrente) clienteRemetente.getConta()).transferir(clienteDestinatario.getConta(), valor);
                        } else {
                            System.out.println("Destinatário não encontrado.");
                        }
                    } else {
                        System.out.println("Remetente não encontrado ou conta não permite transferências.");
                    }
                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfPoupanca = sc.nextLine();
                    Cliente clientePoupanca = banco.buscarClientePorCPF(cpfPoupanca);
                    if (clientePoupanca != null && clientePoupanca.getConta() instanceof ContaPoupanca) {
                        ContaPoupanca contaPoupanca = (ContaPoupanca) clientePoupanca.getConta();
                        System.out.println("Rendimento mensal: R$" + contaPoupanca.simularRendimentoMensal());
                        System.out.println("Rendimento anual: R$" + contaPoupanca.simularRendimentoAnual());
                    } else {
                        System.out.println("Conta poupança não encontrada.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }
}
