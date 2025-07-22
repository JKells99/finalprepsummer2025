# Final Prep Summer 2025

This project is a Java-based application designed for user management with role-based access control. It features two main user roles: Commander and Chief. The application uses a PostgreSQL database for storing user information, including securely hashed passwords (BCrypt).

## Project Structure

- **src/main/java/**
  - `UserMain.java`: Main entry point for the application.
  - `database/DatabaseConnection.java`: Handles database connectivity.
  - `logger/CustomLogger.java`: Custom logging utility.
  - `user/`
    - `User.java`: Base user class.
    - `Commander.java`, `Chief.java`: Role-specific user classes.
    - `UserDAO.java`: Data access object for user operations.
    - `UserService.java`: Business logic for user management.
- **src/main/resources/scripts.sql**: SQL script for database schema (users table).
- **pom.xml**: Maven configuration file.

## Features

- User registration and authentication
- Role-based access (Commander, Chief)
- Secure password storage using BCrypt
- Database schema setup via SQL script

## Getting Started

1. Set up a PostgreSQL database and run the SQL script in `src/main/resources/scripts.sql`.
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   java -cp target/classes UserMain
   ```

## Future Improvements
- Add more user roles and permissions
- Implement additional business logic and features
- Enhance logging and error handling

---
This README provides an overview of the current state of the project. For more details, refer to the source code and documentation within each module.
