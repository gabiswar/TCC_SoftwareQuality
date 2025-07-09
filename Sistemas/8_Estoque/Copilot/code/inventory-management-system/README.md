# Inventory Management System

This project is an Inventory Management System built in Java, designed to manage multiple warehouses and their products efficiently. It allows users to create, view, update, and delete warehouses and products, as well as manage product entries, exits, and transfers between warehouses.

## Features

- **Warehouse Management**
  - Create new warehouses with names and locations.
  - View all existing warehouses.
  - Update information of existing warehouses.
  - Delete warehouses that are no longer needed.

- **Product Management**
  - Register new products with details such as name, description, price, and quantity.
  - Update information of existing products.
  - Delete products that are no longer needed.

- **Inventory Operations**
  - Record entries of products into specific warehouses, including quantity and date.
  - Record exits of products from specific warehouses, including quantity and date.
  - Transfer products between warehouses, including quantity and date.

## Technologies Used

- Java
- Spring Boot (for building the application)
- Maven (for dependency management)
- JUnit (for testing)

## Setup Instructions

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/inventory-management-system.git
   ```

2. Navigate to the project directory:
   ```
   cd inventory-management-system
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access the application at `http://localhost:8080`.

## Usage Guidelines

- Use the provided controllers to interact with the inventory system.
- Refer to the `application.properties` file for configuration settings.
- Sample data can be initialized using the `data.sql` file.

## Testing

Unit tests are provided for the controllers to ensure the functionality of the application. You can run the tests using:
```
mvn test
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.