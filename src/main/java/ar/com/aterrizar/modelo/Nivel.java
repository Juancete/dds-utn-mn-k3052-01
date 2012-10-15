package ar.com.aterrizar.modelo;


import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.entidades.Asiento;

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
		List<Asiento> listaSinSuperOfertas = new ArrayList<Asiento>();
		for(Asiento unAsiento : listaDeAsientos ){
			if(!unAsiento.soySuperOferta()){
				listaSinSuperOfertas.add(unAsiento);
			}
		}
return listaSinSuperOfertas;
	}
	
	public abstract List<Asiento> obtenerAsientosListosParaComprar(List<Asiento> listaDeAsientos);

}
