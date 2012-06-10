package ar.com.aterrizar.modelo;

import java.util.List;

public class NivelNoPago extends Nivel{

	public NivelNoPago(Usuario unUsuario) {
		super(unUsuario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Asiento> obtenerAsientosListosParaComprar(List<Asiento> listaDeAsientos) {
		// TODO Filtro las super ofertas y le sumo $20
		return sumarleUnRecargoALaLista(filtrarSuperOfertas(listaDeAsientos));
	}
	
	private List<Asiento> sumarleUnRecargoALaLista(List<Asiento> listaDeAsientos){
		// TODO Agregar un recargo a una lista.
		return listaDeAsientos;
		
	}

}
