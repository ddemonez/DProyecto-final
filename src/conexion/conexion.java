package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String URL = "jdbc:mariadb://localhost:3333/taller_moto";
    private static final String USER = "root";
    private static final String PASSWORD = "porsche911$";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(" Error de conexión: " + e.getMessage());
        }
        return con;
    }
}
