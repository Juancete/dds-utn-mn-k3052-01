package ar.com.aterrizar.modelo;

import java.util.List;

import ar.com.aterrizar.negocio.Calculable;

public class Usuario {

	protected String nombre;
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public List<Busqueda> getBusquedas() {
		return busquedas;
	}

	protected String apellido;
	protected String dni;
	protected Calculable tipoDeUsuario;

	protected List<Busqueda> busquedas;

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public void setAPellido(String apellido) {
		this.apellido = apellido;

	}

	public void setDNI(String dni) {
		this.dni = dni;
	}
}
