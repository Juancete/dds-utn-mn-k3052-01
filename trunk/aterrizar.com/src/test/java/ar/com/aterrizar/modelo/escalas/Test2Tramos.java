package ar.com.aterrizar.modelo.escalas;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.adapter.AerolineaOceanicAdapter;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;
import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

public class Test2Tramos {

	protected AerolineaOceanic aerolineaOceanicMock;
	protected AerolineaOceanicAdapter aerolineaOceanicAdapter;
	private List<AsientoDTO> retornoImpostorPrimerTramo;
	private List<AsientoDTO> retornoImpostor2Tramo;
	
	@Before
	public void setUp(){
		aerolineaOceanicMock = mock(AerolineaOceanic.class);
		retornoImpostorPrimerTramo = new ArrayList<AsientoDTO>();
		retornoImpostorPrimerTramo.add(new AsientoDTO("OC100",10,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"BUE","CAR"));
		retornoImpostorPrimerTramo.add(new AsientoDTO("OC100",10,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"BUE","KIV"));
		
		when(aerolineaOceanicMock.asientosDisponiblesParaOrigen("BUE", "11/11/2012")).thenAnswer(new Answer<List<AsientoDTO>>() {
			public List<AsientoDTO> answer(InvocationOnMock invocation) throws Throwable{
				return (List<AsientoDTO>) retornoImpostorPrimerTramo;
			}
		});
		
		retornoImpostor2Tramo = new ArrayList<AsientoDTO>();
		retornoImpostor2Tramo.add(new AsientoDTO("OC100",10,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"CAR","MIM"));
		
		when(aerolineaOceanicMock.asientosDisponiblesParaOrigenYDestino("CAR", "MIM", "11/11/2012")).thenAnswer(new Answer<List<AsientoDTO>>() {
			public List<AsientoDTO> answer(InvocationOnMock invocation) throws Throwable{
				return (List<AsientoDTO>) retornoImpostor2Tramo;
			}
		});
		
		when(aerolineaOceanicMock.asientosDisponiblesParaOrigenYDestino("KIV", "MIM", "11/11/2012")).thenAnswer(new Answer<List<AsientoDTO>>() {
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
