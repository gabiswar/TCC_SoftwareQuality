import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLotRepository repository = new ParkingLotRepository(10); // 10 vagas
        ParkingLotService service = new ParkingLotService(repository);

        while (true) {
            System.out.println("\n---- Gerenciamento de Estacionamento ----");
            System.out.println("1. Estacionar veículo");
            System.out.println("2. Remover veículo");
            System.out.println("3. Verificar vagas disponíveis");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (option) {
                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    String licensePlate = scanner.nextLine();
                    System.out.print("Digite o tipo do veículo (Carro/Moto): ");
                    String type = scanner.nextLine();
                    System.out.println(service.parkVehicle(licensePlate, type));
                    break;
                case 2:
                    System.out.print("Digite a placa do veículo: ");
                    licensePlate = scanner.nextLine();
                    System.out.println(service.removeVehicle(licensePlate));
                    break;
                case 3:
                    System.out.println("Vagas disponíveis: " + repository.getAvailableSpots());
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
