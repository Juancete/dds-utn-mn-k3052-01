package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public abstract class Estado {
	protected org.uqbar.commons.model.Home<Asiento> aplication =AterrizarCom.getInstance().getHome(Asiento.class);
	
	public abstract void reservar(Asiento unAsiento,Usuario unUsuario);
	public abstract void comprar(Asiento unAsiento,Usuario unUsuario);	
}
