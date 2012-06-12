package ar.com.aterrizar.modelo.adapter;

import java.util.List;

import ar.com.aterrizar.modelo.*;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public abstract class Aerolinea {
	
	protected double porcentajePorCompania;
		
	public abstract List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) ;
	public abstract void comprarAsiento(Asiento unAsiento) throws NoSeEncuentraDisponibleElAsientoException;
	
	public void setPorcentajePorCompania(double unPorcentaje) {
		this.porcentajePorCompania = unPorcentaje;
		
	}
	
	public Double getPorcentajePorCompania(){
		return this.porcentajePorCompania;
	}
	
}

