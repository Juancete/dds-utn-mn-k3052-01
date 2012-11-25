package ar.com.aterrizar.arena.appmodel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.Home;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.state.Estado;

public class ShowAsientoPorEstadoModel extends ShowModel<Asiento> {

	Class<?> clase;
	private List<Asiento> asientosFiltrados;
	
	public ShowAsientoPorEstadoModel(Home<Asiento> home, Class<?> clase) {
		super(home);
		this.clase = clase;
	}

	@Override
	public List<Asiento> getResults() {

		List<Asiento> allAsiento = super.getResults();
		if (allAsiento != null )
		{
			asientosFiltrados = new ArrayList<Asiento>();
			for (Asiento unAsiento : allAsiento){
				if(unAsiento.getEstado().getClass().equals(this.clase)){
					asientosFiltrados.add(unAsiento);
				}
			}
		}
		return asientosFiltrados;
		
	}
}
