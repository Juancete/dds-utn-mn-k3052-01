package ar.com.aterrizar.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.uqbar.commons.model.Application;
import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.Home;


/**
 * Modelo de aplicacion que representa a la aplicacion misma del videocAterrizarComlub.
 * 
 * @author npasserini
 */
public class AterrizarCom implements Application {
	private static AterrizarCom instance;
	private Map<Class<?>, Home<?>> homes;

	@SuppressWarnings("unchecked")
	public synchronized <T extends Entity> Home<T> getHome(Class<? extends T> type) {
		return (Home<T>) this.homes.get(type);
	}

	public static AterrizarCom initialize(HomeFactory factory) {
		instance = new AterrizarCom();
		//HARDCODED: coupled with in-memory homes. 
		Map<Class<?>, Home<?>> homes = new HashMap<Class<?>, Home<?>>();
		factory.addHomes(homes);
		instance.homes = homes;
		return instance;
	}
	
	public static synchronized AterrizarCom initialize() {
//		return initialize(new DefaultPersistentHomeFactory()); //default is to persist with db4o
		return initialize(new InMemoryHomeFactory()); //default is to persist with db4o
	}
	
	public static synchronized AterrizarCom getInstance() {
		if (instance == null) {
			instance = initialize();
		}
		return instance;
	}

	/*public List<String> getGeneros() {
		SortedSet<String> set = new TreeSet<String>();
		for (Pelicula unaPelicula : this.getHome(Pelicula.class).allInstances()) {
			set.add(unaPelicula.getGenero());
		}
		List<String> listaGeneros = new ArrayList<String>();
		listaGeneros.addAll(set);
		return listaGeneros;
	}
	*/
}
