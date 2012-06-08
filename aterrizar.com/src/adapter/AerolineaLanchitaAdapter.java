package adapter;

import ar.com.aterrizar.modelo.Busqueda;

import com.lanchita.AerolineaLanchita;

public class AerolineaLanchitaAdapter {
	
	protected Integer porcentajePorCompania;
	protected AerolineaLanchita aerolinea;
	
	public AerolineaLanchitaAdapter(){
		aerolinea = AerolineaLanchita.getInstance();
	}
	
	/*String[][] com.lanchita.AerolineaLanchita.asientosDisponibles(String origen, String destino, String fechaSalida, String horaSalida, String fechaLlegada, String horaLlegada)*/
	public void buscarAsiento(Busqueda unaBusqueda){
	//	aerolinea.asientosDisponibles(origen, destino, fechaSalida, horaSalida, fechaLlegada, horaLlegada)
	}	

}
