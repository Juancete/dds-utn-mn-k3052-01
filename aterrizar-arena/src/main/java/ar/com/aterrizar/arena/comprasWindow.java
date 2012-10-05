package ar.com.aterrizar.arena;

import ar.com.aterrizar.modelo.Usuario;

public class comprasWindow extends vistaWindow {

	public comprasWindow(InicioWindow owner, Usuario modelUsuario) {
		super(owner, modelUsuario);
	}

	public String setAction(){
		return "Compras de ";
	}
}
