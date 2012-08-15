package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import ar.com.aterrizar.modelo.Asiento;

public class FiltroOr extends Filtro {
	
	Filtro unFiltro;
	Filtro otroFiltro;

	public FiltroOr(Filtro unFiltro, Filtro otroFiltro){
		this.unFiltro = unFiltro;
		this.otroFiltro = otroFiltro;
	}
	
	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		return (this.unFiltro.evaluarLaCondicion(unAsiento) || this.otroFiltro.evaluarLaCondicion(unAsiento));
	}

	public void setUnfiltro(Filtro unFiltro){
		this.unFiltro = unFiltro;
	}
	
	public void setotrofiltro(Filtro unFiltro){
		this.otroFiltro = unFiltro;
	}	
}
