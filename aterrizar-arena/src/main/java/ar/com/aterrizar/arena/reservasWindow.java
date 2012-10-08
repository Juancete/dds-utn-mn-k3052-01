package ar.com.aterrizar.arena;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.modelo.state.EstadoReservado;

public class reservasWindow extends vistaWindow{
	
	public reservasWindow(InicioWindow owner, ShowModel<EstadoReservado> modelo) {
		super(owner, modelo);
	}

	public String setAction(){
		return "Reservas de ";
	}
}
