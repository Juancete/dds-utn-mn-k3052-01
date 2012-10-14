/**UTN FRBA - Tp #1 Manejo de Fechas
 * 
 * Diseño de sistemas - Curso k3052
 * Profesores: fdodino, fscarpa 
 */
package com.aterrizar.fecha.excepcion;
/**
 * <b>FechaException modela una Exception susceptible a ocurrir durante el manejo de Fechas.</b>
 *  Excepcion no checkeada
 * 
 * @version 1.0 
 * @author iguardines
 *
 */
public class FechaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3802001112314689132L;

	public FechaException() {
		super();
	}

	public FechaException(String mensaje, Throwable causa) {
		super(mensaje, causa);		
	}

	public FechaException(String mensaje) {
		super(mensaje);
	}

	public FechaException(Throwable causa) {
		super(causa);		
	}
}

