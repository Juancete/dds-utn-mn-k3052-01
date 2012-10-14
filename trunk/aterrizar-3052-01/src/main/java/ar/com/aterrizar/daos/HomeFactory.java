package ar.com.aterrizar.daos;

import java.util.Map;

import org.uqbar.commons.model.Home;

/**
 * Strategy object to decouple Aterrizar initialization from the actual
 * concrete home instantiations.
 * This allow us to run tests with in-memory homes, while running the application
 * with a real persistence homes.
 * 
 * This is temporary until we add dependency injection.
 * 
 * @author jfernandes
 * @author iguardines
 */
public interface HomeFactory {
	
	/**
	 * Creates an add the {@link Home} instances associated to any domain entities.
	 */
	public void addHomes(Map<Class<?>, Home<?>> homes);

}
