package ar.com.aterrizar.daos;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome;

import ar.com.aterrizar.modelo.state.EstadoComprado;

public class AsientoCompradoDaoCollectionImpl extends
		CollectionBasedHome<EstadoComprado> {

//	public AsientoCompradoDaoCollectionImpl(){
//		EstadoComprado unEstado = new EstadoComprado();
//		Asiento unAsiento = new Asiento("codigo", new BigDecimal(10),'V', 'P', unEstado, new AerolineaLanchitaAdapter());
//		unAsiento.setVuelo(new Vuelo("V815", "BAIRES", "Congo", new Fecha(), new Fecha()));
//		this.create(unEstado);
//	}
	@Override
	public Class<EstadoComprado> getEntityType() {
		return EstadoComprado.class;
	}

	@Override
	public EstadoComprado createExample() {
		return new EstadoComprado();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Predicate getCriterio(EstadoComprado example) {
		// TODO Auto-generated method stub
		return null;
	}

}
