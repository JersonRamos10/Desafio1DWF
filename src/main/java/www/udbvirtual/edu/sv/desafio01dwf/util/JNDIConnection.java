package www.udbvirtual.edu.sv.desafio01dwf.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * aca se gestiona la obtencion de conexiones a la base de datos
 * a través del pool de conexiones JNDI configurado en el servidor Payara.
 */

// Clase para gestionar la conexion a la base de datos mediante JNDI.
public class JNDIConnection {

    // Bloque estatico que se ejecuta una sola vez al cargar la clase.
    private static DataSource dataSource;

    static {
        try {
            Context ctx = new InitialContext();
            // Se busca el pool de conexiones configurado en Payara con el nombre "jdbc/mysql".
            dataSource = (DataSource) ctx.lookup("jdbc/mysql");
        } catch (NamingException e) {
            System.err.println("Error al buscar el DataSource JNDI global: jdbc/mysql");
            e.printStackTrace();
        }
    }

    // Metodo para obtener una conexion del pool.
    public static Connection getConnection() throws SQLException {
        // Valida que el DataSource se haya inicializado correctamente.
        if (dataSource == null) {
            throw new SQLException("DataSource no inicializado. Revisa el nombre del Recurso JDBC en Payara.");
        }
        // Retorna una conexion activa desde el pool de Payara.
        return dataSource.getConnection();
    }
}