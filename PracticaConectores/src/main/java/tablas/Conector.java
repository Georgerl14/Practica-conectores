package tablas;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import logicaRegistros.AdministrarProcesos;

public class Conector {
	public static Connection generarConexionMySQL() throws ClassNotFoundException, SQLException {
		// Cargar el driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Establecemos la conexion con la BD.
		return DriverManager.getConnection("jdbc:mysql://localhost/prueba1?allowMultiQueries=true", "root", "root");
	}

	public static boolean comprobarTablas() throws ClassNotFoundException, SQLException {
		Connection conexion = generarConexionMySQL();

		DatabaseMetaData dbmd = conexion.getMetaData();
		ResultSet resul = null;

		//Recoge las tablas de la base de datos pedida.
		resul = dbmd.getTables("prueba1", "root", null, null);

		//Cuenta la cantidad de tablas.
		int cantidadTablas = 0;
		while (resul.next()) {
			cantidadTablas++;
		}

		resul.close();
		conexion.close();
		
		if(cantidadTablas == 2)
			return true;
		else
			return false;
	}
	
	public static boolean comprobarRegistros() throws ClassNotFoundException, SQLException {
		AdministrarProcesos.mostrarAlumnos();
		AdministrarProcesos.mostrarEscuelas();
	
		return false;
	}
}
