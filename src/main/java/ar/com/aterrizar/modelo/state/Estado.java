package ar.com.aterrizar.modelo.state;

import org.uqbar.commons.model.Entity;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public abstract class Estado extends Entity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Asiento miAsiento;
	
	//protected org.uqbar.commons.model.Home<Asiento> aplication =AterrizarCom.getInstance().getHome(Asiento.class);
	
	public abstract void reservar(Asiento unAsiento,Usuario unUsuario);
	public abstract void comprar(Asiento unAsiento,Usuario unUsuario);
	
	public Asiento getMiAsiento() {
		return miAsiento;
	}
	public void setMiAsiento(Asiento miAsiento) {
		this.miAsiento = miAsiento;
	}	
}
