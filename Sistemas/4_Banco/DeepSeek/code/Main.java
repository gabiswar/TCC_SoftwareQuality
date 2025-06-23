import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe abstrata para representar uma conta bancária
abstract class Conta {
    protected String numeroConta;
    protected String titular;
    protected double saldo;
    
    public Conta(String numeroConta, String titular) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = 0.0;
    }
    
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }
    
    public abstract void sacar(double valor);
    
    public void consultarSaldo() {
        System.out.println("Saldo atual da conta " + numeroConta + ": R$" + saldo);
    }
    
    public String getNumeroConta() {
        return numeroConta;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public abstract String getTipoConta();
}

// Classe para conta corrente
class ContaCorrente extends Conta {
    public ContaCorrente(String numeroConta, String titular) {
        super(numeroConta, titular);
    }
    
    @Override
    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }
    
    public void transferir(Conta destino, double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " para conta " + 
                               destino.getNumeroConta() + " realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para transferência.");
        }
    }
    
    @Override
    public String getTipoConta() {
        return "Corrente";
    }
}

// Classe para conta poupança
class ContaPoupanca extends Conta {
    private double taxaJuros;
    
    public ContaPoupanca(String numeroConta, String titular, double taxaJuros) {
        super(numeroConta, titular);
        this.taxaJuros = taxaJuros;
    }
    
    @Override
    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }
    
    public void aplicarRendimentoMensal() {
        double rendimento = saldo * taxaJuros / 100;
        saldo += rendimento;
        System.out.println("Rendimento mensal de R$" + rendimento + " aplicado.");
    }
    
    public void simularRendimento(int meses) {
        double saldoSimulado = saldo;
        System.out.println("\nSimulação de rendimento para " + meses + " meses:");
        System.out.println("Saldo inicial: R$" + saldoSimulado);
        System.out.println("Taxa de juros mensal: " + taxaJuros + "%");
        
        for (int i = 1; i <= meses; i++) {
            double rendimento = saldoSimulado * taxaJuros / 100;
            saldoSimulado += rendimento;
            System.out.printf("Mês %d: R$%.2f (rendimento: R$%.2f)\n", i, saldoSimulado, rendimento);
        }
        
        System.out.printf("Saldo final após %d meses: R$%.2f\n", meses, saldoSimulado);
    }
    
    @Override
    public String getTipoConta() {
        return "Poupança";
    }
    
    public double getTaxaJuros() {
        return taxaJuros;
    }
}

// Classe para representar um cliente
class Cliente {
    private String cpf;
    private String nome;
    private List<Conta> contas;
    
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.contas = new ArrayList<>();
    }
    
    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }
    
    public List<Conta> getContas() {
        return contas;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public String getNome() {
        return nome;
    }
}

// Classe principal do sistema bancário
class SistemaBancario {
    private List<Cliente> clientes;
    private Scanner scanner;
    
