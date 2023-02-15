package logicaXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import tablas.Alumno;
import tablas.Conector;

public class CrearXmlDOM {
	
	public static void generarFichero() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Sistema_educativo", null);
			document.setXmlVersion("1.0");

			Connection conexion = Conector.generarConexionMySQL();
			Statement sentencia = conexion.createStatement();

			ResultSet resul = sentencia.executeQuery("Select * from alumnos");
			List<Alumno> listaAlumnos = new ArrayList<>();
			while (resul.next()) {
				listaAlumnos.add(new Alumno(resul.getInt(1), resul.getInt(2), resul.getString(3),
						resul.getString(4), resul.getString(5), resul.getInt(6)));
			}

			resul = sentencia.executeQuery("Select * from escuelas");
			while (resul.next()) {
				String Id_Escuela = String.valueOf(resul.getInt(1));
				String Nombre = resul.getString(2).replace(" ", "_");
				String Provincia = resul.getString(3).replace(" ","_");

				Element raiz = document.createElement("Escuela");
				document.getDocumentElement().appendChild(raiz);
				CrearElemento("Id_Escuela", Id_Escuela, raiz, document);
				CrearElemento("Nombre", Nombre, raiz, document);
				CrearElemento("Provincia", Provincia, raiz, document);
				
				Element elem = document.createElement("Lista_alumnos");
				raiz.appendChild(elem);
				
				for (int i = 0; i < listaAlumnos.size(); i++) {
					if (listaAlumnos.get(i).getIdEscuela() == resul.getInt(1)) {
						Element subraiz = document.createElement("Alumno");
						elem.appendChild(subraiz);
						CrearElemento("Id", String.valueOf(listaAlumnos.get(i).getId()), subraiz, document);
						CrearElemento("Id_Escuela", String.valueOf(listaAlumnos.get(i).getIdEscuela()), subraiz, document);
						CrearElemento("Nombre", listaAlumnos.get(i).getNombre(), subraiz, document);
						CrearElemento("Primer_Apellido", listaAlumnos.get(i).getPrimerApellido(), subraiz, document);
						CrearElemento("Segundo_Apellido", listaAlumnos.get(i).getSegundoApellido(), subraiz, document);
						CrearElemento("Codigo_Postal", String.valueOf(listaAlumnos.get(i).getCodigoPostal()), subraiz, document);
					}
				}

			}

			resul.close();
			sentencia.close();
			conexion.close();

			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Escuelas.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	private static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

}