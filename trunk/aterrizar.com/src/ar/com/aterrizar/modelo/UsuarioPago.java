package ar.com.aterrizar.modelo;

import ar.com.aterrizar.negocio.Calculable;

public class UsuarioPago implements Calculable {

	@Override
	public double calculoDePrecio() {
		throw new RuntimeException("Must implements");
	}

}
