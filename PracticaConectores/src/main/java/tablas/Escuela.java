package tablas;

import java.util.ArrayList;

public class Escuela {
	private ArrayList<Alumno> listaAlumno;
	private int idEscuela;
	private String nombre;
	private String provincia;

	public Escuela() {
		super();
	}

	public Escuela(String nombre, String provincia) {
		super();
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public Escuela(int idEscuela, String nombre, String provincia) {
		super();
		this.idEscuela = idEscuela;
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public ArrayList<Alumno> getListaAlumno() {
		return listaAlumno;
	}

	public void setListaAlumno(ArrayList<Alumno> listaAlumno) {
		this.listaAlumno = listaAlumno;
	}

	public int getIdEscuela() {
		return idEscuela;
	}

	public void setIdEscuela(int idEscuela) {
		this.idEscuela = idEscuela;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Escuela [idEscuela=" + idEscuela + ", nombre=" + nombre + ", provincia=" + provincia + "]";
	}
	
	
	
}
