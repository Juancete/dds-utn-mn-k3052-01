package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class CriterioDeOrdenamientoTiempoDeVuelo extends CriterioDeOrdenamiento {

	@Override
	public int compare(Asiento unAsiento, Asiento otroAsiento) {
		if (this.ascendente)
		{
			return (int) (unAsiento.getTiempo() - otroAsiento.getTiempo());
		}
		else
		{
			return (int) (otroAsiento.getTiempo() - unAsiento.getTiempo());
		}
	}

}
