package user;

import logger.CustomLogger;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public void createNewUser(User user) throws IOException {
        try {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDAO.saveNewUser(user);
            System.out.println("User created successfully: " + user.getUsername());
        } catch (Exception e) {
            CustomLogger.logError("Error creating user: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void printAllUsersInSystemReport() throws IOException {
        try {
            List<User> users=  userDAO.getAllUsers()
                    .stream()
                    .sorted(Comparator.comparing(User::getRole))
                    .toList();

            System.out.println("Users in the system:");
            users.forEach(user -> System.out.println(user.getUsername() + ", " + user.getRole()));
            CustomLogger.logInfo("User List Accessed:");


        } catch (IOException e) {
            CustomLogger.logError(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User loginUserToSystem(String username, String password) throws IOException {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    return user;
                } else {
                    CustomLogger.logError("Invalid Credentials for user: " + username);
                    throw new RuntimeException("Invalid Credentials, Check Username and Password.");
                }
            } else {
                CustomLogger.logError("Invalid Credentials for user: " + username);
                throw new RuntimeException("Invalid Credentials, User With Username Not Found.");
            }
        } catch (IOException e) {
            CustomLogger.logError(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
