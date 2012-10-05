package ar.com.aterrizar.arena;

import ar.com.aterrizar.modelo.Usuario;

public class reservasWindow extends vistaWindow{
	
	public reservasWindow(InicioWindow owner, Usuario modelUsuario) {
		super(owner, modelUsuario);
	}

	public String setAction(){
		return "Reservas de ";
	}
}
