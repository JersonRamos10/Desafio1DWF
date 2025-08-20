package www.udbvirtual.edu.sv.desafio01dwf.daos;

import www.udbvirtual.edu.sv.desafio01dwf.models.TipoContratacion;
import www.udbvirtual.edu.sv.desafio01dwf.util.JNDIConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase para acceder a los datos de la tabla TipoContratacion.
public class TipoContratacionDao {

    // Obtiene todos los tipos de contratación de la base de datos.
    public List<TipoContratacion> getAllTiposContratacion() throws SQLException {
        List<TipoContratacion> tiposContratacion = new ArrayList<>();
        String query = "SELECT * FROM TipoContratacion";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                TipoContratacion tipo = new TipoContratacion();
                tipo.setIdTipoContratacion(resultSet.getInt("idTipoContratacion"));
                tipo.setTipoContratacion(resultSet.getString("tipoContratacion"));
                tiposContratacion.add(tipo);
            }
        }
        return tiposContratacion;
    }


    // Agrega un nuevo tipo de contratación a la base de datos.
    public void addTipoContratacion(TipoContratacion tipoContratacion) throws SQLException {
        String query = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tipoContratacion.getTipoContratacion());
            statement.executeUpdate();
        }
    }


    // Obtiene un tipo de contratación específico por su ID.
    public TipoContratacion getTipoContratacionById(int id) throws SQLException {
        TipoContratacion tipo = null;
        String query = "SELECT * FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tipo = new TipoContratacion();
                    tipo.setIdTipoContratacion(resultSet.getInt("idTipoContratacion"));
                    tipo.setTipoContratacion(resultSet.getString("tipoContratacion"));
                }
            }
        }
        return tipo;
    }

    // Actualiza los datos de un tipo de contratación existente.
    public void updateTipoContratacion(TipoContratacion tipoContratacion) throws SQLException {
        String query = "UPDATE TipoContratacion SET tipoContratacion = ? WHERE idTipoContratacion = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tipoContratacion.getTipoContratacion());
            statement.setInt(2, tipoContratacion.getIdTipoContratacion());
            statement.executeUpdate();
        }
    }

    // Elimina un tipo de contratación por su ID.
    public void deleteTipoContratacion(int id) throws SQLException {
        String query = "DELETE FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
