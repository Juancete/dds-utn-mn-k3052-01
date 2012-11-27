package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.TransactionalAndObservable;

import uqbar.arena.persistence.annotations.PersistentClass;
import uqbar.arena.persistence.annotations.PersistentField;
import uqbar.arena.persistence.annotations.Relation;

import ar.com.aterrizar.entidades.Asiento;

import com.aterrizar.fecha.modelo.Fecha;

@TransactionalAndObservable
@PersistentClass
public class Vuelo extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String origen;
	private String destino;
	private Fecha fechaOrigen;
	public Fecha fechaDestino;
	protected List<Asiento> asientos = new ArrayList<Asiento>();
	private String codigo;
	private int popularidad;

	public Vuelo(){
		super();
	}
	
	public Vuelo(String codigoDeVuelo, String unOrigen, String unDestino, Fecha fechaOrigen, Fecha fechaDestino){
		this.setCodigo(codigoDeVuelo);
		this.setOrigen(unOrigen);
		this.setDestino(unDestino);
		this.setFechaOrigen(fechaOrigen);
		this.fechaDestino = fechaDestino;
		this.popularidad = 0;
	}
	
	public void agregarUnAsiento (Asiento unAsiento){
		asientos.add(unAsiento);
	}
	
	@Override
	public boolean equals(Object obj) {
		Vuelo unVuelo = (Vuelo)obj;
		return this.getCodigo().equals(unVuelo.getCodigo());
	}
	
	public void incrementarPopularidad(){
		this.setPopularidad(new Integer(this.getPopularidad() + 1));
	}

	public int getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(int popularidad) {
		this.popularidad = popularidad;
	}

	@PersistentField
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@PersistentField
	public String getDestino() {
		return this.destino;
	}
	@Relation
	public Fecha getFechaOrigen() {
		return fechaOrigen;
	}
	
	public Fecha getFechaDestino() {
		return fechaDestino;
	}

	@PersistentField
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setFechaOrigen(Fecha fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

}

