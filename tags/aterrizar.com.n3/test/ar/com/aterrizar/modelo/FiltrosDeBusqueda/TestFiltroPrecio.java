package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

import com.aterrizar.fecha.modelo.Fecha;

public class TestFiltroPrecio {
	
	protected AerolineaLanchitaAdapter aerolineaLancita = new AerolineaLanchitaAdapter();
	
	protected Asiento asientoPrimera = new Asiento("345", new BigDecimal("125.30"),'P', 'P', new EstadoDisponible(), aerolineaLancita);
	protected Asiento asientoEjecutivo = new Asiento("1130", new BigDecimal("2.4"),'C', 'E', new EstadoReservado(), aerolineaLancita);
	protected Asiento asientoTuristaBarato = new Asiento("234", new BigDecimal("300.50"),'V', 'T', new EstadoDisponible(), aerolineaLancita);
	protected Asiento asientoTuristaCaro = new Asiento("200", new BigDecimal("1000"),'V', 'T', new EstadoDisponible(), aerolineaLancita);
	
	protected List<Asiento>retornoImpostor = new ArrayList<Asiento>();
	protected Busqueda unaBusqueda;
	
	@Before
	public void setUp(){
		retornoImpostor.add(asientoPrimera);
		retornoImpostor.add(asientoTuristaBarato);
		retornoImpostor.add(asientoEjecutivo);
		retornoImpostor.add(asientoTuristaCaro);
		Fecha unaFecha = new Fecha();
		unaBusqueda = new Busqueda("BUE","LA",unaFecha);
	}
	
	@Test
	public void dejoDeUnaBusqeudaLosAsientosEntre500Y1000(){
		unaBusqueda.agregarFiltro(new FiltroPrecio(new BigDecimal("400"),new BigDecimal("100")));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertTrue(listaFiltrada.contains(asientoTuristaBarato));
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaCaro));
		Assert.assertFalse(listaFiltrada.contains(asientoEjecutivo));
		Assert.assertTrue(listaFiltrada.contains(asientoPrimera));		
	}
	
	@Test
	public void dejoDeUnaBusqeudaLosAsientosEntre100Y400(){
		unaBusqueda.agregarFiltro(new FiltroPrecio(new BigDecimal("1000"),new BigDecimal("400")));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaBarato));
		Assert.assertTrue(listaFiltrada.contains(asientoTuristaCaro));
		Assert.assertFalse(listaFiltrada.contains(asientoEjecutivo));
		Assert.assertFalse(listaFiltrada.contains(asientoPrimera));		
	}	
}
