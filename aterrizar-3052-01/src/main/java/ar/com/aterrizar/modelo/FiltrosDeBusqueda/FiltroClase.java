package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.entidades.Asiento;

public class FiltroClase extends Filtro {

	private char clase;

	public FiltroClase (char unaclase){
		this.clase = unaclase;
	}
	
	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return (unAsiento.getTipo() == this.clase);
	}

	public void setClase(char unaclase){
		this.clase = unaclase;
	}

}
