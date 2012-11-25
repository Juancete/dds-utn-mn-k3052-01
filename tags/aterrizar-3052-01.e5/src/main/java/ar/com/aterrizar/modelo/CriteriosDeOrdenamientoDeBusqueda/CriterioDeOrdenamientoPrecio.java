package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import ar.com.aterrizar.entidades.Asiento;

public class CriterioDeOrdenamientoPrecio extends CriterioDeOrdenamiento {

	@Override
	public int compare(Asiento unAsiento, Asiento otroAsiento) {
		if (this.ascendente)
		{
			return unAsiento.getPrecio().compareTo(otroAsiento.getPrecio());
		}
		else
		{
			return otroAsiento.getPrecio().compareTo(unAsiento.getPrecio());
		}
	}

}
