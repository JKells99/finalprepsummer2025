package product;

import database.DatabaseConnection;
import logger.CustomLogger;

import java.io.IOException;
import java.sql.SQLException;

public class ProductDAO {

    public void createANewProduct(Product product) throws IOException {
        String sql = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
        // Logic to execute the SQL statement and insert the product into the database
        try (var connection = DatabaseConnection.getConnection();
                var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
            CustomLogger.logError(e.getMessage());
            System.out.println(e.getMessage());
            }


    }

    public void deleteProduct(int productId) throws IOException {
        String sql = "DELETE FROM products WHERE id = ?";
        // Logic to execute the SQL statement and delete the product from the database
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            CustomLogger.logError(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public Product getProductByName(String productName) {
        String sql = "SELECT * FROM products WHERE name = ?";
        // Logic to execute the SQL statement and retrieve a product by its name from the database
        try (var connection = DatabaseConnection.getConnection();
                var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productName);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Product getProductById(int productId) {
        // TODO: Implement logic to retrieve a product by its ID from the database
        return null; //
    }


    public void getAllProducts() throws IOException {
        String sql = "SELECT * FROM products";
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description + ", Price: " + price);
            }
        } catch (Exception e) {
            CustomLogger.logError(e.getMessage());
            System.out.println(e.getMessage());
        }

    }
}
