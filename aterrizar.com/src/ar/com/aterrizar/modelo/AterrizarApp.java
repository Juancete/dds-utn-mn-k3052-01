package ar.com.aterrizar.modelo;

import java.util.Collection;
import java.util.List;

import ar.com.aterrizar.modelo.adapter.Aerolinea;

public class AterrizarApp {

	protected static AterrizarApp instance;
	protected Collection<Usuario> usuarios;
	protected List<Aerolinea> aerolineas;
	
	private AterrizarApp() {
	}

	public static AterrizarApp getInstance(){
		if(instance == null)
			instance = new AterrizarApp();
		return instance;
	}
	
	public void registarUsuario(String nombre,String apellido, String dni){
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setAPellido(apellido);
		nuevoUsuario.setDNI(dni);
		
	}
}
