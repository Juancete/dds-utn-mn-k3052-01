package ar.com.aterrizar.daos;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome;

import uqbar.arena.persistence.PersistentHome;

import ar.com.aterrizar.entidades.Asiento;

public class AsientoHistorialDaoCollectionImpl extends PersistentHome<Asiento>  {

	@Override
	public Class<Asiento> getEntityType() {
		return Asiento.class;
	}

	@Override
	public Asiento createExample() {
		return new Asiento();
	}

	@SuppressWarnings("rawtypes")
	protected Predicate getCriterio(Asiento example) {
		return null;
	}

}
