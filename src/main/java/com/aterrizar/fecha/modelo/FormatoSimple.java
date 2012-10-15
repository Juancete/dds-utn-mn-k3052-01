/**UTN FRBA - Tp #1 Manejo de Fechas
 * 
 * Diseño de sistemas - Curso k3052
 * Profesores: fdodino, fscarpa 
 */
package com.aterrizar.fecha.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aterrizar.fecha.excepcion.FechaException;

/**
 * FormatoSimple representa la implementacion basica de un formato, el formato simple para su funcionamiento
 * necesita el string que representa el formato que utilizara para interpretar las representaciones Strings de fechas.
 * @author iguardines
 *
 */
public class FormatoSimple extends Formato {

	String formatStringValue;
	DateFormat formatter;
	
	/**
	 * La construccion de un formatoSimple va siempre acompañada por la representacion String del formato, no existe 
	 * un formato por default
	 * @throws FechaException en la construccion, cuando se le envia null como formato
	 * @throws FechaException en el parsing, la fecha no es validas.
	 * @param theFormat
	 * @version 1.0
	 * 
	 */
	public FormatoSimple(String theFormat){
		if(theFormat == null)throw new FechaException("El formato no puede ser nulo");
		this.formatStringValue = theFormat;
		this.formatter = new SimpleDateFormat(this.formatStringValue);
	}
	@Override
	public Date parse(String aDate) {		
		try {
			return this.formatter.parse(aDate);
		} catch (ParseException e) {
			throw new FechaException("La fecha no es valida para el formato dado: "+ this.formatStringValue); 
		}
	}

}
