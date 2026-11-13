# Employee Management System — Spring Boot REST API + JDBC + MySQL

CRUD REST API using Spring Boot 3, Java 17, JdbcTemplate and MySQL.

## Setup
1. Install JDK 17 and MySQL 8+.
2. In src/main/resources/application.properties, replace YOUR_MYSQL_PASSWORD with your local MySQL password.
3. Open this folder in IntelliJ as a Maven project and wait for dependencies.
4. Run com.parth.employee.EmployeeManagementApplication.
5. The JDBC URL creates employee_db; schema.sql creates the employees table.

Never publish a real database password.

## Postman endpoints
Base URL: http://localhost:8080/api/employees

- POST /api/employees — create employee (201 Created). Example JSON: {"name":"Aarav Sharma","department":"Engineering","salary":55000.00}
- GET /api/employees — list all employees (200 OK).
- GET /api/employees/1 — get employee by ID (404 if missing).
- PUT /api/employees/1 — update employee. Example JSON: {"name":"Aarav Sharma","department":"AI Engineering","salary":62000.00}; 404 if missing.
- DELETE /api/employees/1 — delete employee (204 No Content; 404 if missing).

## Project structure
- model/Employee.java: employee fields.
- repository/EmployeeRepository.java: JDBC CRUD using JdbcTemplate.
- controller/EmployeeController.java: REST endpoints and validation.
- EmployeeManagementApplication.java: Spring Boot entry point.
- src/main/resources: DB configuration and schema.

## Three AI prompts used
1. "Create a Java 17 Spring Boot Maven project for an Employee Management REST API using JDBC and MySQL. List dependencies and project structure."
2. "Create an Employee model with id, name, department and salary, and a JdbcTemplate repository with CRUD methods."
3. "Create POST, GET, PUT and DELETE REST endpoints with validation and suitable HTTP responses, including 404 for missing IDs."

## Submission checklist
Run the application and test all endpoints in Postman. Capture your own screenshots of the project structure and successful/unsuccessful API requests.