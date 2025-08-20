package www.udbvirtual.edu.sv.desafio01dwf.daos;

import www.udbvirtual.edu.sv.desafio01dwf.models.Puesto;
import www.udbvirtual.edu.sv.desafio01dwf.util.JNDIConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase para acceder a los datos de la tabla Cargos
public class PuestoDao {

    // Obtiene todos los cargos de la base de datos.
    public List<Puesto> getAllPuestos() throws SQLException {
        List<Puesto> puestos = new ArrayList<>();

        String query = "SELECT * FROM Cargos";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Puesto puesto = new Puesto();
                puesto.setIdCargo(resultSet.getInt("idCargo"));
                puesto.setCargo(resultSet.getString("cargo"));
                puesto.setDescripcionCargo(resultSet.getString("descripcionCargo"));
                puesto.setJefatura(resultSet.getBoolean("jefatura"));
                puestos.add(puesto);
            }
        }
        return puestos;
    }
    // Agrega un nuevo cargo a la base de datos.
    public void addPuesto(Puesto puesto) throws SQLException {
        String query = "INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?)";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, puesto.getCargo());
            statement.setString(2, puesto.getDescripcionCargo());
            statement.setBoolean(3, puesto.isJefatura());
            statement.executeUpdate();
        }
    }

    // Obtiene un cargo especifico por su ID.
    public Puesto getPuestoById(int id) throws SQLException {
        Puesto puesto = null;
        String query = "SELECT * FROM Cargos WHERE idCargo = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    puesto = new Puesto();
                    puesto.setIdCargo(resultSet.getInt("idCargo"));
                    puesto.setCargo(resultSet.getString("cargo"));
                    puesto.setDescripcionCargo(resultSet.getString("descripcionCargo"));
                    puesto.setJefatura(resultSet.getBoolean("jefatura"));
                }
            }
        }
        return puesto;
    }
    // Actualiza los datos de un cargo ya existente.
    public void updatePuesto(Puesto puesto) throws SQLException {
        String query = "UPDATE Cargos SET cargo = ?, descripcionCargo = ?, jefatura = ? WHERE idCargo = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, puesto.getCargo());
            statement.setString(2, puesto.getDescripcionCargo());
            statement.setBoolean(3, puesto.isJefatura());
            statement.setInt(4, puesto.getIdCargo());
            statement.executeUpdate();
        }
    }

    // Elimina un cargo por su ID.
    public void deletePuesto(int id) throws SQLException {
        String query = "DELETE FROM Cargos WHERE idCargo = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}