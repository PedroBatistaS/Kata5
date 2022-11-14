package kata5p1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Kata5P1 {

    public static void main(String[] args) {
        connect();
        SelectApp app = new SelectApp();
        app.selectAll();
        createMailTable();
    }
    
    private static void connect() {
        Connection conn = null;
        try {
            // parámetros de la BD
            String url = "jdbc:sqlite:KATA5.db";
            // creamos una conexión a la BD
            conn = DriverManager.getConnection(url);
            System.out.println("Connexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void createMailTable() {
        // Cadena de conexión SQLite
        String url = "jdbc:sqlite:mail.db";
        // Instrucción SQL para crear nueva tabla
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                    + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + " mail text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement()) {
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
