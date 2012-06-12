package ar.com.aterrizar.modelo;

import java.math.BigDecimal;
import java.util.List;

public class NivelPago extends Nivel {
	
	BigDecimal montoMinimoParaSerVip;

	public NivelPago(Usuario unUsuario) {
		super(unUsuario);
		montoMinimoParaSerVip = new BigDecimal(100000);
	}
	
	private boolean soyVip(){
	return(unUsuario.montoAcumulado.compareTo(montoMinimoParaSerVip) == 1);
	}
	
	@Override
	public List<Asiento> obtenerAsientosListosParaComprar(List<Asiento> listaDeAsientos) {
		if(!soyVip()){
			listaDeAsientos = this.filtrarSuperOfertas(listaDeAsientos);
		}
		return listaDeAsientos;
	}


}
