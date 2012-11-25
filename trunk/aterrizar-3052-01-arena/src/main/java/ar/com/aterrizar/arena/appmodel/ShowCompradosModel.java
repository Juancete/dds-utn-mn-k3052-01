package ar.com.aterrizar.arena.appmodel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.Home;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.state.EstadoComprado;

public class ShowCompradosModel extends ShowModel<Asiento> {

	private List<Asiento> asientosFiltrados;
	
	public ShowCompradosModel(Home<Asiento> home) {
		super(home);
	}

	@Override
	public List<Asiento> getResults() {
		this.search();
		List<Asiento> allAsiento = super.getResults();
		asientosFiltrados = new ArrayList<Asiento>();
		for (Asiento unAsiento : allAsiento){
			if(unAsiento.getEstado().getClass().equals(EstadoComprado.class)){
				asientosFiltrados.add(unAsiento);
			}
		}
		return asientosFiltrados;
		
	}
}
