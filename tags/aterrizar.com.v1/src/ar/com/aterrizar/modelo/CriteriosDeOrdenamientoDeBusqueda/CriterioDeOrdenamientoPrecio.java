package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class CriterioDeOrdenamientoPrecio extends CriterioDeOrdenamiento {

	@Override
	public int compare(Asiento unAsiento, Asiento otroAsiento) {
		if (this.ascendente)
		{
			return unAsiento.precio.compareTo(otroAsiento.precio);
		}
		else
		{
			return otroAsiento.precio.compareTo(unAsiento.precio);
		}
	}

}
