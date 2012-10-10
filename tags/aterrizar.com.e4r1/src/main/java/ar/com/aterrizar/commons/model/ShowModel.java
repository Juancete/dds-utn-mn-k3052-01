package ar.com.aterrizar.commons.model;

import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.Home;
import org.uqbar.commons.utils.TransactionalAndObservable;

@TransactionalAndObservable
public class ShowModel<T extends Entity>{

	public static final String RESULTS = "results";
	public static final String SELECTED = "selected";
	
	private Home<T> home;
	private List<T> results;
	private T selected;
	
	public ShowModel(Home<T> home) {
		this.home = home;
		this.search();
	}
	
	protected void search() {
		results = this.home.allInstances();
	}
	
	public List<T> getResults(){
		return this.results;
	}
	
	public T getSelected(){
		return this.selected;
	}

}