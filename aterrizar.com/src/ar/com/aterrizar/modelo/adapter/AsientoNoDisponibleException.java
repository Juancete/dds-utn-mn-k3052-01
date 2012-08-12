package ar.com.aterrizar.modelo.adapter;

public class AsientoNoDisponibleException extends RuntimeException {

	
	private static final long serialVersionUID = -5201868829462286772L;

	public AsientoNoDisponibleException(){};
	
	public AsientoNoDisponibleException(String mensaje) {
		super(mensaje);
	}

}
