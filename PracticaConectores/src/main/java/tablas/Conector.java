package tablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	public static Connection generarConexionMySQL() throws ClassNotFoundException, SQLException {
		// Cargar el driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Establecemos la conexion con la BD.
		return DriverManager.getConnection("jdbc:mysql://localhost/Escuela?allowMultiQueries=true", "root", "root");
	}

}
