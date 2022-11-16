package kata5p1;

import java.util.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Kata5P1 {
    
    private static InsertarDatosTabla idt;

    public static void main(String[] args) {
        connect();
        SelectApp app = new SelectApp();
        app.selectAll();
        createMailTable();
        String fileName = "C:\\Users\\Pedro\\Desktop\\PRÁCTICAS IS2\\Kata5P1\\email.txt";
        List<String> mails = new MailListReader().read(fileName);
        idt = new InsertarDatosTabla();
        idt.createMailTable();
        idt.connect();
        for (String mail : mails) {
            idt.insert(mail);
        }
        
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
    
    public static class InsertarDatosTabla {
        //direcc_email
        private void createMailTable() {
        // Cadena de conexión SQLite
        String url = "jdbc:sqlite:mail.db";
        // Instrucción SQL para crear nueva tabla
        String sql = "CREATE TABLE IF NOT EXISTS direcc_email (\n"
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
     
        private Connection connect() {
            String url = "jdbc:sqlite:mail.db";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }

        public void insert(String email) {
            String sql = "INSERT INTO direcc_email(mail) VALUES(?)";
            try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
   
}
}
