package ar.com.aterrizar.daos;

import org.uqbar.commons.utils.Observable;

import ar.com.aterrizar.modelo.Busqueda;
import uqbar.arena.persistence.PersistentHome;

@Observable
public class BusquedaDaoCollection extends PersistentHome<Busqueda> {

	@Override
	public Class<Busqueda> getEntityType() {
		return Busqueda.class;
	}

	@Override
	public Busqueda createExample() {
		return new Busqueda();
	}

}
