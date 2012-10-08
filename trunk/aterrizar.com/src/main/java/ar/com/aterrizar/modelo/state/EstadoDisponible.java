package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.daos.AsientoDaoCollectionImpl;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public class EstadoDisponible extends Estado {

	@Override
	public void comprar(Asiento unAsiento, Usuario unUsuario) {
		unAsiento.aerolinea.comprarAsiento(unAsiento, unUsuario.getDni());
		unAsiento.setEstado(new EstadoComprado());
	}

	@Override
	public void reservar(Asiento unAsiento, Usuario unUsuario) {
		unAsiento.aerolinea.reservarAsiento(unUsuario, unAsiento);		
		unAsiento.setEstado(new EstadoReservado());
		unAsiento.setReservante(unUsuario);
		//((AsientoDaoCollectionImpl) this.aplication).agregarReservaOSobreReserva(unAsiento); TODO agregarReservaOSobreReserva en la home
	}

}
