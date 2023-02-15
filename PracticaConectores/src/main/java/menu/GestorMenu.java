package menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logicaRegistros.AdministrarProcesos;
import logicaRegistros.GestionarRegistrosAlumno;
import logicaRegistros.GestionarRegistrosEscuela;
import logicaTablas.AdministrarScript;
import logicaXML.CrearXmlDOM;
import logicaXML.InsertarDatosDOM;
import logicaXML.InsertarDatosSAX;
import tablas.Alumno;
import tablas.Conector;
import tablas.Escuela;

public class GestorMenu {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, SAXException, ParserConfigurationException {
		int opcion = 0, minimo = 0, maximo = 4;
		boolean datosInsertador = false;

		// Comprueba si las tablas estan en la base de datos.
		if (!Conector.comprobarTablas()) {
			menuCrearTablas();
		}

		do {
			// Muestra interfaz de modificaciones completa.
			InterfazMenu.modificarTablas(true);

			// Eligir opcion.
			switch (opcion = introducirOpcion(0, 4)) {
			case 1:
				try {
					AdministrarScript.ejecutarInsertarDatosEjemplo();
					System.out.println("Datos insertados correctemante.");
				} catch (Exception e) {
					System.out.println("Los datos ya fueron introducidos previamente.");
				}
				break;

			case 2:
				try {
					AdministrarScript.ejecutarEliminarTablas();
					System.out.println("Tablas eliminadas correctamente.");
					menuCrearTablas();
				} catch (ClassNotFoundException | SQLException | IOException e) {
					System.out.println("Las tablas no se pudieron eliminar.");
				}

				break;

			case 3:
				gestorModificarRegistros();
				break;

			case 4:
				gestorXML();
				break;
			}
		} while (opcion != 0);
		System.out.println("Programa cerrado correctamente.");
	}

	private static void gestorXML() throws ClassNotFoundException, SQLException, IOException, SAXException, ParserConfigurationException {
		int opcion = 0;
		do {
			InterfazMenu.gestionarFicheros();
			switch (opcion = introducirOpcion(0, 3)) {
			case 1:
				try {
					CrearXmlDOM.generarFichero();
					System.out.println("Se ha creado el fichero XML.");
				} catch (Exception e) {
					System.out.println("Se produjo un error al crear el fichero XML ");
				}
				break;

			case 2:
				try {
				InsertarDatosDOM.introducirDatosXML("Escuelas2.xml");
				} catch(Exception e) {
					System.out.println("Se produjo un error al insertar el fichero XML");
				}
				
				break;

			case 3:
				try {
				InsertarDatosSAX.insertarDatosTabla("Escuelas3.xml");
				} catch(Exception e) {
					System.out.println("Se produjo un error al insertar el fichero XML");
				}
				break;
			}
		} while (opcion != 0);
	}

	private static void gestorModificarRegistros() throws ClassNotFoundException, SQLException, IOException {
		int opcion = 0;
		do {
			InterfazMenu.modificarRegistros();
			switch (opcion = introducirOpcion(0, 8)) {
			case 1:
				GestionarRegistrosAlumno.insertarRegistro(crearAlumno());
				break;

			case 2:
				System.out.println("Id alumno que quiere eliminar:");
				System.out.println("(0) Volver");
				opcion = introducirOpcion(0, 9999);
				if (opcion != 0) {
					GestionarRegistrosAlumno.eliminarRegistro(opcion);
					System.out.println("Alumno eliminado");
				}
				break;

			case 3:
				gestorModificarRegistros();
				break;

			case 4:
				AdministrarProcesos.mostrarAlumnos();
				break;

			case 5:
				GestionarRegistrosEscuela.insertarRegistro(crearEscuela());
				break;

			case 6:
				System.out.println("Id escuela que quiere eliminar:");
				System.out.println("(0) Volver");
				opcion = introducirOpcion(0, 9999);
				if (opcion != 0) {
					GestionarRegistrosEscuela.eliminarRegistro(opcion);
					System.out.println("Escuela eliminado");
				}
				break;

			case 7:
				gestorModificarRegistros();
				break;

			case 8:
				AdministrarProcesos.mostrarEscuelas();
				break;
			}
		} while (opcion != 0);
	}

	private static void menuCrearTablas() throws ClassNotFoundException, SQLException, IOException {
		// Muestra la interfaz para crear tablas.
		InterfazMenu.modificarTablas(false);

		if (introducirOpcion(0, 1) == 1) {
			// Crea las tablas.
			AdministrarScript.ejecutarCrearTablas();

			System.out.println("\nTablas creadas correctamente");
		} else {
			// Cerrar el programa.
			cerrarPrograma();
		}
	}

	private static void cerrarPrograma() {
		System.out.println("Programa cerrado correctamente.");
		System.exit(0);
	}

	private static Escuela crearEscuela() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Id escuela: ");
		int id_Escuela = Integer.parseInt(sc.nextLine());
		System.out.println("Nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Provincia: ");
		String provincia = sc.nextLine();

		return new Escuela(id_Escuela, nombre, provincia);
	}

	private static Alumno crearAlumno() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Id: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Id escuela: ");
		int id_Escuela = Integer.parseInt(sc.nextLine());
		System.out.println("Nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Primer apellido: ");
		String primerApellido = sc.nextLine();

		System.out.println("Segundo apellido: ");
		String segundoApellido = sc.nextLine();
		System.out.println("Codigo postal: ");
		int codigoPostal = Integer.parseInt(sc.nextLine());
		return new Alumno(id, id_Escuela, nombre, primerApellido, segundoApellido, codigoPostal);
	}

	private static int introducirOpcion(int minimo, int maximo) {
		Scanner sc = new Scanner(System.in);
		int resultado = 0;

		do {
			System.out.print("[>] ");
			resultado = Integer.parseInt(sc.nextLine());
		} while (resultado < minimo || resultado > maximo);

		return resultado;
	}
}
