package ar.com.aterrizar.daos;

import java.math.BigDecimal;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome;

import com.aterrizar.fecha.modelo.Fecha;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Vuelo;
import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.state.Estado;
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

	@Override
	protected Predicate getCriterio(EstadoComprado example) {
		// TODO Auto-generated method stub
		return null;
	}

}
