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

    // Agregar un artículo
    public void agregarArticulo(Articulo articulo) throws SQLException {
        String query = "INSERT INTO Articulo (TrackName, Description, UnitPrice, Stock, Category, CurrencyType) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, articulo.getTrackName());
            pstmt.setString(2, articulo.getDescription());
            pstmt.setDouble(3, articulo.getUnitPrice());
            pstmt.setInt(4, articulo.getStock());
            pstmt.setString(5, articulo.getCategory());
            pstmt.setString(6, articulo.getCurrencyType());
            pstmt.executeUpdate();
        }
    }

    // Obtener un artículo por su ID
    public Articulo obtenerArticuloPorId(int id) throws SQLException {
        String query = "SELECT * FROM Articulo WHERE TrackID = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setTrackID(rs.getInt("TrackID"));
                    articulo.setTrackName(rs.getString("TrackName"));
                    articulo.setDescription(rs.getString("Description"));
                    articulo.setUnitPrice(rs.getDouble("UnitPrice"));
                    articulo.setStock(rs.getInt("Stock"));
                    articulo.setCategory(rs.getString("Category"));
                    articulo.setCurrencyType(rs.getString("CurrencyType"));
                    return articulo;
                }
            }
        }
        return null;
    }

    // Obtener todos los artículos
    public List<Articulo> obtenerTodosLosArticulos() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String query = "SELECT * FROM Articulo";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setTrackID(rs.getInt("TrackID"));
                articulo.setTrackName(rs.getString("TrackName"));
                articulo.setDescription(rs.getString("Description"));
                articulo.setUnitPrice(rs.getDouble("UnitPrice"));
                articulo.setStock(rs.getInt("Stock"));
                articulo.setCategory(rs.getString("Category"));
                articulo.setCurrencyType(rs.getString("CurrencyType"));
                articulos.add(articulo);
            }
        }
        return articulos;
    }

    // Actualizar un artículo
    public void actualizarArticulo(Articulo articulo) throws SQLException {
        String query = "UPDATE Articulo SET TrackName = ?, Description = ?, UnitPrice = ?, Stock = ?, Category = ?, CurrencyType = ? WHERE TrackID = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, articulo.getTrackName());
            pstmt.setString(2, articulo.getDescription());
            pstmt.setDouble(3, articulo.getUnitPrice());
            pstmt.setInt(4, articulo.getStock());
            pstmt.setString(5, articulo.getCategory());
            pstmt.setString(6, articulo.getCurrencyType());
            pstmt.setInt(7, articulo.getTrackID());
            pstmt.executeUpdate();
        }
    }

    // Eliminar un artículo por su ID
    public void eliminarArticulo(int id) throws SQLException {
        String query = "DELETE FROM Articulo WHERE TrackID = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

