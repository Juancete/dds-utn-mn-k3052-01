package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class FiltroReservados extends Filtro {

	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return unAsiento.estadoReservado();
	}

}
