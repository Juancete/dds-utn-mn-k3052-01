package ar.com.aterrizar.daos;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome;
import org.uqbar.commons.model.Home;

import ar.com.aterrizar.modelo.state.EstadoReservado;

public class AsientoReservadoDaoCollectionImpl extends CollectionBasedHome<EstadoReservado>  {

	@Override
	public Class<EstadoReservado> getEntityType() {
		return EstadoReservado.class;
	}

	@Override
	public EstadoReservado createExample() {
		return new EstadoReservado();
	}

	@Override
	protected Predicate getCriterio(EstadoReservado example) {
		// TODO Auto-generated method stub
		return null;
	}

}
