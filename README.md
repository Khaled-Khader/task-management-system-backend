# Task Management System - Backend

Backend application for the Task Management System built with **Java 21 and Spring Boot 4**.

The project is structured as a **multi-module Maven project** and uses a **custom Maven parent POM** to manage the modules and shared dependencies.

## Technologies

* Java 21
* Spring Boot 4
* Maven
* Multi-module Maven project
* Custom Maven parent POM
* MySQL
* **Liquibase** for database schema management and migrations
* Spring Security
* JWT Authentication
* Spring AOP
* WebSocket / STOMP

### Authentication

The application uses **JWT (JSON Web Tokens)** for authentication and authorization.

JWT tokens are stored in an **HttpOnly cookie** and are used to authenticate requests to protected endpoints.

### Activity Logging

**Spring AOP** is used to implement activity logging.

Application actions are intercepted using AOP and recorded as activity logs without placing logging logic directly inside the business logic.

### Real-Time Notifications

**WebSocket / STOMP** is used to provide real-time notifications.

When a notification event occurs, the backend sends the notification to the connected client through WebSocket.

### Database Management

The application uses **MySQL** as its relational database.

**Liquibase** is used to manage database schema changes and migrations. Database tables and changes are defined through Liquibase changelogs, allowing the database schema to be created and updated automatically when the application starts.

## Prerequisites

Install the following:

* IntelliJ IDEA
* Java 21
* MySQL 8+
* Git

## 1. Clone the Repository

Clone the repository:

```bash
git clone https://github.com/Khaled-Khader/task-management-system-backend.git
```

Open the cloned project in **IntelliJ IDEA**.

The project is a **multi-module Maven project**. IntelliJ should automatically detect the Maven configuration from the `pom.xml` files and download the required dependencies.

## 2. Configure MySQL

Make sure MySQL is running.

Create the database:

```sql
CREATE DATABASE `task-management-system`;
```

No tables need to be created manually.

## 3. Configure Database Credentials

Open the appropriate application configuration file:

```text
src/main/resources/application.properties
```

Configure your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task-management-system
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Replace `YOUR_PASSWORD` with your MySQL password.

> Do not commit real passwords or sensitive credentials to Git.

## 4. Run the Application from IntelliJ IDEA

In IntelliJ IDEA:

1. Open the project.
2. Wait for Maven to finish importing all modules and downloading dependencies.
3. Locate the main Spring Boot application class.
4. Find the class containing `@SpringBootApplication`.
5. Right-click the class.
6. Select **Run '...Application'**.

Alternatively, click the green **Run ▶** button next to the `main` method.

The application should start on:

```text
http://localhost:8888
```

## 5. Database Migration with Liquibase

Liquibase manages the database schema automatically.

When the application starts:

1. Liquibase connects to the MySQL database.
2. Liquibase checks the configured changelog files.
3. Pending database changes are applied.
4. The required tables and database structure are created or updated.

Therefore, **no manual table creation is required**.

Make sure the database itself exists before starting the application:

```sql
CREATE DATABASE `task-management-system`;
```

## 6. Troubleshooting

### MySQL Connection Error

Make sure:

* MySQL is running.
* The database `task-management-system` exists.
* The username is correct.
* The password is correct.

### Liquibase Migration Error

If Liquibase fails during startup:

* Make sure the MySQL database exists.
* Check the database credentials.
* Make sure the Liquibase changelog files are available in the project.
* Check the application logs for the specific Liquibase error.

### Maven Dependencies Are Not Loaded

In IntelliJ IDEA:

1. Open the **Maven** tool window.
2. Click **Reload All Maven Projects**.
3. Wait for Maven to finish downloading the dependencies.

Since this is a multi-module Maven project, make sure all modules are imported successfully.

### Port 8888 Is Already in Use

Stop the application using port `8888`, or change the server port in:

```text
src/main/resources/application.properties
```

Example:

```properties
server.port=8080
```

## Default Configuration

| Configuration      | Value                   |
| ------------------ | ----------------------- |
| Java               | 21                      |
| Spring Boot        | 4                       |
| Build Tool         | Maven                   |
| Project Type       | Multi-module Maven      |
| Parent             | Custom Maven Parent POM |
| Database           | MySQL                   |
| Database Migration | Liquibase               |
| Authentication     | JWT                     |
| Activity Logging   | Spring AOP              |
| Notifications      | WebSocket / STOMP       |
| Backend Port       | 8888                    |
| Database Name      | task-management-system  |


