package ar.com.aterrizar.arena;

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

public class SearchAsientoByExample extends SearchByExample<Asiento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario miUsuario;
	private InicioWindow w;

	public SearchAsientoByExample(Home<Asiento> home, Usuario unUsuario,
			InicioWindow wOwner) {
		super(home);
		this.miUsuario = unUsuario;
		w = wOwner;
	}

	public void comprar() {
		try {
			this.getSelected().comprar(this.miUsuario);
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		}
		AterrizarCom
				.getInstance()
				.getHome(EstadoComprado.class)
				.create((EstadoComprado) ( this.getSelected())
						.getEstado());
		(new InformationWindow(w, new Usuario(),
				"Su compra se ha realizado exitosamente.")).open();
	}

	public void reservar() {
		try {
			this.getSelected().reservar(this.miUsuario);
			AterrizarCom
					.getInstance()
					.getHome(EstadoReservado.class)
					.create((EstadoReservado) ( this.getSelected())
							.getEstado());
			(new InformationWindow(w, new Usuario(),
					"Su reserva se ha realizado exitosamente.")).open();
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		} catch (AsientoYaReservadoException e) {
			new SobreReservaDialog(w, this.getSelected(), miUsuario)
					.open();
		}
	}

}
