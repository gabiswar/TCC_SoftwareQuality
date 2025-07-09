import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao sistema de gerenciamento de estacionamento!");

        System.out.print("Digite a quantidade de vagas no estacionamento: ");
        int totalVagas = scanner.nextInt();
        Estacionamento estacionamento = new Estacionamento(totalVagas);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Estacionar veículo");
            System.out.println("2. Remover veículo");
            System.out.println("3. Verificar vagas disponíveis");
            System.out.println("4. Alterar quantidade de vagas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    String placa = scanner.nextLine();
                    if (estacionamento.estacionarVeiculo(placa)) {
                        System.out.println("Veículo estacionado com sucesso.");
                    } else {
                        System.out.println("Estacionamento lotado.");
                    }
                    break;
                case 2:
                    System.out.print("Digite a placa do veículo a ser removido: ");
                    placa = scanner.nextLine();
                    if (estacionamento.removerVeiculo(placa)) {
                        System.out.println("Veículo removido com sucesso.");
                    } else {
                        System.out.println("Veículo não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Vagas disponíveis: " + estacionamento.vagasDisponiveis());
                    break;
                case 4:
                    System.out.print("Digite a nova quantidade de vagas: ");
                    totalVagas = scanner.nextInt();
                    estacionamento.setTotalVagas(totalVagas);
                    System.out.println("Quantidade de vagas atualizada.");
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Até mais!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
