import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento(0);

        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE ESTACIONAMENTO ===");
            System.out.println("1 - Cadastrar quantidade de vagas");
            System.out.println("2 - Estacionar veículo");
            System.out.println("3 - Remover veículo");
            System.out.println("4 - Verificar vagas disponíveis");
            System.out.println("5 - Listar veículos estacionados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Informe a quantidade de vagas do estacionamento: ");
                    int vagas = scanner.nextInt();
                    estacionamento.setTotalVagas(vagas);
                    System.out.println("Quantidade de vagas cadastrada com sucesso.");
                    break;

                case 2:
                    System.out.print("Informe a placa do veículo: ");
                    String placa = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    if (estacionamento.estacionarVeiculo(veiculo)) {
                        System.out.println("Veículo estacionado com sucesso.");
                    } else {
                        System.out.println("Estacionamento cheio!");
                    }
                    break;

                case 3:
                    System.out.print("Informe a placa do veículo a remover: ");
                    String placaRemover = scanner.nextLine();
                    if (estacionamento.removerVeiculo(placaRemover)) {
                        System.out.println("Veículo removido.");
                    } else {
                        System.out.println("Veículo não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Vagas disponíveis: " + estacionamento.vagasDisponiveis());
                    break;

                case 5:
                    estacionamento.listarVeiculos();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
