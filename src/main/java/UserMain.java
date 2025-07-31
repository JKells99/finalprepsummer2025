import product.Product;
import product.ProductService;
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
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);
//        Chief chief = new Chief("chiefUsername2", "chiefPassword");
//        Commander commander = new Commander("commanderUsername2", "commanderPassword");

        boolean running = true;

        User currentUser = null;

        do {
            while (currentUser == null) {
                System.out.println("\n1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                int authChoice = scanner.nextInt();
                scanner.nextLine();
                switch (authChoice) {
                    case 1:
                        logInMenu(userService,scanner,currentUser,productService);
                        break;
                    case 2:
                        registerMenu(userService, scanner);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } while (running);

        scanner.close();
    }

    private static void registerMenu(UserService userService, Scanner scanner) throws IOException {
        System.out.print("Enter username: ");
        String regUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String regPassword = scanner.nextLine();
        System.out.println("Enter role(Chief, Commander");
        String role = scanner.nextLine();
        if (role.equals("Chief")) {
            Chief newChief = new Chief(regUsername, regPassword);
            userService.createNewUser(newChief);
            System.out.println("Registration successful! You can now log in as Chief.");
        } else if (role.equals("Commander")) {
            Commander newCommander = new Commander(regUsername, regPassword);
            userService.createNewUser(newCommander);
            System.out.println("Registration successful! You can now log in as Commander.");
        } else {
            System.out.println("Invalid role. Please try again.");

        }
    }

    private static void logInMenu(UserService userService, Scanner scanner, User currentUser, ProductService productService) throws IOException {
//        System.out.print("Enter username: ");
//        String loginUsername = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String loginPassword = scanner.nextLine();
        User user = userService.loginUserToSystem("chiefUsername2", "chiefPassword");
        if (user != null) {
            currentUser = user;
            // Chief users access the Chief menu
            if (Objects.equals(currentUser.getRole(), "Chief")) {
                System.out.println("\nYou are logged in as Chief........: " + currentUser.getUsername());
                chiefMenu(userService, scanner, currentUser, productService);
                // Commander users access the Commander menu
            } else if (Objects.equals(currentUser.getRole(), "Commander")) {
                System.out.println("\nYou are logged in as Commander: " + currentUser.getUsername());
                commanderMenu(userService, scanner, currentUser, productService);
            } else {
                System.out.println("You do not have permission to access this menu.");
            }
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    /**
     * Displays the Commander menu and handles Commander-specific actions.
     * @param userService the user service for user operations
     * @param scanner the scanner for user input
     * @param currentUser the currently authenticated user
     */
    private static void commanderMenu(UserService userService, Scanner scanner, User currentUser, ProductService productService) throws IOException {
        boolean commanderRunning = true;
        while (commanderRunning) {
            System.out.println("\nCommander Menu:");
            System.out.println("1. Print All Users in System Report");
            System.out.println("2. Create New Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Search Products");
            System.out.println("5. View All Products");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int reportChoice = scanner.nextInt();
            scanner.nextLine();
            switch (reportChoice) {
                case 1:
                    userService.printAllUsersInSystemReport();
                    System.out.println("All users in the system report printed successfully.");
                    break;
                case 2:
                    System.out.println("Creating a new product...");
                    createNewProduct(scanner, productService);
                    break;
                case 3:
                    System.out.println("Deleting a product...");
                    deleteProduct(scanner, productService);
                    break;
                case 4:
                    System.out.println("Searching for products...");
                    searchProducts(scanner, productService);
                    break;
                case 5:
                    System.out.println("Viewing all products...");
                    viewAllProducts(productService);
                    break;
                case 6:
                    System.out.println("Exiting Commander Menu.");
                    commanderRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }

    /**
     * Retrieves and displays all products in the system.
     * Handles any IO exceptions that may occur during retrieval.
     * @param productService the product service for product operations
     */
    private static void viewAllProducts(ProductService productService) {
        try {
            productService.getAllProducts();
        } catch (IOException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
    }

    /**
     * Searches for a product by name and displays its details if found.
     * @param scanner the scanner for user input
     * @param userService the product service for product operations
     */
    private static void searchProducts(Scanner scanner, ProductService userService) throws IOException {
        System.out.print("Enter product name to search: ");
        String productName = scanner.nextLine();
        Product product = userService.getProductByName(productName);
        if (product != null) {
            System.out.println("Product found: " + product.getName() + ", Description: " + product.getDescription() + ", Price: " + product.getPrice());
        } else {
            System.out.println("Product not found with name: " + productName);
        }
    }

    /**
     * Deletes a product by its ID after displaying all products.
     * Handles IO exceptions during deletion.
     * @param scanner the scanner for user input
     * @param productService the product service for product operations
     * @throws IOException if an I/O error occurs
     */
    private static void deleteProduct(Scanner scanner, ProductService productService) throws IOException {
        try {
            productService.getAllProducts();
            System.out.print("Enter the ID of the product to delete: ");
            int productId = scanner.nextInt();
            scanner.nextLine();
            productService.deleteProduct(productId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void createNewProduct(Scanner scanner, ProductService productService) throws IOException {
        System.out.println("Creating New Product Menu!:");
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter product description: ");
        String productDescription = scanner.nextLine();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();

        Product newProduct = new Product(productName, productDescription, productPrice);
        productService.createNewProduct(newProduct);
        return;

    }

    /**
     * Displays the Chief menu and handles Chief-specific actions.
     *
     * @param userService    the user service for user operations
     * @param scanner        the scanner for user input
     * @param currentUser    the currently authenticated user
     * @param productService
     */
    private static void chiefMenu(UserService userService, Scanner scanner, User currentUser, ProductService productService) {
        System.out.println("\nChief Menu:");
        boolean chiefRunning = true;
        while (chiefRunning) {
            int choice;
            System.out.println();
            System.out.println("Chief Menu Options:");
            System.out.println("1: view all products");
            System.out.println("2 Print Inventory Value Report");
            System.out.println("3: Exit Chief Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                try {
                    productService.getAllProducts();
                } catch (IOException e) {
                    System.out.println("Error retrieving products: " + e.getMessage());
                }
            } else if (choice == 2) {
                productService.printInventoryReport();

            } else if (choice == 3) {
                System.out.println("Exiting Chief Menu.");
                chiefRunning = false;

            } else {
                System.out.println("Invalid option. Please try again.");
            }


        }
    }
}
