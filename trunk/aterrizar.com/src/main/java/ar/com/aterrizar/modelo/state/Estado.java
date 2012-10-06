package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.AterrizarApp;
import ar.com.aterrizar.modelo.Usuario;

public abstract class Estado {
	protected AterrizarApp aplication =AterrizarApp.getInstance();
	
	public abstract void reservar(Asiento unAsiento,Usuario unUsuario);
	public abstract void comprar(Asiento unAsiento,Usuario unUsuario);	
}
