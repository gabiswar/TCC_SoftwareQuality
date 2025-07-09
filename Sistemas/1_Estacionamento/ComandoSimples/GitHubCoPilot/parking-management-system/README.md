# Parking Management System

## Overview
The Parking Management System is a Java-based application designed to manage parking spots efficiently. It allows users to add, remove, and view available parking spots, as well as park and unpark vehicles.

## Project Structure
```
parking-management-system
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── parking
│   │   │           ├── App.java
│   │   │           ├── controller
│   │   │           │   └── ParkingController.java
│   │   │           ├── model
│   │   │           │   └── ParkingSpot.java
│   │   │           └── service
│   │   │               └── ParkingService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── parking
│       │           └── AppTest.java
│       └── resources
├── pom.xml
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd parking-management-system
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn exec:java -Dexec.mainClass="com.parking.App"
   ```

## Usage
- To add a parking spot, use the `addParkingSpot` method in `ParkingController`.
- To remove a parking spot, use the `removeParkingSpot` method in `ParkingController`.
- To view available parking spots, use the `getAvailableSpots` method in `ParkingController`.
- To park a vehicle, use the `parkVehicle` method in `ParkingService`.
- To unpark a vehicle, use the `unparkVehicle` method in `ParkingService`.

## Dependencies
This project uses Maven for dependency management. The required dependencies are specified in the `pom.xml` file.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.