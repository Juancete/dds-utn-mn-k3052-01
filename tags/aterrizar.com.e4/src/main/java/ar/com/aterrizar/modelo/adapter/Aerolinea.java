package ar.com.aterrizar.modelo.adapter;

import java.util.List;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Usuario;

import com.aterrizar.fecha.modelo.Fecha;

public abstract class Aerolinea {
	
	protected double porcentajePorCompania;
		
	public abstract List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) ;
	public abstract void comprarAsiento(Asiento unAsiento,String dni) throws NoSeEncuentraDisponibleElAsientoException;
	
	public Aerolinea(){
		this.setPorcentajePorCompania(1);
	}
	
	public void setPorcentajePorCompania(double unPorcentaje) {
		this.porcentajePorCompania = unPorcentaje;
		
	}
	
	public Double getPorcentajePorCompania(){
		return this.porcentajePorCompania;
	}
	
	public String fechaToAAAAMMDD(Fecha unaFecha){
		if (unaFecha == null){ return null;};
		int fecha = unaFecha.obtenerAnio()*10000+(unaFecha.obtenerMes()+1)*100+unaFecha.obtenerDia();
		return Integer.toString(fecha);
	}
	
	public abstract String getNombre();
	
	public abstract void reservarAsiento(Usuario unUsuario, Asiento unAsiento);	
	
}

