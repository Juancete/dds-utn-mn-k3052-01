package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public class EstadoDisponible extends Estado {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void comprar(Asiento unAsiento, Usuario unUsuario) {
		unAsiento.getAerolinea().comprarAsiento(unAsiento, unUsuario.getDni());
		unAsiento.setEstado(new EstadoComprado());
		unAsiento.getEstado().setMiAsiento(unAsiento);
	}

	@Override
	public void reservar(Asiento unAsiento, Usuario unUsuario) {
		unAsiento.getAerolinea().reservarAsiento(unUsuario, unAsiento);		
		unAsiento.setEstado(new EstadoReservado());
		unAsiento.getEstado().setMiAsiento(unAsiento);
		unAsiento.setReservante(unUsuario);
		//((AsientoDaoCollectionImpl) this.aplication).agregarReservaOSobreReserva(unAsiento); TODO agregarReservaOSobreReserva en la home
	}

}
