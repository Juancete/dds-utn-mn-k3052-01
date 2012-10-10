package ar.com.aterrizar.modelo;

import java.math.BigDecimal;
import java.util.List;

import ar.com.aterrizar.entidades.Asiento;

public class NivelNoPago extends Nivel{
	
	BigDecimal recargo;
	
	public NivelNoPago(Usuario unUsuario) {
		super(unUsuario);
		recargo = new BigDecimal(20);
	}

	@Override
	public List<Asiento> obtenerAsientosListosParaComprar(List<Asiento> listaDeAsientos) {
		listaDeAsientos= this.filtrarSuperOfertas(listaDeAsientos);
		sumarleUnRecargoALaLista(listaDeAsientos);
		return listaDeAsientos;
	}
	
	private void sumarleUnRecargoALaLista(List<Asiento> listaDeAsientos){
		for(Asiento unAsiento : listaDeAsientos){
			unAsiento.precio = unAsiento.precio.add(recargo);
		}
	}

}
