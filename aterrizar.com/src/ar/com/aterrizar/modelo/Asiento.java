package ar.com.aterrizar.modelo;

import java.math.BigDecimal;

public class Asiento {

	protected String codigo;
	protected BigDecimal precio;
	protected char ubicacion;
	protected boolean disponible;

	public Boolean soySuperOferta(){
		throw new RuntimeException();
	}
}