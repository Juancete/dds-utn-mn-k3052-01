package ar.com.aterrizar.modelo.adapter;

public class NoSeEncuentraDisponibleElAsientoException extends RuntimeException {

	
	public NoSeEncuentraDisponibleElAsientoException(String descripcion){
		super(descripcion);
	}
	
	public NoSeEncuentraDisponibleElAsientoException(){
		super();
	}
	
}
