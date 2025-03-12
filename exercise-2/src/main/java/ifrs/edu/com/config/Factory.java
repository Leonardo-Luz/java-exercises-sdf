package ifrs.edu.com.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Factory {

    public static Connection connect() {
        Connection connection = null;

        // DEFAULT VALUES
        String url = "jdbc:postgresql://localhost:5432/java_revision_exercise_2";
        String password = "postgres";
        String username = "postgres";

        try {
            connection = DriverManager.getConnection(url, password, username);
        } catch (SQLException err) {
            throw new RuntimeException("Server connection error: ", err);
        }

        return connection;
    }
}
