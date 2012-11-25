/**UTN FRBA - Tp #1 Manejo de Fechas
 * 
 * Diseño de sistemas - Curso k3052
 * Profesores: fdodino, fscarpa 
 */
package com.aterrizar.fecha.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.TransactionalAndObservable;

import uqbar.arena.persistence.annotations.PersistentClass;
import uqbar.arena.persistence.annotations.PersistentField;

@TransactionalAndObservable
@PersistentClass
public class Fecha extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Calendar calendar;
	protected Date value;

	public Fecha(String aDate, Formato aFormat) {
		this();
		value = aFormat.parse(aDate);
		calendar.setTime(value);
	}

	public Fecha(Date aDate) {
		this();
		calendar.setTime(aDate);
	}

	public Fecha() {
		calendar = GregorianCalendar.getInstance();
	}

	public int obtenerDia() {
		return calendar.get(GregorianCalendar.DAY_OF_MONTH);
	}

	public int obtenerMes() {
		return calendar.get(GregorianCalendar.MONTH);
	}

	public int obtenerAnio() {
		return calendar.get(GregorianCalendar.YEAR);
	}

	public boolean esAnteriorA(Fecha otraFecha) {
		return this.getFecha().before(otraFecha.getFecha());
	}

	@PersistentField
	public Date getFecha() {
		return calendar.getTime();
	}
	public void setFecha(Date unaFecha){
		this.calendar.setTime(unaFecha);
	}
	
	public int cantidadDeDiasEntre(Fecha unaFechaAnterior) {
		if (this.esAnteriorA(unaFechaAnterior))
			throw new RuntimeException(	"No se puede sacar el modulo de una fecha futura");
		return this.calendar.get(GregorianCalendar.DAY_OF_YEAR) - unaFechaAnterior.calendar.get(GregorianCalendar.DAY_OF_YEAR); 
	}
}
