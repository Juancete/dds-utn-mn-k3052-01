package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class FiltrarUbicacion extends Filtro {

	private char ubicacion;

	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return unAsiento.ubicacion == this.ubicacion;
	}

	public void setUbicacion(char unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
}
