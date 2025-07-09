import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum TipoVeiculo {
    MOTO, CARRO, ONIBUS
}

enum TipoVaga {
    MOTO(5.0), CARRO(10.0), ONIBUS(20.0);

    private final double taxaPorHora;

    TipoVaga(double taxaPorHora) {
        this.taxaPorHora = taxaPorHora;
    }

    public double getTaxaPorHora() {
        return taxaPorHora;
    }
}

class Veiculo {
    private String placa;
    private TipoVeiculo tipo;

    public Veiculo(String placa, TipoVeiculo tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }
}

class Vaga {
    private String id;
    private TipoVaga tipo;
    private Veiculo veiculo;
    private LocalDateTime entrada;

    public Vaga(String id, TipoVaga tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public boolean estaDisponivel() {
        return veiculo == null;
    }

    public boolean estacionar(Veiculo veiculo) {
        if (estaDisponivel() && veiculoCompativel(veiculo.getTipo())) {
            this.veiculo = veiculo;
            this.entrada = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public Veiculo removerVeiculo() {
        Veiculo v = this.veiculo;
        this.veiculo = null;
        return v;
    }

    public double calcularTarifa() {
        if (estaDisponivel()) return 0;
        Duration duracao = Duration.between(entrada, LocalDateTime.now());
        long horas = duracao.toHours();
        if (duracao.toMinutes() % 60 > 0) horas++;
        return horas * tipo.getTaxaPorHora();
    }

    private boolean veiculoCompativel(TipoVeiculo tipoVeiculo) {
        return switch (tipo) {
            case MOTO -> tipoVeiculo == TipoVeiculo.MOTO;
            case CARRO -> tipoVeiculo == TipoVeiculo.CARRO || tipoVeiculo == TipoVeiculo.MOTO;
            case ONIBUS -> tipoVeiculo == TipoVeiculo.ONIBUS;
        };
    }

    public TipoVaga getTipo() {
        return tipo;
    }
}

class Estacionamento {
    private List<Vaga> vagas;

    public Estacionamento() {
        vagas = new ArrayList<>();
        // Inicializar vagas (exemplo)
        vagas.add(new Vaga("M1", TipoVaga.MOTO));
        vagas.add(new Vaga("M2", TipoVaga.MOTO));
        vagas.add(new Vaga("C1", TipoVaga.CARRO));
        vagas.add(new Vaga("C2", TipoVaga.CARRO));
        vagas.add(new Vaga("O1", TipoVaga.ONIBUS));
    }

    public boolean estacionarVeiculo(Veiculo veiculo) {
        for (Vaga vaga : vagas) {
            if (vaga.estacionar(veiculo)) {
                return true;
            }
        }
        return false;
    }

    public double removerVeiculo(String placa) {
        for (Vaga vaga : vagas) {
            if (!vaga.estaDisponivel() && vaga.removerVeiculo().getPlaca().equals(placa)) {
                return vaga.calcularTarifa();
            }
        }
        return -1; // Indica que o veículo não foi encontrado
    }

    public void mostrarVagasDisponiveis() {
        long motos = vagas.stream().filter(v -> v.getTipo() == TipoVaga.MOTO && v.estaDisponivel()).count();
        long carros = vagas.stream().filter(v -> v.getTipo() == TipoVaga.CARRO && v.estaDisponivel()).count();
        long onibus = vagas.stream().filter(v -> v.getTipo() == TipoVaga.ONIBUS && v.estaDisponivel()).count();

        System.out.println("Vagas disponíveis:");
        System.out.println("Motos: " + motos);
        System.out.println("Carros: " + carros);
        System.out.println("Ônibus: " + onibus);
    }
}

public class SistemaEstacionamento {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema de Estacionamento ===");
            System.out.println("1. Estacionar veículo");
            System.out.println("2. Remover veículo");
            System.out.println("3. Ver vagas disponíveis");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.println("Tipo do veículo (1-Moto, 2-Carro, 3-Ônibus): ");
                    int tipo = scanner.nextInt();
                    TipoVeiculo tipoVeiculo = switch (tipo) {
                        case 1 -> TipoVeiculo.MOTO;
                        case 2 -> TipoVeiculo.CARRO;
                        case 3 -> TipoVeiculo.ONIBUS;
                        default -> null;
                    };

                    if (tipoVeiculo != null) {
                        Veiculo veiculo = new Veiculo(placa, tipoVeiculo);
                        if (estacionamento.estacionarVeiculo(veiculo)) {
                            System.out.println("Veículo estacionado com sucesso!");
                        } else {
                            System.out.println("Não há vagas disponíveis para este tipo de veículo");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Digite a placa do veículo a ser removido: ");
                    String placaRemover = scanner.nextLine();
                    double tarifa = estacionamento.removerVeiculo(placaRemover);
                    if (tarifa >= 0) {
                        System.out.printf("Veículo removido. Tarifa: R$ %.2f%n", tarifa);
                    } else {
                        System.out.println("Veículo não encontrado");
                    }
                    break;

                case 3:
                    estacionamento.mostrarVagasDisponiveis();
                    break;

                case 4:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}