# Personal Finance System

## Overview
The Personal Finance System is a Java application designed to help users manage their personal finances effectively. It allows users to track their income and expenses, categorize them, and generate financial reports to gain insights into their financial health.

## Features
- Register and manage income entries with details such as value, date, and category.
- Register and manage expense entries with details such as value, date, and category.
- Update existing income and expense records.
- Delete income and expense records that are no longer needed.
- View the current balance of the user.
- View expenses categorized by different categories.
- View incomes categorized by different categories.
- Generate detailed financial reports that summarize income, expenses, and balance.

## Project Structure
```
personal-finance-system
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── finance
│   │   │   │       ├── App.java
│   │   │   │       ├── model
│   │   │   │       │   ├── Expense.java
│   │   │   │       │   ├── Income.java
│   │   │   │       │   └── Category.java
│   │   │   │       ├── service
│   │   │   │       │   ├── FinanceService.java
│   │   │   │       │   └── ReportService.java
│   │   │   │       └── util
│   │   │   │           └── DateUtil.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── finance
│                   └── AppTest.java
├── pom.xml
└── README.md
```

## Getting Started
To get started with the Personal Finance System, follow these steps:

1. **Clone the repository:**
   ```
   git clone https://github.com/yourusername/personal-finance-system.git
   ```

2. **Navigate to the project directory:**
   ```
   cd personal-finance-system
   ```

3. **Build the project using Maven:**
   ```
   mvn clean install
   ```

4. **Run the application:**
   ```
   mvn exec:java -Dexec.mainClass="com.finance.App"
   ```

## Dependencies
This project uses Maven for dependency management. The required dependencies are specified in the `pom.xml` file.

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.