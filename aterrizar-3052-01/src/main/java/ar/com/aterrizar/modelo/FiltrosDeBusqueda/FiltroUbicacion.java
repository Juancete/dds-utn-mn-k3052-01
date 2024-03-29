package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.entidades.Asiento;

public class FiltroUbicacion extends Filtro {

	private char ubicacion;

	public FiltroUbicacion(char unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return unAsiento.getUbicacion() == this.ubicacion;
	}

	public void setUbicacion(char unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
}
