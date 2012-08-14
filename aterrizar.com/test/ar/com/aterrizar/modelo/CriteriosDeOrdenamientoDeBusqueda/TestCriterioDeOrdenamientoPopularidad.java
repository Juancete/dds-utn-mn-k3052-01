package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.aterrizar.fecha.modelo.Fecha;

import ar.com.aterrizar.modelo.*;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;

public class TestCriterioDeOrdenamientoPopularidad {

	protected List<Asiento>retornoImpostor = new ArrayList<Asiento>();
	protected AerolineaLanchitaAdapter aerolineaLancita;
	
	@Before
	public void setUp(){
		Fecha unaFecha = new Fecha();
		Asiento unAsiento = new Asiento("345", new BigDecimal("125.30"),'P', 'D', true, aerolineaLancita);
		unAsiento.setVuelo(new Vuelo("AR345", "BUE", "LA", unaFecha, unaFecha));
		unAsiento.getVuelo().setPopularidad(3);
		retornoImpostor.add(unAsiento);
		
		unAsiento = new Asiento("234", new BigDecimal("300.50"),'V', 'D', true, aerolineaLancita);
		unAsiento.setVuelo(new Vuelo("BR222", "BRA", "GR", unaFecha, unaFecha));
		unAsiento.getVuelo().setPopularidad(15);
		retornoImpostor.add(unAsiento);

		unAsiento = new Asiento("1130", new BigDecimal("2.4"),'P', 'D', false, aerolineaLancita);
		unAsiento.setVuelo(new Vuelo("PL666", "GRA", "USA", unaFecha, unaFecha));
		unAsiento.getVuelo().setPopularidad(30);
		retornoImpostor.add(unAsiento);
		
		unAsiento = new Asiento("200", new BigDecimal("1000"),'V', 'D', true, aerolineaLancita);
		unAsiento.setVuelo(new Vuelo("SZ999", "ESP", "CAN", unaFecha, unaFecha));
		unAsiento.getVuelo().setPopularidad(1);
		retornoImpostor.add(unAsiento);
	}
	
	@Test
	public void ordenoUnaListaPorPopularidadAscendente(){
		CriterioDeOrdenamientoPopularidad unCriterio = new CriterioDeOrdenamientoPopularidad();
		unCriterio.ascendente = true;
		Collections.sort(retornoImpostor,unCriterio);
		Assert.assertEquals(retornoImpostor.get(0).codigo,"200");
		Assert.assertEquals(retornoImpostor.get(1).codigo,"345");
		Assert.assertEquals(retornoImpostor.get(2).codigo,"234");
		Assert.assertEquals(retornoImpostor.get(3).codigo,"1130");
	}

	@Test
	public void ordenoUnaListaPorPrecioDescendente(){
		CriterioDeOrdenamientoPopularidad unCriterio = new CriterioDeOrdenamientoPopularidad();
		unCriterio.ascendente = false;
		Collections.sort(retornoImpostor,unCriterio);
		Assert.assertEquals(retornoImpostor.get(3).codigo,"200");
		Assert.assertEquals(retornoImpostor.get(2).codigo,"345");
		Assert.assertEquals(retornoImpostor.get(1).codigo,"234");
		Assert.assertEquals(retornoImpostor.get(0).codigo,"1130");
	}
}
