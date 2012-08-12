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
	protected String codigo;	

	public Vuelo(String codigoDeVuelo, String unOrigen, String unDestino, Fecha fechaOrigen, Fecha fechaDestino){
		this.codigo = codigoDeVuelo;
		this.origen = unOrigen;
		this.destino = unDestino;
		this.fechaOrigen = fechaOrigen;
		this.fechaDestino = fechaDestino;
	}
	
	public void agregarUnAsiento (Asiento unAsiento){
		asientos.add(unAsiento);
	}
	
	@Override
	public boolean equals(Object obj) {
		Asiento unAsiento = (Asiento)obj;
		return this.codigo.equals(unAsiento.codigo);
	}
}

