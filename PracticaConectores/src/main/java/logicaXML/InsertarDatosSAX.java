package logicaXML;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import logicaRegistros.GestionarRegistrosAlumno;
import logicaRegistros.GestionarRegistrosEscuela;
import tablas.Alumno;
import tablas.Escuela;


public class InsertarDatosSAX {

	public static void insertarDatosTabla() throws IOException, SAXException, ParserConfigurationException {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		XMLReader procesadorXML = parser.getXMLReader();

		GestionContenido gestor = new GestionContenido();
		procesadorXML.setContentHandler(gestor);

		InputSource fileXML = new InputSource("Escuelas.xml");
		procesadorXML.parse(fileXML);
	}
}

class GestionContenido extends DefaultHandler {
	int valoresIntroducidos = 0;
	String contenidoEscuela = "";
	String contenidoAlumno = "";
	Boolean cambio = false;

	public GestionContenido() {
		super();
	}

	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		if (nombreC.equals("Escuela"))
			cambio = false;
		else if (nombreC.equals("Alumno"))
			cambio = true;
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String(ch, inicio, longitud);

		if (!cambio) {
			if (!car.isBlank()) {
				contenidoEscuela += car + " ";
				valoresIntroducidos++;
			}

			if (valoresIntroducidos == 3) {
				Escuela escuela = crearEscuela();

				try {
					GestionarRegistrosEscuela.insertarRegistro(escuela);
				} catch (ClassNotFoundException | SQLException e) {
					try {
						GestionarRegistrosEscuela.modificarTodo(escuela);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
				mostrarEscuela(escuela);

				contenidoEscuela = "";
				valoresIntroducidos = 0;
			}
		} else {
			if (!car.isBlank()) {
				contenidoAlumno += car + " ";
				valoresIntroducidos++;
			}

			if (valoresIntroducidos == 6) {
				Alumno alumno = crearAlumno();

				try {
					GestionarRegistrosAlumno.insertarRegistro(alumno);
				} catch (ClassNotFoundException | SQLException e) {

					try {
						GestionarRegistrosAlumno.modificarTodo(alumno);

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}

				mostrarAlumno(alumno);

				contenidoAlumno = "";
				valoresIntroducidos = 0;
			}
		}
	}

	private Escuela crearEscuela() {
		String parteEscuela[] = contenidoEscuela.split(" ");

		int idEscuela = Integer.parseInt(parteEscuela[0]);
		String nombre = parteEscuela[1].replace("_", " ");
		String provincia = parteEscuela[2].replace("_", " ");

		return new Escuela(idEscuela, nombre, provincia);
	}

	private void mostrarEscuela(Escuela escuela) {
		System.out.println(escuela + " \n");
	}

	private Alumno crearAlumno() {
		String parteAlumno[] = contenidoAlumno.split(" ");

		int id = Integer.parseInt(parteAlumno[0]);
		int idEscuela = Integer.parseInt(parteAlumno[1]);
		String nombre = parteAlumno[2].replace("_", " ");
		String primerApellido = parteAlumno[3].replace("_", " ");
		String segundoApellido = parteAlumno[4].replace("_", " ");
		int codigoPostal = Integer.parseInt(parteAlumno[5]);

		return new Alumno(id, idEscuela, nombre, primerApellido, segundoApellido, codigoPostal);
	}

	private void mostrarAlumno(Alumno alumno) {
		System.out.println(alumno + "\n");
	}
}
