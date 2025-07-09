import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaEstacionamento {
    private int totalVagas;
    private int vagasDisponiveis;
    private Map<String, Veiculo> veiculosEstacionados;
    private double valorPorHora;

    public SistemaEstacionamento(int totalVagas, double valorPorHora) {
        this.totalVagas = totalVagas;
        this.vagasDisponiveis = totalVagas;
        this.veiculosEstacionados = new HashMap<>();
        this.valorPorHora = valorPorHora;
    }

    public boolean estacionarVeiculo(String placa) {
        if (vagasDisponiveis <= 0) {
            System.out.println("Não há vagas disponíveis no estacionamento.");
            return false;
        }

        if (veiculosEstacionados.containsKey(placa)) {
            System.out.println("Veículo com esta placa já está estacionado.");
            return false;
        }

        Veiculo veiculo = new Veiculo(placa, LocalDateTime.now());
        veiculosEstacionados.put(placa, veiculo);
        vagasDisponiveis--;
        System.out.println("Veículo estacionado com sucesso. Vagas disponíveis: " + vagasDisponiveis);
        return true;
    }

    public double removerVeiculo(String placa) {
        if (!veiculosEstacionados.containsKey(placa)) {
            System.out.println("Veículo não encontrado no estacionamento.");
            return -1;
        }

        Veiculo veiculo = veiculosEstacionados.get(placa);
        LocalDateTime saida = LocalDateTime.now();
        Duration duracao = Duration.between(veiculo.getEntrada(), saida);
        long horas = duracao.toHours();
        if (duracao.toMinutes() % 60 > 0) {
            horas++; // Arredonda para cima se passar de minutos
        }

        double valor = horas * valorPorHora;
        veiculosEstacionados.remove(placa);
        vagasDisponiveis++;
        System.out.println("Veículo removido com sucesso.");
        System.out.println("Tempo estacionado: " + horas + " horas");
        System.out.println("Valor a pagar: R$ " + String.format("%.2f", valor));
        return valor;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setTotalVagas(int totalVagas) {
        int veiculosEstacionados = this.totalVagas - this.vagasDisponiveis;
        if (totalVagas < veiculosEstacionados) {
            System.out.println("Não é possível reduzir para menos que " + veiculosEstacionados + " vagas (número de veículos estacionados).");
            return;
        }

        this.totalVagas = totalVagas;
        this.vagasDisponiveis = totalVagas - veiculosEstacionados;
        System.out.println("Total de vagas atualizado para: " + totalVagas);
    }

    public void mostrarStatus() {
        System.out.println("\n=== Status do Estacionamento ===");
        System.out.println("Total de vagas: " + totalVagas);
        System.out.println("Vagas disponíveis: " + vagasDisponiveis);
        System.out.println("Veículos estacionados: " + (totalVagas - vagasDisponiveis));
        System.out.println("Valor por hora: R$ " + String.format("%.2f", valorPorHora));
        System.out.println("===============================\n");
    }

    private static class Veiculo {
        private String placa;
        private LocalDateTime entrada;

        public Veiculo(String placa, LocalDateTime entrada) {
            this.placa = placa;
            this.entrada = entrada;
        }

        public String getPlaca() {
            return placa;
        }

        public LocalDateTime getEntrada() {
            return entrada;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Sistema de Gerenciamento de Estacionamento ===");
        System.out.print("Informe o número total de vagas: ");
        int totalVagas = scanner.nextInt();
        System.out.print("Informe o valor por hora (R$): ");
        double valorPorHora = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        SistemaEstacionamento estacionamento = new SistemaEstacionamento(totalVagas, valorPorHora);
        
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Estacionar veículo");
            System.out.println("2 - Remover veículo");
            System.out.println("3 - Verificar vagas disponíveis");
            System.out.println("4 - Alterar total de vagas");
            System.out.println("5 - Mostrar status do estacionamento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.print("Informe a placa do veículo: ");
                    String placaEntrada = scanner.nextLine();
                    estacionamento.estacionarVeiculo(placaEntrada);
                    break;
                case 2:
                    System.out.print("Informe a placa do veículo: ");
                    String placaSaida = scanner.nextLine();
                    estacionamento.removerVeiculo(placaSaida);
                    break;
                case 3:
                    System.out.println("Vagas disponíveis: " + estacionamento.getVagasDisponiveis());
                    break;
                case 4:
                    System.out.print("Informe o novo total de vagas: ");
                    int novoTotal = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    estacionamento.setTotalVagas(novoTotal);
                    break;
                case 5:
                    estacionamento.mostrarStatus();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
}