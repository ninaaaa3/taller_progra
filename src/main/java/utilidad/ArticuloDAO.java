package utilidad;

import com.mycompany.taller_progra.model.Articulo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO {
    private Connection conexion;

    public ArticuloDAO() {
        this.conexion = ConexionBD.getInstancia().getConexion();
    }

    public void agregarArticulo(Articulo articulo) throws SQLException {
        String query = "INSERT INTO Articulo (nombre, descripcion, precio, moneda) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, articulo.getNombre());
            pstmt.setString(2, articulo.getDescripcion());
            pstmt.setDouble(3, articulo.getPrecio());
            pstmt.setString(4, articulo.getMoneda());
            pstmt.executeUpdate();
        }
    }

    public Articulo obtenerArticuloPorId(int id) throws SQLException {
        String query = "SELECT * FROM Articulo WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId(rs.getInt("id"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    articulo.setMoneda(rs.getString("moneda"));
                    return articulo;
                }
            }
        }
        return null;
    }

    public List<Articulo> obtenerTodosLosArticulos() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String query = "SELECT * FROM Articulo";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setPrecio(rs.getDouble("precio"));
                articulo.setMoneda(rs.getString("moneda"));
                articulos.add(articulo);
            }
        }
        return articulos;
    }

    public void actualizarArticulo(Articulo articulo) throws SQLException {
        String query = "UPDATE Articulo SET nombre = ?, descripcion = ?, precio = ?, moneda = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, articulo.getNombre());
            pstmt.setString(2, articulo.getDescripcion());
            pstmt.setDouble(3, articulo.getPrecio());
            pstmt.setString(4, articulo.getMoneda());
            pstmt.setInt(5, articulo.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarArticulo(int id) throws SQLException {
        String query = "DELETE FROM Articulo WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
