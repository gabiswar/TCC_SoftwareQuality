import java.util.*;

class Vaga {
    private int numero;
    private boolean disponivel;
    private Veiculo veiculoEstacionado;

    public Vaga(int numero) {
        this.numero = numero;
        this.disponivel = true;
        this.veiculoEstacionado = null;
    }

    public boolean estacionar(Veiculo veiculo) {
        if (disponivel) {
            this.veiculoEstacionado = veiculo;
            this.disponivel = false;
            return true;
        }
        return false;
    }

    public Veiculo removerVeiculo() {
        if (!disponivel) {
            Veiculo veiculo = this.veiculoEstacionado;
            this.veiculoEstacionado = null;
            this.disponivel = true;
            return veiculo;
        }
        return null;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public int getNumero() {
        return numero;
    }

    public Veiculo getVeiculoEstacionado() {
        return veiculoEstacionado;
    }
}

class Veiculo {
    private String placa;
    private String modelo;
    private String cor;

    public Veiculo(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }
}

class Estacionamento {
    private Map<Integer, Vaga> vagas;
    private int capacidade;

    public Estacionamento(int capacidadeInicial) {
        this.capacidade = 0;
        this.vagas = new HashMap<>();
        cadastrarVagas(capacidadeInicial);
    }

    public void cadastrarVagas(int quantidade) {
        for (int i = capacidade + 1; i <= capacidade + quantidade; i++) {
            vagas.put(i, new Vaga(i));
        }
        this.capacidade += quantidade;
        System.out.println(quantidade + " vagas cadastradas com sucesso.");
    }

    public void estacionarVeiculo(Veiculo veiculo) {
        for (Vaga vaga : vagas.values()) {
            if (vaga.isDisponivel()) {
                if (vaga.estacionar(veiculo)) {
                    System.out.println("Veículo placa " + veiculo.getPlaca() + " estacionado na vaga " + vaga.getNumero() + ".");
                    return;
                }
            }
        }
        System.out.println("Não há vagas disponíveis no momento.");
    }

    public void removerVeiculo(String placa) {
        for (Vaga vaga : vagas.values()) {
            Veiculo veiculo = vaga.getVeiculoEstacionado();
            if (veiculo != null && veiculo.getPlaca().equalsIgnoreCase(placa)) {
                vaga.removerVeiculo();
                System.out.println("Veículo placa " + placa + " removido da vaga " + vaga.getNumero() + ".");
                return;
            }
        }
        System.out.println("Veículo com placa " + placa + " não encontrado no estacionamento.");
    }

    public int getVagasDisponiveis() {
        int count = 0;
        for (Vaga vaga : vagas.values()) {
            if (vaga.isDisponivel()) {
                count++;
            }
        }
        return count;
    }

    public void listarVagas() {
        System.out.println("\n--- Status das Vagas ---");
        for (Vaga vaga : vagas.values()) {
            System.out.print("Vaga " + vaga.getNumero() + ": ");
            if (vaga.isDisponivel()) {
                System.out.println("Disponível");
            } else {
                System.out.println("Ocupada (Placa: " + vaga.getVeiculoEstacionado().getPlaca() + ")");
            }
        }
        System.out.println("-------------------------\n");
    }
}

public class SistemaEstacionamento {
    private Estacionamento estacionamento;
    private Scanner scanner;

    public SistemaEstacionamento() {
        scanner = new Scanner(System.in);
        System.out.print("Digite a capacidade inicial do estacionamento: ");
        int capacidadeInicial = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        estacionamento = new Estacionamento(capacidadeInicial);
    }

    public void exibirMenu() {
        System.out.println("\n--- Sistema de Gerenciamento de Estacionamento ---");
        System.out.println("1 - Estacionar Veículo");
        System.out.println("2 - Remover Veículo");
        System.out.println("3 - Verificar Vagas Disponíveis");
        System.out.println("4 - Cadastrar Mais Vagas");
        System.out.println("5 - Listar Todas as Vagas");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                estacionarVeiculo();
                break;
            case 2:
                removerVeiculo();
                break;
            case 3:
                verificarVagasDisponiveis();
                break;
            case 4:
                cadastrarVagas();
                break;
            case 5:
                estacionamento.listarVagas();
                break;
            case 0:
                System.out.println("Saindo do sistema.");
                scanner.close();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void cadastrarVagas() {
        System.out.print("Digite a quantidade de vagas a serem cadastradas: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        estacionamento.cadastrarVagas(quantidade);
    }

    private void estacionarVeiculo() {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
        System.out.print("Digite o modelo do veículo (opcional): ");
        String modelo = scanner.nextLine();
        System.out.print("Digite a cor do veículo (opcional): ");
        String cor = scanner.nextLine();
        Veiculo veiculo = new Veiculo(placa, modelo, cor);
        estacionamento.estacionarVeiculo(veiculo);
    }

    private void removerVeiculo() {
        System.out.print("Digite a placa do veículo a ser removido: ");
        String placa = scanner.nextLine();
        estacionamento.removerVeiculo(placa);
    }

    private void verificarVagasDisponiveis() {
        int vagasDisponiveis = estacionamento.getVagasDisponiveis();
        System.out.println("Número de vagas disponíveis: " + vagasDisponiveis);
    }

    public static void main(String[] args) {
        SistemaEstacionamento sistema = new SistemaEstacionamento();
        int opcao;
        do {
            sistema.exibirMenu();
            opcao = sistema.scanner.nextInt();
            sistema.scanner.nextLine(); // Consumir a quebra de linha
            sistema.processarOpcao(opcao);
        } while (opcao != 0);
    }
}