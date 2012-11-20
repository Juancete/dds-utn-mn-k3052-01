package ar.com.aterrizar.modelo.adapter;

import java.util.List;


import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.TransactionalAndObservable;

import uqbar.arena.persistence.annotations.PersistentClass;
import uqbar.arena.persistence.annotations.PersistentField;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Usuario;

import com.aterrizar.fecha.modelo.Fecha;

@TransactionalAndObservable
@PersistentClass
public abstract class Aerolinea extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double porcentajePorCompania;
		
	public abstract List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) ;
	public abstract void comprarAsiento(Asiento unAsiento,String dni) throws NoSeEncuentraDisponibleElAsientoException;
	
	public Aerolinea(){
		this.setPorcentajePorCompania(1);
	}
	
	public void setPorcentajePorCompania(double unPorcentaje) {
		this.porcentajePorCompania = unPorcentaje;
		
	}
	
	public double getPorcentajePorCompania(){
		return this.porcentajePorCompania;
	}
	
	public String fechaToAAAAMMDD(Fecha unaFecha){
		if (unaFecha == null){ return null;};
		int fecha = unaFecha.obtenerAnio()*10000+(unaFecha.obtenerMes()+1)*100+unaFecha.obtenerDia();
		return Integer.toString(fecha);
	}
	@PersistentField
	public abstract String getNombre();

	public void setNombre(String unNombre){
		
	}
	public abstract void reservarAsiento(Usuario unUsuario, Asiento unAsiento);	
	
}

