package conexion;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        // Llamamos al método estático de la clase conexion
        Connection con = conexion.getConnection();

        // Verificamos si la conexión fue exitosa
        if (con != null) {
            System.out.println("Prueba completada: conexión establecida correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}
