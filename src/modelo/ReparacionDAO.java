package modelo;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReparacionDAO {

    public boolean insertar(Reparacion r) {
        String sql = "INSERT INTO reparaciones(nombre_cliente, modelo_moto, tipo_servicio, costo, fecha_ingreso) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getNombre_cliente());
            ps.setString(2, r.getModelo_moto());
            ps.setString(3, r.getTipo_servicio());
            ps.setDouble(4, r.getCosto());
            ps.setString(5, r.getFecha_ingreso());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public List<Reparacion> listar() {
        List<Reparacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM reparaciones";
        try (Connection con = conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Reparacion r = new Reparacion(
                    rs.getInt("codigo_servicio"),
                    rs.getString("nombre_cliente"),
                    rs.getString("modelo_moto"),
                    rs.getString("tipo_servicio"),
                    rs.getDouble("costo"),
                    rs.getString("fecha_ingreso")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Reparacion r) {
        String sql = "UPDATE reparaciones SET nombre_cliente=?, modelo_moto=?, tipo_servicio=?, costo=?, fecha_ingreso=? WHERE codigo_servicio=?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getNombre_cliente());
            ps.setString(2, r.getModelo_moto());
            ps.setString(3, r.getTipo_servicio());
            ps.setDouble(4, r.getCosto());
            ps.setString(5, r.getFecha_ingreso());
            ps.setInt(6, r.getCodigo_servicio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM reparaciones WHERE codigo_servicio=?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
