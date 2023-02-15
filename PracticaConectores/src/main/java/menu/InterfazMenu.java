package menu;

public class InterfazMenu {

	public static void menuPrincipal(boolean a) {
		modificarTablas(a);
		modificarRegistros();
		gestionarFicheros();

		System.out.println("(0) Cerrar \n");
	}

	public static void modificarTablas(boolean a) {
		if (!a) {
			System.out.println("Modificar tablas");
			System.out.println("-------------------------------------------");
			System.out.println("Para continuar se deben de crear las tablas");
			System.out.println("-------------------------------------------");
			System.out.println("(1) Crear tablas");
			System.out.println("(0) Cerrar");
			System.out.println("-----------------------\n");
		} else {
			System.out.println("Modificar tablas");
			System.out.println("-----------------------");
			System.out.println("(1) Insertar registros");
			System.out.println("(2) Eliminar tablas");
			System.out.println("(3) Modificar registros");
			System.out.println("(4) Gestionar fichero XML");
			System.out.println("(0) Cerrar");
			System.out.println("-----------------------\n");
		}
	}

	public static void modificarRegistros() {
		System.out.println("Modificar registros");
		System.out.println("(1) Crear alumno");
		System.out.println("(2) Eliminar alumno");
		System.out.println("(3) Modificar alumno");
		System.out.println("(4) Mostrar alumnos");
		System.out.println(".......................");
		System.out.println("(5) Crear escuela");
		System.out.println("(6) Eliminar escuela");
		System.out.println("(7) Modificar escuela");
		System.out.println("(8) Mostrar escuelas");
		System.out.println(".......................");
		System.out.println("(0) Cerrar");
		System.out.println("-----------------------\n");
	}

	public static void gestionarFicheros() {
		System.out.println("Gestionra fichero XML");
		System.out.println("-----------------------\n");
		System.out.println("(1) Crear fichero XML");
		System.out.println("(2) Introducir datos a la base de datos XML SAX");
		System.out.println("(3) Introducir datos a la base de datos XML DOM");
		System.out.println(".......................");
		System.out.println("(0) Cerrar");
		System.out.println("-----------------------\n");

	}

	public static void menuFinPrograma() {
		System.err.println("Programa cerrado correctamente");
	}

	public static void menuFinProgramaMal() {
		System.err.println("Programa cerrado de manera forzada");
	}
}
