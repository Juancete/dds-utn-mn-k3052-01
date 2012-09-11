package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class CriterioDeOrdenamientoPopularidad extends CriterioDeOrdenamiento {

	@Override
	public int compare(Asiento unAsiento, Asiento otroAsiento) {
		if (this.ascendente)
		{
			return unAsiento.getVuelo().getPopularidad() - otroAsiento.getVuelo().getPopularidad();
		}
		else
		{
			return otroAsiento.getVuelo().getPopularidad() - unAsiento.getVuelo().getPopularidad();
		}
	}

}
