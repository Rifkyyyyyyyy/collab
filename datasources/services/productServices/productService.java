package productServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import domain.model.ProductModel;
import conectionServices.connectionServices;




public class productService {

    private final Connection connection;
    private final ExecutorService executorService; // Executor to run async tasks

    // Constructor without parameters, directly getting the connection from ConnectionServices
    public productService() throws SQLException {
        this.connection = connectionServices.getConnection();  // Using ConnectionServices to get the connection
        this.executorService = Executors.newCachedThreadPool(); // Initialize the executor
    }

    // Add a new product asynchronously
    public CompletableFuture<Void> addProduct(ProductModel product) {
        return CompletableFuture.runAsync(() -> {
            try {
                String sql = "INSERT INTO products (name, description, price, stock, user_id) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, product.getName());
                    stmt.setString(2, product.getDescription());
                    stmt.setDouble(3, product.getPrice());
                    stmt.setInt(4, product.getStock());
                    stmt.setInt(5, product.getUserId());
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting product", e); // Handling exception
            }
        }, executorService); // Run in a background thread
    }

    // Get a product by ID
    public CompletableFuture<ProductModel> getProductById(int id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String sql = "SELECT * FROM products WHERE id = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return new ProductModel(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getDouble("price"),
                                    rs.getInt("stock"),
                                    rs.getInt("user_id"));
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching product by ID", e);
            }
            return null; // Return null if product is not found
        }, executorService);
    }

    // Get all products asynchronously
    public CompletableFuture<List<ProductModel>> getAllProducts() {
        return CompletableFuture.supplyAsync(() -> {
            List<ProductModel> products = new ArrayList<>();
            try {
                String sql = "SELECT * FROM products";
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        ProductModel product = new ProductModel(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getInt("user_id"));
                        products.add(product);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching all products", e);
            }
            return products;
        }, executorService);
    }

    // Update an existing product asynchronously
    public CompletableFuture<Void> updateProduct(ProductModel product) {
        return CompletableFuture.runAsync(() -> {
            try {
                String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ?, user_id = ? WHERE id = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, product.getName());
                    stmt.setString(2, product.getDescription());
                    stmt.setDouble(3, product.getPrice());
                    stmt.setInt(4, product.getStock());
                    stmt.setInt(5, product.getUserId());
                    stmt.setInt(6, product.getId());
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error updating product", e);
            }
        }, executorService);
    }

    // Delete a product by ID asynchronously
    public CompletableFuture<Void> deleteProduct(int id) {
        return CompletableFuture.runAsync(() -> {
            try {
                String sql = "DELETE FROM products WHERE id = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting product", e);
            }
        }, executorService);
    }
}
