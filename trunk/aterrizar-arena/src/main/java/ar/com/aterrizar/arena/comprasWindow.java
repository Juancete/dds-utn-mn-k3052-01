package ar.com.aterrizar.arena;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.state.EstadoComprado;

public class comprasWindow extends vistaWindow {

	public comprasWindow(InicioWindow owner, ShowModel<EstadoComprado> modelo) {
		super(owner, modelo);
	}

	public String setAction(){
		return "Compras de ";
	}
}
