package logicaRegistros;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import tablas.Conector;

public class GestionarRegistros {

	// Metodo para introducir consultas con Execute
	protected static void metodoExecute(String consulta) throws ClassNotFoundException, SQLException {
		Connection conexion = Conector.generarConexionMySQL();
		Statement sentencia = conexion.createStatement();

		sentencia.execute(consulta);

		sentencia.close();
		conexion.close();
	}

	// Metodo para introducir consultas con ExecuteUpdate
	protected static void realizarExecuteUpdate(String consulta) throws ClassNotFoundException, SQLException {
		Connection conexion = Conector.generarConexionMySQL();
		Statement sentencia = conexion.createStatement();

		sentencia.executeUpdate(consulta);

		sentencia.close();
		conexion.close();
	}

}
