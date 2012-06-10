package ar.com.aterrizar.modelo;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;

public class AerolineaLanchitaInterfaceTest {

	protected AerolineaLanchita  aerolineaLanchita;
	protected Busqueda busquedaBALA20121010;
	
	
	@Before
	public void setUp(){
		
		aerolineaLanchita = AerolineaLanchita.getInstance();
		busquedaBALA20121010= new Busqueda("BUE", "20121010",null, "LA", null, null);
	}
	
	
	@Test
	public void buscoAsientosDisponiblesEnLanchita(){
		List<String[]> filas = Arrays.asList(aerolineaLanchita.asientosDisponibles(null, null, null, null, null, null));
		for(String[] asientos : filas){
			System.out.println(asientos);
			for(String asiento: asientos){
				System.out.println(asiento);
			}
		}
		
		System.out.println(aerolineaLanchita.asientosDisponibles("BUE", "20121010",null, "LA", null, null));
	}
	
	public void obtenerAsientoApartirDeUnaBusquedaConBue20121010La(){
		String origen = busquedaBALA20121010.getOrigen();
		
		String destino = busquedaBALA20121010.getDestino();
		String fechaSalida = busquedaBALA20121010.getFechaSalida();
		String horaSalida = busquedaBALA20121010.getHoraSalida();
		String fechaLlegada = busquedaBALA20121010.getFechaLlegada();
		String horaLlegada = busquedaBALA20121010.getHoraLlegada();		

		Assert.assertNotNull(aerolineaLanchita.asientosDisponibles(origen, destino, fechaSalida, horaSalida, fechaLlegada, horaLlegada));
	}
	
	
}
