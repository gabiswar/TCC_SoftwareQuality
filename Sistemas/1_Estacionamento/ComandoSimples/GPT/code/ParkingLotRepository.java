import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<String, Vehicle> parkedVehicles = new HashMap<>();
    private int totalSpots;
    private int availableSpots;

    public ParkingLotRepository(int totalSpots) {
        this.totalSpots = totalSpots;
        this.availableSpots = totalSpots;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (availableSpots > 0 && !parkedVehicles.containsKey(vehicle.getLicensePlate())) {
            parkedVehicles.put(vehicle.getLicensePlate(), vehicle);
            availableSpots--;
            return true;
        }
        return false;
    }

    public Vehicle removeVehicle(String licensePlate) {
        Vehicle removedVehicle = parkedVehicles.remove(licensePlate);
        if (removedVehicle != null) {
            availableSpots++;
        }
        return removedVehicle;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public boolean isVehicleParked(String licensePlate) {
        return parkedVehicles.containsKey(licensePlate);
    }
}
