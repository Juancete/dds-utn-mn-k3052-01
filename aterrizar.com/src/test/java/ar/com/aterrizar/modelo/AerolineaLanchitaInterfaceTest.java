package ar.com.aterrizar.modelo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.state.EstadoDisponible;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;
import com.lanchita.AerolineaLanchita;

public class AerolineaLanchitaInterfaceTest {

	protected AerolineaLanchitaAdapter aerolineaLanchitaAdapter;
	protected Busqueda busquedaBALA20121010;
	protected AerolineaLanchita aerolineaLancita;
	protected AerolineaLanchita aerolineaLancitaMock;
	protected String[][] retornoImpostor;
	
	@Before
	public void setUp(){
		aerolineaLancita = AerolineaLanchita.getInstance();
		
		retornoImpostor = new String[][] {{ "01202022220202-3", "159.90", "P", "V", "D", "" },{ "01202022220123-3", "205.10", "E", "P", "D", "" }};
		
		aerolineaLancitaMock = mock(AerolineaLanchita.class);
		
		when(aerolineaLancitaMock.asientosDisponibles("BUE", "LA", "20121010", null, null, null)).thenAnswer(new Answer<String[][]>() {
																							public String[][] answer(InvocationOnMock invocation) throws Throwable{
																								return (String[][]) retornoImpostor;
																							}
																				});
		 
		aerolineaLanchitaAdapter = new AerolineaLanchitaAdapter();
		aerolineaLanchitaAdapter.setAerolinea(aerolineaLancitaMock);
		aerolineaLanchitaAdapter.setPorcentajePorCompania(1.2);
		busquedaBALA20121010= new Busqueda("BUE", "LA",new Fecha("20121010",new FormatoSimple("yyyyMMdd")));
	}

	@Test
	public void losAsientosSonGeneradosCorrectamente(){
		final List<Asiento> asientosMock = new ArrayList<Asiento>();
		asientosMock.add(new Asiento("01202022220202-3", (new BigDecimal("159.90")).multiply(new BigDecimal(aerolineaLanchitaAdapter.getPorcentajePorCompania())), 'V', 'P', new EstadoDisponible() , aerolineaLanchitaAdapter));
		asientosMock.add(new Asiento("01202022220123-3", (new BigDecimal("205.10")).multiply(new BigDecimal(aerolineaLanchitaAdapter.getPorcentajePorCompania())), 'P', 'E', new EstadoDisponible() , aerolineaLanchitaAdapter));
		Assert.assertTrue(aerolineaLanchitaAdapter.buscarAsientosConComision(busquedaBALA20121010).containsAll(asientosMock));
	}
	
	@Test
	public void buscoAsientosDisponiblesEnLanchita(){
		List<Asiento> listaDeAsientos = aerolineaLanchitaAdapter.buscarAsientosConComision(busquedaBALA20121010);
		for(Asiento unAsiento : listaDeAsientos){
			//System.out.println(unAsiento);
//			for(String asiento: unAsiento){
//				System.out.println(asiento);
//			}
		}
		
		//System.out.println(aerolineaLanchita.buscarAsientosConComision(busquedaBALA20121010);
	}
	
	@Test
	public void obtenerAsientoApartirDeUnaBusquedaConBue20121010La(){
		String origen = busquedaBALA20121010.getOrigen();
		
		String destino = busquedaBALA20121010.getDestino();
		Fecha fecha = busquedaBALA20121010.getFecha();

		Assert.assertNotNull(aerolineaLancita.asientosDisponibles(origen, destino, Integer.toString(fecha.obtenerAnio()).concat(Integer.toString(fecha.obtenerMes())).concat(Integer.toString(fecha.obtenerDia())), null, null, null));
	}
	
	@Test
	public void probarUnAsientoSuperOferta(){
		Asiento unAsiento = new Asiento("ABCD", new BigDecimal(1000), 'V', 'E', new EstadoDisponible(),aerolineaLanchitaAdapter);
		Assert.assertEquals(unAsiento.soySuperOferta(), true);
	}
	
}
