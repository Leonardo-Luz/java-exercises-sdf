package ifrs.edu.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

enum Database {
    PRODUCTION, DEVELOPMENT, TEST
}

public class ConnectionFactory {
    private static Connection connection;
    private final static Dotenv dotenv = Dotenv.load();

    public static Connection connect(Database database) throws SQLException {
        String url = "";
        String user = "";
        String password = "";

        switch (database) {
            case PRODUCTION:
                url = dotenv.get("PRODUCTION_POSTGRES_DATABASE_URL");
                user = dotenv.get("PRODUCTION_POSTGRES_DATABASE_USER");
                password = dotenv.get("PRODUCTION_POSTGRES_DATABASE_PASSWORD");
                break;
            case DEVELOPMENT:
                url = dotenv.get("DEVELOPMENT_POSTGRES_DATABASE_URL");
                user = dotenv.get("DEVELOPMENT_POSTGRES_DATABASE_USER");
                password = dotenv.get("DEVELOPMENT_POSTGRES_DATABASE_PASSWORD");
                break;
            case TEST:
                url = dotenv.get("TEST_POSTGRES_DATABASE_URL");
                user = dotenv.get("TEST_POSTGRES_DATABASE_USER");
                password = dotenv.get("TEST_POSTGRES_DATABASE_PASSWORD");
                break;
        }

        return ConnectionFactory.connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return ConnectionFactory.connection;
    }
}
