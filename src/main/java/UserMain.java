import user.Chief;
import user.Commander;
import user.User;
import user.UserService;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Entry point for the user management application.
 * <p>
 * This class provides a console-based menu for user authentication and registration.
 * Users can log in or register as either Chief or Commander roles. After authentication,
 * users are presented with role-specific menus. The application interacts with the
 * UserService to manage users and print system reports.
 * </p>
 *
 * <p><b>Menu Flow:</b></p>
 * <ul>
 *   <li>On startup, the system prints all users in the system.</li>
 *   <li>The main menu offers options to Login, Register, or Exit.</li>
 *   <li>Login: Prompts for username and password, authenticates, and shows the role-specific menu.</li>
 *   <li>Register: Prompts for username, password, and role, then creates a new user.</li>
 *   <li>Exit: Terminates the application.</li>
 *   <li>Role-specific menus (Chief/Commander) provide further actions, such as printing user reports.</li>
 * </ul>
 */
public class UserMain {
    /**
     * Main method to start the application and display the menu.
     * @param args command-line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        Chief chief = new Chief("chiefUsername2", "chiefPassword");
        Commander commander = new Commander("commanderUsername2", "commanderPassword");
//        userService.createNewUser(commander);
//        userService.createNewUser(chief);

//        User user1 =  userService.loginUserToSystem("chiefUsername2", "chiefPassword");

        userService.printAllUsersInSystemReport();




        boolean running = true;

        User currentUser = null;

        while (running) {
            while (currentUser == null) {
                System.out.println("\n1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                int authChoice = scanner.nextInt();
                scanner.nextLine();
                switch (authChoice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();
                        User user = userService.loginUserToSystem(loginUsername, loginPassword);
                        if (user != null) {
                            currentUser = user;
                            if(Objects.equals(currentUser.getRole(), "Chief")) {
                                System.out.println("\nYou are logged in as Chief........: " + currentUser.getUsername());
                                chiefMenu(userService, scanner, currentUser);
                            } else if(Objects.equals(currentUser.getRole(), "Commander")) {
                                System.out.println("\nYou are logged in as Commander: " + currentUser.getUsername());
                                commanderMenu(userService, scanner, currentUser);
                            } else {
                                System.out.println("You do not have permission to access this menu.");
                            }
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String regUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String regPassword = scanner.nextLine();
                        System.out.println("Enter role(Chief, Commander");
                        String role = scanner.nextLine();

                        if(role.equals("Chief")){
                            Chief newChief = new Chief(regUsername, regPassword);
                            userService.createNewUser(newChief);
                            System.out.println("Registration successful! You can now log in as Chief.");
                        } else if(role.equals("Commander")){
                            Commander newCommander = new Commander(regUsername, regPassword);
                            userService.createNewUser(newCommander);
                            System.out.println("Registration successful! You can now log in as Commander.");
                        } else {
                            System.out.println("Invalid role. Please try again.");
                            continue;
                        }
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            if (!running) break;



        }

        scanner.close();
    }

    /**
     * Displays the Commander menu and handles Commander-specific actions.
     * @param userService the user service for user operations
     * @param scanner the scanner for user input
     * @param currentUser the currently authenticated user
     */
    private static void commanderMenu(UserService userService, Scanner scanner, User currentUser) {
        System.out.println("\nCommander Menu:");
        System.out.println("1. Print All Users in System Report");
        System.out.println("2. Exit");
        System.out.print("Select an option: ");
        int reportChoice = scanner.nextInt();
        scanner.nextLine();
        if(reportChoice == 1) {
            try {
                userService.printAllUsersInSystemReport();
            } catch (IOException e) {
                System.out.println("Error generating user report: " + e.getMessage());
            }
        } else if(reportChoice == 2) {
            System.out.println("Exiting Commander Menu.");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Displays the Chief menu and handles Chief-specific actions.
     * @param userService the user service for user operations
     * @param scanner the scanner for user input
     * @param currentUser the currently authenticated user
     */
    private static void chiefMenu(UserService userService, Scanner scanner, User currentUser) {
        System.out.println("\nChief Menu:");
    }
}
