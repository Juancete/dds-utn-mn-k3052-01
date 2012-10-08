package ar.com.aterrizar.arena;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.Home;
import org.uqbar.commons.model.SearchByExample;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public class SearchAsientoByExample<T extends Entity> extends SearchByExample<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario miUsuario;

	public SearchAsientoByExample(Home<T> home, Usuario unUsuario) {
		super(home);
		this.miUsuario = unUsuario;
	}
	
	public void comprar(){
		((Asiento) this.getSelected()).getEstado().comprar(((Asiento) this.getSelected()), this.miUsuario);
	}
	public void reservar(){
		((Asiento) this.getSelected()).getEstado().reservar(((Asiento) this.getSelected()), this.miUsuario);
	}

}
