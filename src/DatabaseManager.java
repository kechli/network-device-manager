import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:network.db";

    public static Connection connect() throws SQLException {
        try {
            // Φόρτωση του Driver στη μνήμη
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("[DB Error] SQLite Driver Class missing: " + e.getMessage());
        }
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS devices (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "name TEXT NOT NULL, " +
                                "ip_address TEXT NOT NULL, " +
                                "type TEXT NOT NULL, " +
                                "is_online INTEGER DEFAULT 0, " +
                                "port_count INTEGER DEFAULT 0);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createTableSQL);
            System.out.println("[DB] Database initialized successfully.");
            
        } catch (SQLException e) {
            System.out.println("[DB Error] " + e.getMessage());
        }
    }
}