package ar.com.aterrizar.modelo;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;

public class AerolineaLanchitaInterfaceTest {

	protected AerolineaLanchita  aerolineaLanchita;
	
	
	
	@Before
	public void setUp(){
		
		aerolineaLanchita = AerolineaLanchita.getInstance();
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
	}
	
	
}
