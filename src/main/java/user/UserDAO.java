package user;

import database.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void saveNewUser(User user){
        String sql = "INSERT INTO users2 (username, password, role) VALUES (?, ?, ?)";
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users2";
        try (Connection connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error retrieving users from the database: " + e.getMessage());
        }
        return users;
    }

    public User getUserByUsername(String username) throws IOException {
        String sql = "SELECT * FROM users2 WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error retrieving user by username: " + e.getMessage());
        }
        throw new RuntimeException("User not found with username: " + username);
    }
}
