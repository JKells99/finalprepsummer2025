package product;

import java.io.IOException;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    public void createNewProduct(Product product) throws IOException {
        productDAO.createANewProduct(product);
        System.out.println("Product created successfully: " + product.getName());
    }

    public void deleteProduct(int productId) throws IOException {
        productDAO.deleteProduct(productId);
        System.out.println("Product deleted successfully with ID: " + productId);
    }
    public void getAllProducts() throws IOException {
        try {
            productDAO.getAllProducts();
            System.out.println("All products retrieved successfully.");
        } catch (IOException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Product getProductByName(String productName) throws IOException {
        Product product = productDAO.getProductByName(productName);
        if (product != null) {
            System.out.println("Product found: " + product.getName());
            return product;
        } else {
            System.err.println("Product not found with name: " + productName);
            return null;
        }
    }

    public void printInventoryReport() {
        try {
            int totalValueOfInventory = productDAO.getProductValueForWholeStore();
            String report = "Total Value of Inventory: $" + totalValueOfInventory;
            System.out.println(report);
        } catch (IOException e) {
            System.out.println("Error generating inventory report: " + e.getMessage());
        }
    }
}