    public SistemaBancario() {
        this.clientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        int opcao;
        
        do {
            System.out.println("\n=== SISTEMA BANCÁRIO ===");
            System.out.println("1. Cadastrar novo cliente");
            System.out.println("2. Cadastrar nova conta");
            System.out.println("3. Realizar depósito");
            System.out.println("4. Realizar saque");
            System.out.println("5. Realizar transferência (apenas corrente)");
            System.out.println("6. Aplicar rendimento mensal (poupança)");
            System.out.println("7. Simular rendimento (poupança)");
            System.out.println("8. Consultar saldo");
            System.out.println("9. Listar clientes e contas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarConta();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    realizarSaque();
                    break;
                case 5:
                    realizarTransferencia();
                    break;
                case 6:
                    aplicarRendimento();
                    break;
                case 7:
                    simularRendimento();
                    break;
                case 8:
                    consultarSaldo();
                    break;
                case 9:
                    listarClientesContas();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        clientes.add(new Cliente(cpf, nome));
        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    private void cadastrarConta() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        
        System.out.println("\n--- CADASTRO DE CONTA ---");
        System.out.println("Clientes disponíveis:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome() + " (CPF: " + clientes.get(i).getCpf() + ")");
        }
        
        System.out.print("Escolha o cliente (número): ");
        int escolhaCliente = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpar buffer
        
        if (escolhaCliente < 0 || escolhaCliente >= clientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }
        
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        System.out.println("Tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.print("Escolha: ");
        int tipoConta = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        Cliente cliente = clientes.get(escolhaCliente);
        Conta novaConta;
        
        switch (tipoConta) {
            case 1:
                novaConta = new ContaCorrente(numeroConta, cliente.getNome());
                cliente.adicionarConta(novaConta);
                System.out.println("Conta corrente criada com sucesso!");
                break;
            case 2:
                System.out.print("Taxa de juros mensal (%): ");
                double taxaJuros = scanner.nextDouble();
                scanner.nextLine(); // Limpar buffer
                novaConta = new ContaPoupanca(numeroConta, cliente.getNome(), taxaJuros);
                cliente.adicionarConta(novaConta);
                System.out.println("Conta poupança criada com sucesso!");
                break;
            default:
                System.out.println("Tipo de conta inválido!");
        }
    }
    
    private Conta encontrarConta(String numeroConta) {
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                if (conta.getNumeroConta().equals(numeroConta)) {
                    return conta;
                }
            }
        }
        return null;
    }
    
    private void realizarDeposito() {
        System.out.println("\n--- DEPÓSITO ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        Conta conta = encontrarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }
        
        System.out.print("Valor do depósito: R$");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        conta.depositar(valor);
    }
    
    private void realizarSaque() {
        System.out.println("\n--- SAQUE ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        Conta conta = encontrarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }
        
        System.out.print("Valor do saque: R$");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        conta.sacar(valor);
    }
    
    private void realizarTransferencia() {
        System.out.println("\n--- TRANSFERÊNCIA ---");
        System.out.print("Número da conta de origem: ");
        String numeroOrigem = scanner.nextLine();
        
        Conta origem = encontrarConta(numeroOrigem);
        if (origem == null) {
            System.out.println("Conta de origem não encontrada!");
            return;
        }
        
        if (!(origem instanceof ContaCorrente)) {
            System.out.println("Apenas contas correntes podem realizar transferências!");
            return;
        }
        
        System.out.print("Número da conta de destino: ");
        String numeroDestino = scanner.nextLine();
        
        Conta destino = encontrarConta(numeroDestino);
        if (destino == null) {
            System.out.println("Conta de destino não encontrada!");
            return;
        }
        
        System.out.print("Valor da transferência: R$");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        ((ContaCorrente) origem).transferir(destino, valor);
    }
    
    private void aplicarRendimento() {
        System.out.println("\n--- APLICAR RENDIMENTO MENSAL ---");
        System.out.print("Número da conta poupança: ");
        String numeroConta = scanner.nextLine();
        
        Conta conta = encontrarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }
        
        if (!(conta instanceof ContaPoupanca)) {
            System.out.println("Apenas contas poupança têm rendimento!");
            return;
        }
        
        ((ContaPoupanca) conta).aplicarRendimentoMensal();
    }
    
    private void simularRendimento() {
        System.out.println("\n--- SIMULAR RENDIMENTO ---");
        System.out.print("Número da conta poupança: ");
        String numeroConta = scanner.nextLine();
        
        Conta conta = encontrarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }
        
        if (!(conta instanceof ContaPoupanca)) {
            System.out.println("Apenas contas poupança têm rendimento!");
            return;
        }
        
        System.out.println("1. Simular rendimento mensal");
        System.out.println("2. Simular rendimento anual (12 meses)");
        System.out.println("3. Simular para período personalizado");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        switch (opcao) {
            case 1:
                ((ContaPoupanca) conta).simularRendimento(1);
                break;
            case 2:
                ((ContaPoupanca) conta).simularRendimento(12);
                break;
            case 3:
                System.out.print("Quantidade de meses para simular: ");
                int meses = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                ((ContaPoupanca) conta).simularRendimento(meses);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private void consultarSaldo() {
        System.out.println("\n--- CONSULTAR SALDO ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        Conta conta = encontrarConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }
        
        conta.consultarSaldo();
    }
    
    private void listarClientesContas() {
        System.out.println("\n--- CLIENTES E CONTAS ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        for (Cliente cliente : clientes) {
            System.out.println("\nCliente: " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
            System.out.println("Contas:");
            
            if (cliente.getContas().isEmpty()) {
                System.out.println("  Nenhuma conta cadastrada.");
            } else {
                for (Conta conta : cliente.getContas()) {
                    System.out.printf("  %s: %s (Saldo: R$%.2f)\n", 
                                      conta.getTipoConta(), 
                                      conta.getNumeroConta(), 
                                      conta.getSaldo());
                    
                    if (conta instanceof ContaPoupanca) {
                        System.out.printf("    Taxa de juros: %.2f%%\n", 
                                        ((ContaPoupanca) conta).getTaxaJuros());
                    }
                }
            }
        }
    }
}

// Classe principal para executar o sistema
public class Main {
    public static void main(String[] args) {
        SistemaBancario sistema = new SistemaBancario();
        sistema.iniciar();
    }
}