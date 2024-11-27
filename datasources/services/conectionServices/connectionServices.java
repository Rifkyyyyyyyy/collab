package conectionServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configure.constant;

public class connectionServices {

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create and return the connection
            return DriverManager.getConnection(constant.DB_URL, constant.DB_USER, constant.DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new SQLException("Database connection failed: " + e.getMessage(), e);
        }
    }

}
