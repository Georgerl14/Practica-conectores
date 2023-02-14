package logicaRegistros;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tablas.Conector;

public class AdministrarProcesos {

	public static void mostrarAlumnos() throws ClassNotFoundException, SQLException {
		procesoSinParametro("{ call mostrarAlumnos}");
	}

	public static void mostrarEscuelas() throws ClassNotFoundException, SQLException {
		procesoSinParametro("{ call mostrarEscuelas}");
	}

	public static void mostrarAlumnoPorId(int id) {
		procesoUnParametro("{ call mostrarAlumno_Id (?)}", id);
	}

	public static void mostrarEscuelaPorId(int id) {
		procesoUnParametro("{ call mostrarEscuela_Id (?)}", id);
	}

	private static void procesoSinParametro(String consulta) throws ClassNotFoundException, SQLException {
		Connection conexion = Conector.generarConexionMySQL();

		CallableStatement llamada = conexion.prepareCall(consulta);
		mostrarResultado(conexion, llamada);
	}

	private static void procesoUnParametro(String consulta, int id) {
		try {
			Connection conexion = Conector.generarConexionMySQL();

			// Preparamos la llamada
			CallableStatement llamada = conexion.prepareCall(consulta);

			// Damos valor a los argumentos
			llamada.setInt(1, id);

			mostrarResultado(conexion, llamada);
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void mostrarResultado(Connection conexion, CallableStatement llamada) throws SQLException {
		ResultSet resul = llamada.executeQuery();
		while (resul.next()) {
			System.out.printf("%d %d %s %s %s %d %n", resul.getInt(1), resul.getInt(2), resul.getString(3),
					resul.getString(4), resul.getString(5), resul.getInt(6));
		}

		llamada.close();
		conexion.close();
	}
}
