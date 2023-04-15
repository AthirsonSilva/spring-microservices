# Employee Management System

This is a microservice application built with Spring Boot and Spring Cloud to manage the employees and departments of an organization.

## Technologies used

- Java 17+
- Spring Boot 3.x
- Spring Cloud
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Swagger

## Architecture

The application is built using Spring Boot and consists of several layers:

- `Controller`: Handles incoming HTTP requests and maps them to the appropriate service methods
- `Services`: Contains the business logic for managing records and interacts with the repository layer
- `Repository`: Provides an abstraction for interacting with the database
- `Exceptions`: Handles custom exceptions used all over the application
- `Payload`: Layer with classes to transfer objects with data from a layer to another

## Getting started

To run this project on your machine, make sure you have Java and Docker installed. Then, follow these steps:

1. Clone the repository

```
git clone https://github.com/your-username/employee-management-system.git
```

2. Build the project

```
cd employee-management-system
./mvnw clean package -DskipTests
```

3. Start the PostgreSQL database using Docker Compose

```
docker-compose up -d
```

5. Access the Swagger UI

```
http://localhost:8080/swagger-ui.html
```

## Endpoints

| Method | URL                                      | Description                                     |
|--------|------------------------------------------|-------------------------------------------------|
| GET    | /departments                             | Retrieve all departments                        |
| GET    | /departments/{id}                        | Retrieve department by id                       |
| POST   | /departments                             | Create a new department                          |
| GET    | /employees                                | Retrieve all employees                           |
| GET    | /employees/{id}                           | Retrieve employee by id                          |
| POST   | /employees                                | Create a new employee                            |
| GET    | /employees?departmentId={departmentId}    | Retrieve all employees by department id          |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
