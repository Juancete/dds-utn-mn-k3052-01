package ar.com.aterrizar.modelo.escalas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Vuelo;
import ar.com.aterrizar.modelo.adapter.AerolineaOceanicAdapter;
import ar.com.aterrizar.modelo.state.EstadoDisponible;

import org.mockito.invocation.*;
import org.mockito.stubbing.*;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;
import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

import static org.mockito.Mockito.*;

public class TestUnicoAsiento {

	protected AerolineaOceanic aerolineaOceanicMock;
	protected AerolineaOceanicAdapter aerolineaOceanicAdapter;
	private List<AsientoDTO> retornoImpostor;
	
	@Before
	public void setUp(){
		aerolineaOceanicMock = mock(AerolineaOceanic.class);
		retornoImpostor = new ArrayList<AsientoDTO>();
		Asiento asiento = new Asiento("OC10010", new BigDecimal("3150.98"), 'P', 'E', new EstadoDisponible() , aerolineaOceanicAdapter);
		Vuelo vuelo = new Vuelo("OC10010", "BUE", "MIM", new Fecha("20121111",new FormatoSimple("yyyyMMdd")), new Fecha("20121111",new FormatoSimple("yyyyMMdd")));
		asiento.setVuelo(vuelo);
		retornoImpostor = new ArrayList<AsientoDTO>();
		retornoImpostor.add(new AsientoDTO("OC100",10,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"BUE","MIM"));

		when(aerolineaOceanicMock.asientosDisponiblesParaOrigen("BUE", "11/11/2012")).thenAnswer(new Answer<List<AsientoDTO>>() {
			public List<AsientoDTO> answer(InvocationOnMock invocation) throws Throwable{
				return (List<AsientoDTO>) retornoImpostor;
			}
		});
		
		when(aerolineaOceanicMock.asientosDisponiblesParaOrigenYDestino("MIM", "MIM", "11/11/2012")).thenAnswer(new Answer<List<AsientoDTO>>() {
			public List<AsientoDTO> answer(InvocationOnMock invocation) throws Throwable{
				return (List<AsientoDTO>) new ArrayList<AsientoDTO>();
			}
		});
		
		aerolineaOceanicAdapter = new AerolineaOceanicAdapter();
		aerolineaOceanicAdapter.setAerolinea(aerolineaOceanicMock);
		aerolineaOceanicAdapter.setPorcentajePorCompania(1.2);

	}
	
	@Test
	public void comprueboQueElResultadoSeaUnaListaDeAsientosConUnElemento(){
		Busqueda busquedaDeUsuario = new Busqueda("BUE", "MIM", new Fecha("20121111",new FormatoSimple("yyyyMMdd")), 1);
		ConstructorDeEscalas constructor = new ConstructorDeEscalas(busquedaDeUsuario, aerolineaOceanicAdapter);
		constructor.construirPrimeraEscala();
		constructor.construirTodasLasEscalasPosibles();
		constructor.construirEscalaFinal();
		List<Asiento> resultado = constructor.construir();
		Assert.assertTrue(!resultado.isEmpty());
	}
}
