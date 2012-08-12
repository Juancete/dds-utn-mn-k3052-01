package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.List;

import com.aterrizar.fecha.modelo.Fecha;

public class Vuelo {
	protected String origen;
	protected String destino;
	protected Fecha fechaOrigen;
	protected Fecha fechaDestino;
	protected List<Asiento> asientos = new ArrayList<Asiento>();
	private String codigo;
	private int popularidad;

	public Vuelo(String codigoDeVuelo, String unOrigen, String unDestino, Fecha fechaOrigen, Fecha fechaDestino){
		this.setCodigo(codigoDeVuelo);
		this.origen = unOrigen;
		this.destino = unDestino;
		this.fechaOrigen = fechaOrigen;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}

