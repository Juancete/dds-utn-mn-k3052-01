package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class FiltroUbicacion extends Filtro {

	private char ubicacion;

	public FiltroUbicacion(char unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return unAsiento.ubicacion == this.ubicacion;
	}

	public void setUbicacion(char unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
}
