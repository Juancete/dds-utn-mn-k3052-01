package ar.com.aterrizar.modelo;

import java.util.List;

public abstract class Aerolinea {
	
	public abstract List<Asiento> buscarAsientos(Busqueda unaBusqueda);
	public abstract void comprarAsiento(String codigoAsiento);
}
