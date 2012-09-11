package ar.com.aterrizar.modelo.adapter;

public class NoSeEncuentraDisponibleElAsientoException extends RuntimeException {
	
	private static final long serialVersionUID = -4068897912877107389L;

	public NoSeEncuentraDisponibleElAsientoException(String descripcion){
		super(descripcion);
	}
	
	public NoSeEncuentraDisponibleElAsientoException(){
		super();
	}
	
}
