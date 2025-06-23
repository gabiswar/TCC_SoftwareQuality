# Restaurant Ordering System

## Overview
The Restaurant Ordering System is a Java-based application designed to streamline the process of managing restaurant orders, integrating kitchen communication, and handling payments. This system allows restaurant staff to create and manage orders efficiently while providing real-time updates to the kitchen and customers.

## Features
- **Create and View Orders**: Staff can create new orders and view existing ones.
- **Update Order Status**: Easily update the status of orders as they progress through the kitchen.
- **Kitchen Integration**: Send orders directly to the kitchen and receive updates on their status.
- **Payment Processing**: Calculate total prices for orders and generate payment receipts.
- **Menu Management**: Add and manage menu items along with their prices.

## Project Structure
```
restaurant-ordering-system
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── restaurant
│   │   │   │   │   ├── Main.java
│   │   │   │   │   ├── models
│   │   │   │   │   │   ├── Order.java
│   │   │   │   │   │   ├── MenuItem.java
│   │   │   │   │   │   └── KitchenStatus.java
│   │   │   │   │   ├── services
│   │   │   │   │   │   ├── OrderService.java
│   │   │   │   │   │   ├── KitchenService.java
│   │   │   │   │   │   └── PaymentService.java
│   │   │   │   │   └── utils
│   │   │   │   │       └── ReceiptGenerator.java
│   │   ├── resources
│   │   │   └── application.properties
│   └── test
│       └── java
│           └── com
│               └── restaurant
│                   ├── OrderServiceTest.java
│                   ├── KitchenServiceTest.java
│                   └── PaymentServiceTest.java
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   ```
2. **Navigate to the Project Directory**: 
   ```
   cd restaurant-ordering-system
   ```
3. **Build the Project**: 
   ```
   mvn clean install
   ```
4. **Run the Application**: 
   ```
   mvn exec:java -Dexec.mainClass="com.restaurant.Main"
   ```

## Usage
- To create a new order, use the order management interface.
- The kitchen will receive the order and can update its status through the kitchen communication interface.
- Payments can be processed through the payment service, which will generate a receipt for the customer.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.