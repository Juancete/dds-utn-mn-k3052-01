package ar.com.aterrizar.daos;

import java.util.Map;

import org.uqbar.commons.model.Home;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.state.EstadoComprado;
import ar.com.aterrizar.modelo.state.EstadoReservado;

/**
 * Implementacion dummy que crea las homes en-memoria
 * 
 * @author iguardines
 */
public class InMemoryHomeFactory implements HomeFactory {

	@Override
	public void addHomes(Map<Class<?>, Home<?>> homes) {
		homes.put(Asiento.class, new AsientoDaoCollectionImpl());
		homes.put(EstadoComprado.class, new AsientoCompradoDaoCollectionImpl());
		homes.put(EstadoReservado.class, new AsientoReservadoDaoCollectionImpl());

	}

}
