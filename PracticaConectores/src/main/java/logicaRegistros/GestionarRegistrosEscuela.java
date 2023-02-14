package logicaRegistros;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tablas.Conector;
import tablas.Escuela;

public class GestionarRegistrosEscuela extends GestionarRegistros {

	public static void insertarRegistro(Escuela escuelas) throws ClassNotFoundException, SQLException {
		String valoresConsulta = escuelas.getIdEscuela() + ",'" + escuelas.getNombre() + "','" + escuelas.getProvincia()
				+ "'";
		String consulta = "INSERT INTO escuelas VALUES(" + valoresConsulta + ");";
		metodoExecute(consulta);
	}

	public static void eliminarRegistro(Escuela escuelas) throws ClassNotFoundException, SQLException {
		String consulta = "DELETE FROM escuelas WHERE Id_Escuela = " + escuelas.getIdEscuela() + ";";
		metodoExecute(consulta);
	}

	public static void modificarNombre(Escuela escuelas, String valorActual)
			throws ClassNotFoundException, SQLException {
		String encontrarId = " WHERE Id_Escuela=" + escuelas.getIdEscuela() + ";";
		String consulta = "UPDATE escuelas SET Nombre='" + valorActual + encontrarId;
		realizarExecuteUpdate(consulta);
	}

	public static void modificarProvincia(Escuela escuelas, String valorActual)
			throws ClassNotFoundException, SQLException {
		String encontrarId = " WHERE Id_Escuela=" + escuelas.getIdEscuela() + ";";
		String consulta = "UPDATE escuelas SET Provincia='" + valorActual + encontrarId;
		realizarExecuteUpdate(consulta);
	}

	public static void modificarTodo(Escuela escuelas) throws ClassNotFoundException, SQLException {
		String encontrarId = "' WHERE Id_Escuela=" + escuelas.getIdEscuela() + ";";
		String consultaUpdate = "UPDATE escuelas SET ";
		String consulta = consultaUpdate + "Nombre='" + escuelas.getNombre() + ", Provincia='" + escuelas.getProvincia()
				+ encontrarId;
		realizarExecuteUpdate(consulta);
	}

	// Metodo para introducir consultas con ExecuteQuery
	public static void metodoExecuteQuery(int IdEscuela) throws ClassNotFoundException, SQLException {
		Connection conexion = Conector.generarConexionMySQL();
		Statement sentencia = conexion.createStatement();

		ResultSet resul = sentencia.executeQuery("Select * from escuelas where Id_Escuela = " + IdEscuela);
		while (resul.next()) {
			System.out.printf("%d, %s, %s ", resul.getInt(1), resul.getString(2), resul.getString(3));
		}

		resul.close();
		sentencia.close();
		conexion.close();
	}
}
