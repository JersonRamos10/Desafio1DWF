package www.udbvirtual.edu.sv.desafio01dwf.daos;

import www.udbvirtual.edu.sv.desafio01dwf.models.Contratacion;
import www.udbvirtual.edu.sv.desafio01dwf.util.JNDIConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase para acceder a los datos de la tabla Contrataciones.
public class ContratacionDao {

    // Obtiene todas las contrataciones de la base de datos.
    public List<Contratacion> getAllContrataciones() throws SQLException {
        List<Contratacion> contrataciones = new ArrayList<>();
        String query = "SELECT * FROM Contrataciones";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Contratacion contratacion = new Contratacion();
                contratacion.setIdContratacion(resultSet.getInt("idContratacion"));
                contratacion.setIdDepartamento(resultSet.getInt("idDepartamento"));
                contratacion.setIdEmpleado(resultSet.getInt("idEmpleado"));
                contratacion.setIdCargo(resultSet.getInt("idCargo"));
                contratacion.setIdTipoContratacion(resultSet.getInt("idTipoContratacion"));
                contratacion.setFechaContratacion(resultSet.getDate("fechaContratacion"));
                contratacion.setSalario(resultSet.getDouble("salario"));
                contratacion.setEstado(resultSet.getBoolean("estado"));
                contrataciones.add(contratacion);
            }
        }
        return contrataciones;
    }

    // Agrega una nueva contratacion a la base de datos.
    public void addContratacion(Contratacion contratacion) throws SQLException {
        String query = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contratacion.getIdDepartamento());
            statement.setInt(2, contratacion.getIdEmpleado());
            statement.setInt(3, contratacion.getIdCargo());
            statement.setInt(4, contratacion.getIdTipoContratacion());
            statement.setDate(5, new Date(contratacion.getFechaContratacion().getTime()));
            statement.setDouble(6, contratacion.getSalario());
            statement.setBoolean(7, contratacion.isEstado());
            statement.executeUpdate();
        }
    }

    // Obtiene una contratacion especifica por su ID.
    public Contratacion getContratacionById(int id) throws SQLException {
        Contratacion contratacion = null;
        String query = "SELECT * FROM Contrataciones WHERE idContratacion = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    contratacion = new Contratacion();
                    contratacion.setIdContratacion(resultSet.getInt("idContratacion"));
                    contratacion.setIdDepartamento(resultSet.getInt("idDepartamento"));
                    contratacion.setIdEmpleado(resultSet.getInt("idEmpleado"));
                    contratacion.setIdCargo(resultSet.getInt("idCargo"));
                    contratacion.setIdTipoContratacion(resultSet.getInt("idTipoContratacion"));
                    contratacion.setFechaContratacion(resultSet.getDate("fechaContratacion"));
                    contratacion.setSalario(resultSet.getDouble("salario"));
                    contratacion.setEstado(resultSet.getBoolean("estado"));
                }
            }
        }
        return contratacion;
    }

    // Actualiza los datos de una contratacion existente.
    public void updateContratacion(Contratacion contratacion) throws SQLException {
        String query = "UPDATE Contrataciones SET idDepartamento = ?, idEmpleado = ?, idCargo = ?, idTipoContratacion = ?, fechaContratacion = ?, salario = ?, estado = ? WHERE idContratacion = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contratacion.getIdDepartamento());
            statement.setInt(2, contratacion.getIdEmpleado());
            statement.setInt(3, contratacion.getIdCargo());
            statement.setInt(4, contratacion.getIdTipoContratacion());
            statement.setDate(5, new Date(contratacion.getFechaContratacion().getTime()));
            statement.setDouble(6, contratacion.getSalario());
            statement.setBoolean(7, contratacion.isEstado());
            statement.setInt(8, contratacion.getIdContratacion());
            statement.executeUpdate();
        }
    }

    // Elimina una contratacion por su ID.
    public void deleteContratacion(int id) throws SQLException {
        String query = "DELETE FROM Contrataciones WHERE idContratacion = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
