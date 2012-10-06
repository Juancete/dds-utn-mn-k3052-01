package ar.com.aterrizar.daos;

import java.util.Map;

import org.uqbar.commons.model.Home;

import ar.com.aterrizar.entidades.Asiento;

/**
 * Implementacion dummy que crea las homes en-memoria
 * 
 * @author iguardines
 */
public class InMemoryHomeFactory implements HomeFactory {

	@Override
	public void addHomes(Map<Class<?>, Home<?>> homes) {
		homes.put(Asiento.class, new AsientoDaoCollectionImpl());

	}

}
