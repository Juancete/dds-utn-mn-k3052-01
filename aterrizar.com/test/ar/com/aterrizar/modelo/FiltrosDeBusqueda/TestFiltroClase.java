package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.aterrizar.fecha.modelo.Fecha;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;

public class TestFiltroClase {
	protected AerolineaLanchitaAdapter aerolineaLancita = new AerolineaLanchitaAdapter();
	
	protected Asiento asientoPrimera = new Asiento("345", new BigDecimal("125.30"),'P', 'P', true, aerolineaLancita);
	protected Asiento asientoEjecutivo = new Asiento("1130", new BigDecimal("2.4"),'C', 'E', false, aerolineaLancita);
	protected Asiento asientoTuristaBarato = new Asiento("234", new BigDecimal("300.50"),'V', 'T', true, aerolineaLancita);
	protected Asiento asientoTuristaCaro = new Asiento("200", new BigDecimal("1000"),'V', 'T', true, aerolineaLancita);
	
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
	public void eliminaDeUnaBusqeudaLosAsientosTurista(){
		unaBusqueda.agregarFiltro(new FiltroClase('T'));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaBarato));
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaCaro));
		Assert.assertTrue(listaFiltrada.contains(asientoEjecutivo));
		Assert.assertTrue(listaFiltrada.contains(asientoPrimera));
	}
	
	@Test
	public void eliminaDeUnaBusqeudaLosAsientosTuristaYLosDePrimera(){
		unaBusqueda.agregarFiltro(new FiltroClase('T'));
		unaBusqueda.agregarFiltro(new FiltroClase('P'));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaBarato));
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaCaro));
		Assert.assertFalse(listaFiltrada.contains(asientoPrimera));
		Assert.assertTrue(listaFiltrada.contains(asientoEjecutivo));
	}
	
	@Test
	public void eliminaDeUnaBusqeudaLosAsientosTuristaYLosDePrimeraConUnOr(){
		unaBusqueda.agregarFiltro(new FiltroOr(new FiltroClase('T'),new FiltroClase('P')));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaBarato));
		Assert.assertFalse(listaFiltrada.contains(asientoTuristaCaro));
		Assert.assertFalse(listaFiltrada.contains(asientoPrimera));
		Assert.assertTrue(listaFiltrada.contains(asientoEjecutivo));
	}	

	@Test
	public void eliminaDeUnaBusqeudaLosAsientosEjecutivos(){
		unaBusqueda.agregarFiltro(new FiltroClase('E'));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertTrue(listaFiltrada.contains(asientoTuristaBarato));
		Assert.assertTrue(listaFiltrada.contains(asientoTuristaCaro));
		Assert.assertTrue(listaFiltrada.contains(asientoPrimera));
		Assert.assertFalse(listaFiltrada.contains(asientoEjecutivo));
	}
}
