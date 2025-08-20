package www.udbvirtual.edu.sv.desafio01dwf.daos;

import www.udbvirtual.edu.sv.desafio01dwf.models.Departamento;
import www.udbvirtual.edu.sv.desafio01dwf.util.JNDIConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase para acceder a los datos de la tabla Departamento.

public class DepartamentoDao {
    // Obtiene todos los departamentos de la base de datos.
    public List<Departamento> getAllDepartamentos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM Departamento";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(resultSet.getInt("idDepartamento"));
                departamento.setNombreDepartamento(resultSet.getString("nombreDepartamento"));
                departamento.setDescripcionDepartamento(resultSet.getString("descripcionDepartamento"));
                departamentos.add(departamento);
            }
        }
        return departamentos;
    }

    // Agrega un nuevo departamento a la base de datos.
    public void addDepartamento(Departamento departamento) throws SQLException {
        String query = "INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?)";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departamento.getNombreDepartamento());
            statement.setString(2, departamento.getDescripcionDepartamento());
            statement.executeUpdate();
        }
    }


    // Obtiene un departamento especifico por su ID.
    public Departamento getDepartamentoById(int id) throws SQLException {
        Departamento departamento = null;
        String query = "SELECT * FROM Departamento WHERE idDepartamento = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    departamento = new Departamento();
                    departamento.setIdDepartamento(resultSet.getInt("idDepartamento"));
                    departamento.setNombreDepartamento(resultSet.getString("nombreDepartamento"));
                    departamento.setDescripcionDepartamento(resultSet.getString("descripcionDepartamento"));
                }
            }
        }
        return departamento;
    }

    // Actualiza los datos de un departamento existente.
    public void updateDepartamento(Departamento departamento) throws SQLException {
        String query = "UPDATE Departamento SET nombreDepartamento = ?, descripcionDepartamento = ? WHERE idDepartamento = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departamento.getNombreDepartamento());
            statement.setString(2, departamento.getDescripcionDepartamento());
            statement.setInt(3, departamento.getIdDepartamento());
            statement.executeUpdate();
        }
    }

    // Elimina un departamento por su ID.
    public void deleteDepartamento(int id) throws SQLException {
        String query = "DELETE FROM Departamento WHERE idDepartamento = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}