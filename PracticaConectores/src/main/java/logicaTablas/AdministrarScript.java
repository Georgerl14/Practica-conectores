package logicaTablas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import logicaRegistros.GestionarRegistros;

public class AdministrarScript extends GestionarRegistros {
	
	public static void main(String[] args) {
//		ejecutarCrearTablas();
//		ejecutarInsertarDatosEjemplo();
	}
	
	// Ejecuta el script para crear tablas.
	public static void ejecutarCrearTablas() throws ClassNotFoundException, SQLException, IOException {
		ejecutarScript(".\\src\\main\\java\\logicaTablas\\scriptCrearTablas.sql");
	}

	// Ejecuta el script para eliminar tablas.
	public static void ejecutarEliminarTablas() throws ClassNotFoundException, SQLException, IOException {
		ejecutarScript(".\\src\\main\\java\\logicaTablas\\scriptEliminarTablas.sql");
	}

	// Ejecuta el script para crear regitros de ejemplo.
	public static void ejecutarInsertarDatosEjemplo() throws ClassNotFoundException, SQLException, IOException {
		ejecutarScript(".\\src\\main\\java\\logicaTablas\\scriptInsertarDatosEjemplo.sql");
	}

	private static void ejecutarScript(String ficheroSQL) throws ClassNotFoundException, SQLException, IOException {
		// Obtener el fichero con las consultas.
		File FicheroScript = new File(ficheroSQL);

		try (BufferedReader entrada = new BufferedReader(new FileReader(FicheroScript));) {
			String linea = null;
			StringBuilder consulta = new StringBuilder();

			// Leer la entrada de datos.
			while ((linea = entrada.readLine()) != null) {
				// Almacenar la consulta.
				consulta.append(linea);
			}

			// Realizar la consulta.
			realizarExecuteUpdate(consulta.toString());
		}

	}
}
