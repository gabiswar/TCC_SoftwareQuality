public class Vehicle {
    private String licensePlate;
    private String type; // Carro, Moto, etc.
    private long entryTime; // Em milissegundos

    public Vehicle(String licensePlate, String type, long entryTime) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.entryTime = entryTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    public long getEntryTime() {
        return entryTime;
    }
}
