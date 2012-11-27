package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.entidades.Asiento;

public abstract class Filtro {

	public List<Asiento> evaluarLista(List<Asiento> listaSinFiltrar){
		List<Asiento> listaFiltrada = new ArrayList<Asiento>();
		for (Asiento unAsiento: listaSinFiltrar){
			if (!evaluarLaCondicion(unAsiento)){
				listaFiltrada.add(unAsiento);
			}
		}
		return listaFiltrada;	
	}
	
	protected abstract boolean evaluarLaCondicion(Asiento unAsiento);

}