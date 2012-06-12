package ar.com.aterrizar.modelo;

import static org.mockito.Mockito.*;
import org.mockito.invocation.*;
import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import com.lanchita.AerolineaLanchita;

import ar.com.aterrizar.modelo.adapter.*;

public class AerolineaLanchitaInterfaceTest {

	protected AerolineaLanchitaAdapter aerolineaLanchitaAdapter;
	protected Busqueda busquedaBALA20121010;
	protected AerolineaLanchita aerolineaLancitaMock;
	protected String[][] retornoImpostor= new String[][] {{ "01202022220202-3", "159.90", "P", "V", "D", "" },{ "01202022220123-3", "205.10", "E", "P", "D", "" }};
	
	@Before
	public void setUp(){
		
		aerolineaLancitaMock = mock(AerolineaLanchita.class);
		
		when(aerolineaLancitaMock.asientosDisponibles("BUE", "LA", "20121010", null, null, null)).thenAnswer(new Answer<String[][]>() {
																							public String[][] answer(InvocationOnMock invocation) throws Throwable{
																								return (String[][]) retornoImpostor;
																							}
																				});
		 
		aerolineaLanchitaAdapter = new AerolineaLanchitaAdapter();
		aerolineaLanchitaAdapter.setAerolinea(aerolineaLancitaMock);
		aerolineaLanchitaAdapter.setPorcentaje(1.2);
		busquedaBALA20121010= new Busqueda("BUE", "20121010",null, "LA", null, null);
	}
	
	
	@Test
	public void buscoAsientosDisponiblesEnLanchita(){
		List<Asiento> listaDeAsientos = aerolineaLanchitaAdapter.buscarAsientosConComision(busquedaBALA20121010);
		for(Asiento unAsiento : listaDeAsientos){
			System.out.println(unAsiento);
//			for(String asiento: unAsiento){
//				System.out.println(asiento);
//			}
		}
		
		//System.out.println(aerolineaLanchita.buscarAsientosConComision(busquedaBALA20121010);
	}
	
	public void obtenerAsientoApartirDeUnaBusquedaConBue20121010La(){
		String origen = busquedaBALA20121010.getOrigen();
		
		String destino = busquedaBALA20121010.getDestino();
		String fechaSalida = busquedaBALA20121010.getFechaSalida();
		String horaSalida = busquedaBALA20121010.getHoraSalida();
		String fechaLlegada = busquedaBALA20121010.getFechaLlegada();
		String horaLlegada = busquedaBALA20121010.getHoraLlegada();		

//		Assert.assertNotNull(aerolineaLanchita.asientosDisponibles(origen, destino, fechaSalida, horaSalida, fechaLlegada, horaLlegada));
	}
	
	@Test
	public void probarUnAsientoSuperOferta(){
		Asiento unAsiento = new Asiento("ABCD", new BigDecimal(1000), 'V', 'E', true,aerolineaLanchitaAdapter);
		Assert.assertEquals(unAsiento.soySuperOferta(), true);
	}
	
}
