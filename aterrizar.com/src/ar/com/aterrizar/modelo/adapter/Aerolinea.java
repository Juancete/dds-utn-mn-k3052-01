package ar.com.aterrizar.modelo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.aterrizar.fecha.modelo.Fecha;

import ar.com.aterrizar.modelo.*;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public abstract class Aerolinea {
	
	protected double porcentajePorCompania;
		
	public abstract List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) ;
	public abstract void comprarAsiento(Asiento unAsiento,String dni) throws NoSeEncuentraDisponibleElAsientoException;
	
	public void setPorcentajePorCompania(double unPorcentaje) {
		this.porcentajePorCompania = unPorcentaje;
		
	}
	
	public Double getPorcentajePorCompania(){
		return this.porcentajePorCompania;
	}
	
	public String fechaToAAAAMMDD(Fecha unaFecha){
		int fecha = unaFecha.obtenerAnio()*10000+(unaFecha.obtenerMes()+1)*100+unaFecha.obtenerDia();
		return Integer.toString(fecha);
	}
	
	public abstract void reservarAsiento(Usuario unUsuario, Asiento unAsiento);	
	
}

