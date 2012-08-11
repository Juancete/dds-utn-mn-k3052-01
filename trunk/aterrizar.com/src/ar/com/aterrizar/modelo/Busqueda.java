package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.modelo.FiltrosDeBusqueda.Filtro;

public class Busqueda {

	protected String origen;
	protected String destino;
	protected String fecha; //formato: "AAAAMMdd"
	protected List<Filtro> filtros = new ArrayList<Filtro>();

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String unaFecha) {
		this.fecha = unaFecha;
	}

	public Busqueda(String origen, String destino, String unaFecha) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.fecha = unaFecha;

	}
	
	public List<Asiento> filtrarAsientos(List<Asiento> listaSinFiltrar){
		for (Filtro unFiltro: this.filtros){
			listaSinFiltrar = unFiltro.evaluarLista(listaSinFiltrar);
		}
		return listaSinFiltrar;
	}
}
