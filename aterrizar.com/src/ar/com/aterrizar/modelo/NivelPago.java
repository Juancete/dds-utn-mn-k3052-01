package ar.com.aterrizar.modelo;

import java.util.List;

public class NivelPago extends Nivel {

	public NivelPago(Usuario unUsuario) {
		super(unUsuario);
		// TODO Auto-generated constructor stub
	}
	private boolean soyVip(){
		// TODO implementar la condición para ser vip
		return false;
		
	}
	@Override
	public List<Asiento> obtenerAsientosListosParaComprar(List<Asiento> listaDeAsientos) {
		// TODO filtra las super ofertas si no sos vip
		return null;
	}


}
