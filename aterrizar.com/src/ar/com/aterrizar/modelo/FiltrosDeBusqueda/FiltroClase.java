package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class FiltroClase extends Filtro {

	private char clase;

	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return (unAsiento.tipo == this.clase);
	}

	public void setClase(char unaclase){
		this.clase = unaclase;
	}

}
