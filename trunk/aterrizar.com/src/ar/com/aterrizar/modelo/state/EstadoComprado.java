package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public class EstadoComprado extends Estado {

	@Override
	public void comprar(Asiento unAsiento, Usuario unUsuario) {
		throw new RuntimeException("El asiento ya fue comprado, no se puede Comprar nuevamente.");

	}

	@Override
	public void reservar(Asiento unAsiento, Usuario unUsuario) {
		throw new RuntimeException("El asiento ya fue comprado, no se puede Reservar nuevamente.");

	}

}
