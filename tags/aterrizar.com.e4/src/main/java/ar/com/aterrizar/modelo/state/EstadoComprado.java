package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public class EstadoComprado extends Estado {

	@Override
	public void comprar(Asiento unAsiento, Usuario unUsuario) {
		throw new NoSeEncuentraDisponibleElAsientoException("El asiento ya fue comprado, no se puede Comprar nuevamente.");

	}

	@Override
	public void reservar(Asiento unAsiento, Usuario unUsuario) {
		throw new NoSeEncuentraDisponibleElAsientoException("El asiento ya fue comprado, no se puede Reservar nuevamente.");

	}

}
