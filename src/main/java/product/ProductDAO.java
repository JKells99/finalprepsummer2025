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

    public Product getProductByName(String productName) throws IOException {
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
            CustomLogger.logInfo("Data Accessed: Product Name: " + productName);
        } catch (SQLException | IOException e) {
            CustomLogger.logError(e.getMessage());
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
                // Use string buffer to format the output better
                // Print the product details
                StringBuilder sb = new StringBuilder();
                sb.append("-----------------------------\n");
                sb.append("\n");
                sb.append("Product Details: \n");
                sb.append("ID: ").append(id).append("\n");
                sb.append("Name: ").append(name).append("\n");
                sb.append("Description: ").append(description).append("\n");
                sb.append("Price: ").append(price).append("\n");
                sb.append("-----------------------------\n");
                System.out.println(sb);
                CustomLogger.logInfo("Product Details Accessed");
            }
        } catch (Exception e) {
            CustomLogger.logError(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public int getProductValueForWholeStore() throws IOException {
        String sql = "SELECT SUM(price) AS total_value FROM products";
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CustomLogger.logInfo("Total Value of Inventory Accessed: $" + resultSet.getInt("total_value"));
                return resultSet.getInt("total_value");
            }
        } catch (SQLException e) {
            CustomLogger.logError(e.getMessage());
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
