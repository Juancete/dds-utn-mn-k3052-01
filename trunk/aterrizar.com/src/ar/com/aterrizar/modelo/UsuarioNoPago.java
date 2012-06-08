package ar.com.aterrizar.modelo;

import ar.com.aterrizar.negocio.Calculable;

public class UsuarioNoPago implements Calculable {

	@Override
	public double calculoDePrecio() {
		throw new RuntimeException("Must implements");
	}

}
