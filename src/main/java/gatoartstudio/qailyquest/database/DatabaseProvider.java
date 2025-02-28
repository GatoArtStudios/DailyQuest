package gatoartstudio.qailyquest.database;

// Interfaz or class that provides the database
public interface DatabaseProvider {
    void connect();

    void createTables();

    void disconnect();

    boolean run_query(String query, Object... args);
}
