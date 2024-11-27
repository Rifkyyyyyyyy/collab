package userServices;

import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import domain.model.UserModel;
import roles.role;
import conectionServices.*;;


public class UserService {

    private final ExecutorService executorService;

    public UserService() {
        this.executorService = Executors.newCachedThreadPool();
    }

    // Method to get a user by username and password asynchronously
    public CompletableFuture<UserModel> getUserByUsernameAndPassword(String username, String password) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection conn = connectionServices.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");

                    String roleString = rs.getString("role").toUpperCase();
                    role roles = role.valueOf(roleString);
                    return new UserModel(id, username, password, roles);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Login failed: " + e.getMessage(), e);
            }
            return null; // Return null if no user is found
        }, executorService);
    }

    // Method to register a user asynchronously
    public CompletableFuture<Boolean> registerUser(UserModel user) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            try (Connection conn = connectionServices.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword()); // Make sure the password is hashed
                stmt.setString(3, user.getRole().name()); // Convert Role to String
                int rowsAffected = stmt.executeUpdate();

                // Return true if the user was successfully registered
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new RuntimeException("Register failed: " + e.getMessage(), e);
            }
        }, executorService);
    }
}
