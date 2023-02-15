package logicaRegistros;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tablas.Alumno;
import tablas.Conector;

public class GestionarRegistrosAlumno extends GestionarRegistros {

	public static void insertarRegistro(Alumno alumno) throws ClassNotFoundException, SQLException {
		String valoresConsulta = alumno.getId() + "," + alumno.getIdEscuela() + ",'" + alumno.getNombre() + "','"
				+ alumno.getPrimerApellido() + "','" + alumno.getSegundoApellido() + "'," + alumno.getCodigoPostal();

		String consulta = "INSERT INTO alumnos VALUES(" + valoresConsulta + ");";
		metodoExecute(consulta);
	}

	public static void eliminarRegistro(int id) throws ClassNotFoundException, SQLException {
		String consulta = "DELETE FROM alumnos WHERE id = " + id + ";";
		metodoExecute(consulta);
	}

	public static void modificarIdEscuela(Alumno alumno, String valorActual)
			throws ClassNotFoundException, SQLException {
		String consulta = modificarConsulta(alumno, 0, valorActual);
		realizarExecuteUpdate(consulta);
	}

	public static void modificarNombre(Alumno alumno, String valorActual) throws ClassNotFoundException, SQLException {
		String consulta = modificarConsulta(alumno, 1, valorActual);
		realizarExecuteUpdate(consulta);
	}

	public static void modificarPrimerApellido(Alumno alumno, String valorActual)
			throws ClassNotFoundException, SQLException {
		String consulta = modificarConsulta(alumno, 2, valorActual);
		realizarExecuteUpdate(consulta);
	}

	public static void modificarSegundoApellido(Alumno alumno, String valorActual)
			throws ClassNotFoundException, SQLException {
		String consulta = modificarConsulta(alumno, 3, valorActual);
		realizarExecuteUpdate(consulta);
	}

	public static void modificarCodigoPostal(Alumno alumno, String valorActual)
			throws ClassNotFoundException, SQLException {
		String consulta = modificarConsulta(alumno, 4, valorActual);
		realizarExecuteUpdate(consulta);
	}

	public static void modificarTodo(Alumno alumno) throws ClassNotFoundException, SQLException {
		String consulta = modificarConsulta(alumno, 5, String.valueOf(alumno.getIdEscuela()));
		realizarExecuteUpdate(consulta);
	}

	private static String modificarConsulta(Alumno alumno, int opcion, String valorActual) {
		String consultaUpdate = "UPDATE alumnos SET";
		String consultaWhere = " WHERE Id= " + alumno.getId() + ";";

		switch (opcion) {
		case 0:
			return consultaUpdate + " Id_Escuela" + "=" + valorActual + consultaWhere;
		case 1:
			return consultaUpdate + " Nombre" + "='" + valorActual + "'" + consultaWhere;
		case 2:
			return consultaUpdate + " PrimerApellido" + "='" + valorActual + "'" + consultaWhere;
		case 3:
			return consultaUpdate + " SegundoApellido" + "='" + valorActual + "'" + consultaWhere;
		case 4:
			return consultaUpdate + " codigoPostal" + "=" + valorActual + consultaWhere;
		case 5:
			return consultaUpdate + generarConsulta(alumno) + consultaWhere;
		default:
			return null;
		}
	}

	private static String generarConsulta(Alumno alumno) {
		String valorActual;
		valorActual = alumno.datosActualizar();
		String partesAlumno[] = valorActual.split("-");
		String consultaUpdateTotal = " Id_Escuela" + "= " + partesAlumno[1] + ", " + " Nombre" + "='" + partesAlumno[2]
				+ "', " + " PrimerApellido" + "='" + partesAlumno[3] + "', " + " SegundoApellido" + "='"
				+ partesAlumno[4] + "', " + " codigoPostal" + "=" + partesAlumno[5];
		return consultaUpdateTotal;
	}

	// Metodo para introducir consultas con ExecuteQuery
	public static void metodoExecuteQuery(int id) throws ClassNotFoundException, SQLException {
		Connection conexion = Conector.generarConexionMySQL();
		Statement sentencia = conexion.createStatement();

		ResultSet resul = sentencia.executeQuery("Select * from alumnos where id = " + id);
		while (resul.next()) {
			System.out.printf("%d, %d, %s, %s, %s, %d", resul.getInt(1), resul.getInt(2), resul.getString(3),
					resul.getString(4), resul.getString(5), resul.getInt(6));
		}

		resul.close();
		sentencia.close();
		conexion.close();
	}
}
