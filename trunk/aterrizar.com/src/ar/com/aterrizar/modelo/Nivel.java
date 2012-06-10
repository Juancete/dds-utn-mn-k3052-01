package ar.com.aterrizar.modelo;

import java.util.List;

public abstract class Nivel {

	Usuario unUsuario;
	
	public Nivel (Usuario unUsuario){
		super();
		this.unUsuario = unUsuario;
	}
	
	public void setUsuario(Usuario unUsuario){
	this.unUsuario = unUsuario;	
	}

	public List<Asiento> filtrarSuperOfertas(List<Asiento> listaDeAsientos){
		
		return listaDeAsientos;
		
	}
	
	public abstract List<Asiento> obtenerAsientosListosParaComprar(List<Asiento> listaDeAsientos);

}
