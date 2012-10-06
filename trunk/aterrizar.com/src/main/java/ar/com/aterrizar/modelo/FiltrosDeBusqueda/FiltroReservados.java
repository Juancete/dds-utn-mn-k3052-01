package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.entidades.Asiento;

public class FiltroReservados extends Filtro {

	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return unAsiento.estadoReservado();
	}

}
