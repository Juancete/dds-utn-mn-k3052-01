package ar.com.aterrizar.modelo;

import java.util.LinkedList;

import ar.com.aterrizar.entidades.Asiento;

public class SobreReserva {
	
	protected Asiento asiento;
	protected  LinkedList<Usuario> usuarios;
	
	public SobreReserva(Asiento asiento) {		
		this.asiento = asiento;
		this.usuarios = new LinkedList<Usuario>();
	}
	
	public SobreReserva() {
	} 
	
	public void addUsuarioASobreReserva(Usuario unUsuario){
		this.usuarios.add(unUsuario);
	}
	public Usuario returnPrimerUsuarioDisponible(){
		return this.usuarios.remove();		
	}	
	
}
