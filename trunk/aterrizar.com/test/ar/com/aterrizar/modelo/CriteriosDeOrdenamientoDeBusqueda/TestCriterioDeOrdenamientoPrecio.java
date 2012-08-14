package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.modelo.*;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;

public class TestCriterioDeOrdenamientoPrecio {

	protected List<Asiento>retornoImpostor = new ArrayList<Asiento>();
	protected AerolineaLanchitaAdapter aerolineaLancita;
	
	@Before
	public void setUp(){
		
		retornoImpostor.add(new Asiento("345", new BigDecimal("125.30"),'P', 'D', true, aerolineaLancita));
		retornoImpostor.add(new Asiento("234", new BigDecimal("300.50"),'V', 'D', true, aerolineaLancita));
		retornoImpostor.add(new Asiento("1130", new BigDecimal("2.4"),'P', 'D', false, aerolineaLancita));
		retornoImpostor.add(new Asiento("200", new BigDecimal("1000"),'V', 'D', true, aerolineaLancita));
	}
	
	@Test
	public void ordenoUnaListaPorPrecioAscendente(){
		CriterioDeOrdenamientoPrecio unCriterio = new CriterioDeOrdenamientoPrecio();
		unCriterio.ascendente = true;
		Collections.sort(retornoImpostor,unCriterio);
		Assert.assertEquals(retornoImpostor.get(0).codigo,"1130");
		Assert.assertEquals(retornoImpostor.get(1).codigo,"345");
		Assert.assertEquals(retornoImpostor.get(2).codigo,"234");
		Assert.assertEquals(retornoImpostor.get(3).codigo,"200");
	}

	@Test
	public void ordenoUnaListaPorPrecioDescendente(){
		CriterioDeOrdenamientoPrecio unCriterio = new CriterioDeOrdenamientoPrecio();
		unCriterio.ascendente = false;
		Collections.sort(retornoImpostor,unCriterio);
		Assert.assertEquals(retornoImpostor.get(3).codigo,"1130");
		Assert.assertEquals(retornoImpostor.get(2).codigo,"345");
		Assert.assertEquals(retornoImpostor.get(1).codigo,"234");
		Assert.assertEquals(retornoImpostor.get(0).codigo,"200");
	}
}
