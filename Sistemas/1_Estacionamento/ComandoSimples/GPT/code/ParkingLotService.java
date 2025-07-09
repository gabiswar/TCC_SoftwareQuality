public class ParkingLotService {
    private ParkingLotRepository repository;

    public ParkingLotService(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public String parkVehicle(String licensePlate, String type) {
        Vehicle vehicle = new Vehicle(licensePlate, type, System.currentTimeMillis());
        if (repository.parkVehicle(vehicle)) {
            return "Veículo estacionado com sucesso!";
        }
        return "Estacionamento lotado ou veículo já estacionado.";
    }

    public String removeVehicle(String licensePlate) {
        Vehicle vehicle = repository.removeVehicle(licensePlate);
        if (vehicle != null) {
            long parkedTime = System.currentTimeMillis() - vehicle.getEntryTime();
            double charge = calculateCharge(parkedTime, vehicle.getType());
            return String.format("Veículo removido. Tarifa: R$%.2f", charge);
        }
        return "Veículo não encontrado.";
    }

    private double calculateCharge(long parkedTime, String type) {
        double hourlyRate = type.equalsIgnoreCase("Carro") ? 5.0 : 3.0;
        double hours = Math.ceil(parkedTime / (1000.0 * 60 * 60));
        return hours * hourlyRate;
    }
}
