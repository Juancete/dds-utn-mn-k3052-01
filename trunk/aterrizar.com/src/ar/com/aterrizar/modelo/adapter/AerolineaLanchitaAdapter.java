package ar.com.aterrizar.modelo.adapter;

//import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.modelo.Aerolinea;
import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;

import com.lanchita.AerolineaLanchita;

public class AerolineaLanchitaAdapter extends Aerolinea {

	protected Integer porcentajePorCompania;
	protected AerolineaLanchita aerolinea;

	public AerolineaLanchitaAdapter() {
		aerolinea = AerolineaLanchita.getInstance();
	}

	/*
	 * String[][] com.lanchita.AerolineaLanchita.asientosDisponibles(String
	 * origen, String destino, String fechaSalida, String horaSalida, String
	 * fechaLlegada, String horaLlegada)
	 */
	public List<Asiento> buscarAsientos(Busqueda unaBusqueda) {

		//TODO falta implementar la logica, agregar en asiento el parser para la construccion de asciento a partir de una lista de strings
		//		String origen = unaBusqueda.getOrigen();
//
//		String destino = unaBusqueda.getDestino();
//		String fechaSalida = unaBusqueda.getFechaSalida();
//		String horaSalida = unaBusqueda.getHoraSalida();
//		String fechaLlegada = unaBusqueda.getFechaLlegada();
//		String horaLlegada = unaBusqueda.getHoraLlegada();
//
//		int indexy = 0;
//		String[][] datosAsientos = this.aerolinea.asientosDisponibles(origen,
//				destino, fechaSalida, horaSalida, fechaLlegada, horaLlegada);
//		if (datosAsientos == null)
//			return new ArrayList<Asiento>(0);
//		
//		ArrayList<Asiento> asientos = new ArrayList<Asiento>();
//		
//		for (int indexx = 0; indexx < datosAsientos.length; indexx++) {
//			System.out.println(datosAsientos[indexx][indexy]);
//		}

		throw new RuntimeException("must implements");
	}

	@Override
	/**
	 * <h1>Dado un codigo de Asiento compra efectivamente el asiento
	 */
	public void comprarAsiento(String codigoAsiento) {
		// TODO Implementar el codigo
		
	}

}
