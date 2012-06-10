package ar.com.aterrizar.modelo;

import java.util.List;

public abstract class Aerolinea {
	
	public abstract List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) ;
	public abstract void comprarAsiento(Asiento unAsiento);
}
