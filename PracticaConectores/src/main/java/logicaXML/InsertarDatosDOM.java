package logicaXML;

import java.io.File;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import logicaRegistros.GestionarRegistrosAlumno;
import logicaRegistros.GestionarRegistrosEscuela;
import tablas.Alumno;
import tablas.Escuela;

public class InsertarDatosDOM {
	
	// DOM: Leer XML e insertar los datos del XML en la base de datos.
	public static void introducirDatosXML(String ficheroXML) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(ficheroXML));
			document.getDocumentElement().normalize();
			
			insertarDatosEscuela(document);
			insertarDatosAlumnos(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void insertarDatosEscuela(Document document) throws ClassNotFoundException, SQLException {
		NodeList escuelas = document.getElementsByTagName("Escuela");
		for (int i = 0; i < escuelas.getLength(); i++) {
			Node emple = escuelas.item(i);
			if (emple.getNodeType() == Node.ELEMENT_NODE) {
				
				//Genera una Escuela con información del XML.
				Element elemento = (Element) emple;
				String idEscuela = elemento.getElementsByTagName("Id_Escuela").item(0).getTextContent();
				String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
				String provincia = elemento.getElementsByTagName("Provincia").item(0).getTextContent();

				Escuela escuela = new Escuela(Integer.parseInt(idEscuela), nombre, provincia);
				
				try {
					//Introduce una Escuela. 
					GestionarRegistrosEscuela.insertarRegistro(escuela);
				} catch (Exception e) {
					System.out.println("Error al introducir la escuela: " + escuela.toString());
				}
			}
		}
	}
	
	private static void insertarDatosAlumnos(Document document) throws ClassNotFoundException, SQLException {
		NodeList alumnos = document.getElementsByTagName("Alumno");
		for (int i = 0; i < alumnos.getLength(); i++) {
			Node emple = alumnos.item(i);
			if (emple.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) emple;
				String id = elemento.getElementsByTagName("Id").item(0).getTextContent();
				String idEscuela = elemento.getElementsByTagName("Id_Escuela").item(0).getTextContent();
				String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
				String primerApellido = elemento.getElementsByTagName("Primer_Apellido").item(0).getTextContent();
				String segundoApellido = elemento.getElementsByTagName("Segundo_Apellido").item(0).getTextContent();
				String codigoPostal = elemento.getElementsByTagName("Codigo_Postal").item(0).getTextContent();

				Alumno alumno = new Alumno(Integer.parseInt(id), Integer.parseInt(idEscuela), nombre, primerApellido,
						segundoApellido, Integer.parseInt(codigoPostal));
				try {
					GestionarRegistrosAlumno.insertarRegistro(alumno);
				} catch (Exception e) {
					System.out.println("Error al introducir el alumno: " + alumno.toString());
				}
			}
		}
	}

}
