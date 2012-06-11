package ar.com.aterrizar.modelo;

import java.util.List;

import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public abstract class Aerolinea {
	
	public abstract List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) ;
	public abstract void comprarAsiento(Asiento unAsiento) throws NoSeEncuentraDisponibleElAsientoException;
}
