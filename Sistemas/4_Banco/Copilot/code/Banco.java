import java.util.ArrayList;
import java.util.Scanner;

abstract class Conta {
    protected String cliente;
    protected double saldo;

    public Conta(String cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public String getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void sacar(double valor);

    public abstract void depositar(double valor);
}

class ContaCorrente extends Conta {

    public ContaCorrente(String cliente) {
        super(cliente);
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public void transferir(Conta destino, double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }
}

class ContaPoupanca extends Conta {
    private double taxaJuros;

    public ContaPoupanca(String cliente, double taxaJuros) {
        super(cliente);
        this.taxaJuros = taxaJuros;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public double simularRendimentoMensal() {
        return saldo * (taxaJuros / 100);
    }

    public double simularRendimentoAnual() {
        return simularRendimentoMensal() * 12;
    }
}

public class Banco {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- Sistema Bancário ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Saldo");
            System.out.println("3. Operações (Saque, Depósito, Transferência)");
            System.out.println("4. Simular Rendimento (Conta Poupança)");
            System.out.println("5. Sair");
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
                    realizarOperacoes();
                    break;
                case 4:
                    simularRendimento();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void cadastrarCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.println("Tipo de conta: 1. Conta Corrente  2. Conta Poupança");
        int tipoConta = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (tipoConta == 1) {
            contas.add(new ContaCorrente(nome));
            System.out.println("Conta Corrente criada com sucesso!");
        } else if (tipoConta == 2) {
            System.out.print("Informe a taxa de juros (%): ");
            double taxaJuros = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            contas.add(new ContaPoupanca(nome, taxaJuros));
            System.out.println("Conta Poupança criada com sucesso!");
        } else {
            System.out.println("Tipo de conta inválido.");
        }
    }

    private static void consultarSaldo() {
        Conta conta = buscarConta();
        if (conta != null) {
            System.out.println("Saldo atual: R$ " + conta.getSaldo());
        }
    }

    private static void realizarOperacoes() {
        Conta conta = buscarConta();
        if (conta != null) {
            if (conta instanceof ContaCorrente) {
                System.out.println("1. Saque  2. Depósito  3. Transferência");
                int operacao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (operacao) {
                    case 1:
                        System.out.print("Valor do saque: ");
                        double valorSaque = scanner.nextDouble();
                        conta.sacar(valorSaque);
                        break;
                    case 2:
                        System.out.print("Valor do depósito: ");
                        double valorDeposito = scanner.nextDouble();
                        conta.depositar(valorDeposito);
                        break;
                    case 3:
                        System.out.print("Nome do cliente destino: ");
                        String nomeDestino = scanner.nextLine();
                        Conta destino = buscarContaPorNome(nomeDestino);
                        if (destino != null) {
                            System.out.print("Valor da transferência: ");
                            double valorTransferencia = scanner.nextDouble();
                            ((ContaCorrente) conta).transferir(destino, valorTransferencia);
                        } else {
                            System.out.println("Conta destino não encontrada.");
                        }
                        break;
                    default:
                        System.out.println("Operação inválida.");
                }
            } else if (conta instanceof ContaPoupanca) {
                System.out.println("1. Saque  2. Depósito");
                int operacao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (operacao) {
                    case 1:
                        System.out.print("Valor do saque: ");
                        double valorSaque = scanner.nextDouble();
                        conta.sacar(valorSaque);
                        break;
                    case 2:
                        System.out.print("Valor do depósito: ");
                        double valorDeposito = scanner.nextDouble();
                        conta.depositar(valorDeposito);
                        break;
                    default:
                        System.out.println("Operação inválida.");
                }
            }
        }
    }

    private static void simularRendimento() {
        Conta conta = buscarConta();
        if (conta instanceof ContaPoupanca) {
            ContaPoupanca poupanca = (ContaPoupanca) conta;
            System.out.println("Rendimento mensal: R$ " + poupanca.simularRendimentoMensal());
            System.out.println("Rendimento anual: R$ " + poupanca.simularRendimentoAnual());
        } else {
            System.out.println("Apenas contas poupança possuem rendimento.");
        }
    }

    private static Conta buscarConta() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        return buscarContaPorNome(nome);
    }

    private static Conta buscarContaPorNome(String nome) {
        for (Conta conta : contas) {
            if (conta.getCliente().equalsIgnoreCase(nome)) {
                return conta;
            }
        }
        System.out.println("Conta não encontrada.");
        return null;
    }
}