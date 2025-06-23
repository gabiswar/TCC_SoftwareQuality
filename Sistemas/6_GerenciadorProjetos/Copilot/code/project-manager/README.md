# Project Manager

This project is a complete project management application built in Java. It allows users to create and manage boards, lists, and cards, providing a structured way to organize tasks and projects.

## Features

- Create, view, update, and delete project boards.
- Create, view, update, and delete lists within a board.
- Create, view, update, delete, and move cards within lists.
- Each card can have a title, description, and due date.

## Project Structure

```
project-manager
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── projectmanager
│   │   │   │       ├── App.java
│   │   │   │       ├── controllers
│   │   │   │       │   ├── BoardController.java
│   │   │   │       │   ├── ListController.java
│   │   │   │       │   └── CardController.java
│   │   │   │       ├── models
│   │   │   │       │   ├── Board.java
│   │   │   │       │   ├── List.java
│   │   │   │       │   └── Card.java
│   │   │   │       ├── repositories
│   │   │   │       │   ├── BoardRepository.java
│   │   │   │       │   ├── ListRepository.java
│   │   │   │       │   └── CardRepository.java
│   │   │   │       └── services
│   │   │   │           ├── BoardService.java
│   │   │   │           ├── ListService.java
│   │   │   │           └── CardService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── projectmanager
│                   ├── BoardControllerTest.java
│                   ├── ListControllerTest.java
│                   └── CardControllerTest.java
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
   cd project-manager
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage

Once the application is running, you can access the API endpoints to manage boards, lists, and cards. Refer to the controller classes for specific endpoints and their usage.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.