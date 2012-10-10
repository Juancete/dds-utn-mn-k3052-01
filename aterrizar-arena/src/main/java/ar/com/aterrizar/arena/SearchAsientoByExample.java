package ar.com.aterrizar.arena;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.Home;
import org.uqbar.commons.model.SearchByExample;
import org.uqbar.commons.model.UserException;

import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;
import ar.com.aterrizar.modelo.state.AsientoYaReservadoException;
import ar.com.aterrizar.modelo.state.EstadoComprado;
import ar.com.aterrizar.modelo.state.EstadoReservado;

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
			//throw new UserException("Hay goma con el asiento");
			throw new UserException(e.getMessage());
		}
		//le calzo el objeto a el asiento que corresponde
		AterrizarCom.getInstance().getHome(EstadoComprado.class).create((EstadoComprado) ((Asiento)this.getSelected()).getEstado());
		(new informationWindow(w, new Usuario(),"Su compra se ha realizado exitosamente.")).open();
	}
	
	public void reservar(){
		try {
			((Asiento) this.getSelected()).getEstado().reservar(((Asiento) this.getSelected()), this.miUsuario);
			AterrizarCom.getInstance().getHome(EstadoReservado.class).create((EstadoReservado) ((Asiento)this.getSelected()).getEstado());
			(new informationWindow(w, new Usuario(),"Su reserva se ha realizado exitosamente.")).open();			
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		}
		catch (AsientoYaReservadoException e)
		{
			new sobreReservaDialog( w, (Asiento) this.getSelected(), miUsuario).open();
		}
//		AterrizarCom.getInstance().getHome(EstadoReservado.class).create((EstadoReservado) ((Asiento)this.getSelected()).getEstado());
//		(new informationWindow(w, new Usuario(),"Su reserva se ha realizado exitosamente.")).open();
	}

}
