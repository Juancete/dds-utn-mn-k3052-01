package ar.com.aterrizar.daos;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome;

import ar.com.aterrizar.entidades.Asiento;

public class AsientoHistorialDaoCollectionImpl extends CollectionBasedHome<Asiento>  {

	@Override
	public Class<Asiento> getEntityType() {
		return Asiento.class;
	}

	@Override
	public Asiento createExample() {
		return new Asiento();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Predicate getCriterio(Asiento example) {
		// TODO Auto-generated method stub
		return null;
	}

}
