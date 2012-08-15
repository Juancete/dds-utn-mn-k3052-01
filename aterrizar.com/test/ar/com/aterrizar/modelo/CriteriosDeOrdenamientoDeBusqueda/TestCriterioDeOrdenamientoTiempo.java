package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.lanchita.AerolineaLanchita;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

public class TestCriterioDeOrdenamientoTiempo {
	protected List<Asiento>retornoImpostor = new ArrayList<Asiento>();
	protected AerolineaLanchitaAdapter aerolineaLancita;
	
	@Before
	public void setUp(){
		Asiento unAsientoMock =  mock(Asiento.class);
		when(unAsientoMock.getTiempo()).thenReturn((long) 3);
		when(unAsientoMock.getCodigo()).thenReturn((String) "1130");
		retornoImpostor.add(unAsientoMock);
		
		unAsientoMock =  mock(Asiento.class);
		when(unAsientoMock.getTiempo()).thenReturn((long) 10);
		when(unAsientoMock.getCodigo()).thenReturn((String) "345");
		retornoImpostor.add(unAsientoMock);

		unAsientoMock =  mock(Asiento.class);
		when(unAsientoMock.getTiempo()).thenReturn((long) 300);
		when(unAsientoMock.getCodigo()).thenReturn((String) "200");
		retornoImpostor.add(unAsientoMock);
		
		unAsientoMock =  mock(Asiento.class);
		when(unAsientoMock.getTiempo()).thenReturn((long) 15);
		when(unAsientoMock.getCodigo()).thenReturn((String) "234");
		retornoImpostor.add(unAsientoMock);
	}
	
	@Test
	public void ordenoUnaListaPorTiempoAscendente(){
		CriterioDeOrdenamientoTiempoDeVuelo unCriterio = new CriterioDeOrdenamientoTiempoDeVuelo();
		unCriterio.ascendente = true;
		Collections.sort(retornoImpostor,unCriterio);
		Assert.assertEquals(retornoImpostor.get(0).getCodigo(),"1130");
		Assert.assertEquals(retornoImpostor.get(1).getCodigo(),"345");
		Assert.assertEquals(retornoImpostor.get(2).getCodigo(),"234");
		Assert.assertEquals(retornoImpostor.get(3).getCodigo(),"200");		
	}

	@Test
	public void ordenoUnaListaPorTiempoDescendente(){
		CriterioDeOrdenamientoTiempoDeVuelo unCriterio = new CriterioDeOrdenamientoTiempoDeVuelo();
		unCriterio.ascendente = false;
		Collections.sort(retornoImpostor,unCriterio);
		Assert.assertEquals(retornoImpostor.get(3).getCodigo(),"1130");
		Assert.assertEquals(retornoImpostor.get(2).getCodigo(),"345");
		Assert.assertEquals(retornoImpostor.get(1).getCodigo(),"234");
		Assert.assertEquals(retornoImpostor.get(0).getCodigo(),"200");		
	}
}
