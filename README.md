# Final Prep Summer 2025

This project is a Java-based user management application with role-based access control. It features a console menu for user authentication and registration, supporting two main roles: Commander and Chief. User data is stored in a PostgreSQL database, with passwords securely hashed using BCrypt.

## Project Structure

- **src/main/java/**
  - `UserMain.java`: Entry point. Provides a console menu for login, registration, and role-based actions.
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

- Console-based menu for user login, registration, and exit
- Role-based access (Commander, Chief) with role-specific menus
- Secure password storage using BCrypt
- User registration and authentication
- Print all users in the system (report)

## Menu Flow

1. On startup, the system prints all users in the database.
2. Main menu options:
   - **Login**: Authenticate and access role-specific menu (Chief or Commander).
   - **Register**: Create a new user with a chosen role.
   - **Exit**: Terminate the application.
3. Role-specific menus:
   - **Commander**: Print all users report, exit.
   - **Chief**: Placeholder for future Chief-specific actions.

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
- Expand Chief menu functionality
- Enhance logging and error handling

---
This README provides an updated overview of the application's current state and menu flow. For more details, refer to the source code and documentation within each module.
