package ar.com.aterrizar.daos;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import ar.com.aterrizar.modelo.Busqueda;
import uqbar.arena.persistence.PersistentHome;

@Observable
public class BusquedaDaoCollection extends PersistentHome<Busqueda> {

	public static final String RESULTS = "results";
	
	@Override
	public Class<Busqueda> getEntityType() {
		return Busqueda.class;
	}

	@Override
	public Busqueda createExample() {
		return new Busqueda();
	}

	public List<Busqueda> getResults(){
		return this.allInstances();
	}
		
}
