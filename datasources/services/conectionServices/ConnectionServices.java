package conectionServices;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constant.Constant;

public class ConnectionServices {

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create and return the connection
            return DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new SQLException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
