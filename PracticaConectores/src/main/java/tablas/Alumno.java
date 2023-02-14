package tablas;

import java.io.Serializable;
import java.util.Objects;

public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int idEscuela;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private int codigoPostal;

	public Alumno() {
		super();
	}

	public Alumno(int id, String nombre, String primerApellido, String segundoApellido, int codigoPostal) {
		this.id = id;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.codigoPostal = codigoPostal;
	}

	public Alumno(int id, int idEscuela, String nombre, String primerApellido, String segundoApellido,
			int codigoPostal) {
		this.id = id;
		this.idEscuela = idEscuela;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.codigoPostal = codigoPostal;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEscuela() {
		return idEscuela;
	}

	public void setIdEscuela(int idEscuela) {
		this.idEscuela = idEscuela;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String toString() {
		return "Id: " + this.id + "\nNombre: " + this.nombre + "\nApellidos: " + this.primerApellido + " "
				+ this.segundoApellido + "\nCodigo postal: " + this.codigoPostal;
	}

	public String datosActualizar() {
		return this.id + "-" + this.idEscuela + "-" + this.nombre + "-" + this.primerApellido + "-" + this.segundoApellido + "-"
				+ this.codigoPostal;
	}

	public int hashCode() {
		return Objects.hash(
				new Object[] { this.primerApellido, this.segundoApellido, this.codigoPostal, this.id, this.nombre });
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(this.primerApellido, other.primerApellido)
				|| Objects.equals(this.segundoApellido, other.segundoApellido)
				|| this.codigoPostal == other.codigoPostal || this.id == other.id
				|| Objects.equals(this.nombre, other.nombre);
	}
}
