package ar.com.aterrizar.arena.appmodel;

import java.util.List;

import org.uqbar.commons.model.Home;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.state.EstadoReservado;

public class ShowReservadosModel extends ShowModel<Asiento> {

	private List<Asiento> asientosFiltrados;
	
	public ShowReservadosModel(Home<Asiento> home) {
		super(home);
	}

	@Override
	public List<Asiento> getResults() {
		List<Asiento> allAsiento = super.getResults();
		for (Asiento unAsiento : allAsiento){
			if(unAsiento.getEstado().getClass().equals(EstadoReservado.class)){
				asientosFiltrados.add(unAsiento);
			}
		}
		return allAsiento;
		
	}
}
