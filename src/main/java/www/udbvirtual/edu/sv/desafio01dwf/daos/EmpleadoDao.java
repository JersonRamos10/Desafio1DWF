package www.udbvirtual.edu.sv.desafio01dwf.daos;

import www.udbvirtual.edu.sv.desafio01dwf.models.Empleado;
import www.udbvirtual.edu.sv.desafio01dwf.util.JNDIConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase para acceder a los datos de la tabla Empleados.
public class EmpleadoDao {

    // Obtiene todos los empleados de la base de datos.
    public List<Empleado> getAllEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        String query = "SELECT * FROM Empleados";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
                empleado.setNumeroDui(resultSet.getString("numeroDui"));
                empleado.setNombrePersona(resultSet.getString("nombrePersona"));
                empleado.setUsuario(resultSet.getString("usuario"));
                empleado.setNumeroTelefono(resultSet.getString("numeroTelefono"));
                empleado.setCorreoInstitucional(resultSet.getString("correoInstitucional"));
                empleado.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    // Agrega un nuevo empleado a la base de datos.
    public void addEmpleado(Empleado empleado) throws SQLException {

        String query = "INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, empleado.getNumeroDui());
            statement.setString(2, empleado.getNombrePersona());
            statement.setString(3, empleado.getUsuario());
            statement.setString(4, empleado.getNumeroTelefono());
            statement.setString(5, empleado.getCorreoInstitucional());
            statement.setDate(6, new Date(empleado.getFechaNacimiento().getTime()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        System.out.println("--- DEBUG: ID asignado por BD: " + newId + " ---");
                        empleado.setIdEmpleado(newId);
                    }
                }
            }
        }
    }

    // Verifica si un DUI ya existe para evitar duplicados.
    public boolean duiExists(String numeroDui) throws SQLException {
        // CORRECCIÓN: Se usa el nombre de la tabla "Empleados" con mayúscula.
        String query = "SELECT COUNT(*) FROM Empleados WHERE numeroDui = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, numeroDui);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Obtiene un empleado especifico por su ID.
    public Empleado getEmpleadoById(int id) throws SQLException {
        Empleado empleado = null;
        // CORRECCIÓN: Se usa el nombre de la tabla "Empleados" con mayúscula.
        String query = "SELECT * FROM Empleados WHERE idEmpleado = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = new Empleado();
                    empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
                    empleado.setNumeroDui(resultSet.getString("numeroDui"));
                    empleado.setNombrePersona(resultSet.getString("nombrePersona"));
                    empleado.setUsuario(resultSet.getString("usuario"));
                    empleado.setNumeroTelefono(resultSet.getString("numeroTelefono"));
                    empleado.setCorreoInstitucional(resultSet.getString("correoInstitucional"));
                    empleado.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                }
            }
        }
        return empleado;
    }

    // Actualiza los datos de un empleado existente.
    public void updateEmpleado(Empleado empleado) throws SQLException {

        String query = "UPDATE Empleados SET numeroDui = ?, nombrePersona = ?, usuario = ?, numeroTelefono = ?, correoInstitucional = ?, fechaNacimiento = ? WHERE idEmpleado = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, empleado.getNumeroDui());
            statement.setString(2, empleado.getNombrePersona());
            statement.setString(3, empleado.getUsuario());
            statement.setString(4, empleado.getNumeroTelefono());
            statement.setString(5, empleado.getCorreoInstitucional());
            statement.setDate(6, new Date(empleado.getFechaNacimiento().getTime()));
            statement.setInt(7, empleado.getIdEmpleado());
            statement.executeUpdate();
        }
    }

    // Elimina un empleado por su numero de DUI.
    public void deleteEmpleado(String id) throws SQLException {

        String query = "DELETE FROM Empleados WHERE numeroDui = ?";
        try (Connection connection = JNDIConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }
}