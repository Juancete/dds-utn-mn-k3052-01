package ar.com.aterrizar.arena;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.Home;
import org.uqbar.commons.model.SearchByExample;
import org.uqbar.commons.model.UserException;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public class SearchAsientoByExample<T extends Entity> extends SearchByExample<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario miUsuario;
	private InicioWindow w;
	
	public SearchAsientoByExample(Home<T> home, Usuario unUsuario,InicioWindow wOwner) {
		super(home);
		this.miUsuario = unUsuario;
		w = wOwner;
	}
	
	public void comprar(){
		try {
			((Asiento) this.getSelected()).getEstado().comprar(((Asiento) this.getSelected()), this.miUsuario);
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		}
		(new informationWindow(w, new Usuario(),"Su compra se ha realizado exitosamente.")).open();
	}
	
	public void reservar(){
		try {
			((Asiento) this.getSelected()).getEstado().reservar(((Asiento) this.getSelected()), this.miUsuario);
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		}
		(new informationWindow(w, new Usuario(),"Su reserva se ha realizado exitosamente.")).open();
	}

}
