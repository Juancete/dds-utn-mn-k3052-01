package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

import com.aterrizar.fecha.modelo.Fecha;

public class TestFiltroUbicacion {

	protected AerolineaLanchitaAdapter aerolineaLancita = new AerolineaLanchitaAdapter();
	
	protected Asiento asientoPasillo = new Asiento("345", new BigDecimal("125.30"),'P', 'D', new EstadoDisponible(), aerolineaLancita);
	protected Asiento asientoCentro = new Asiento("1130", new BigDecimal("2.4"),'C', 'D', new EstadoReservado(), aerolineaLancita);
	protected Asiento asientoVentanaBarato = new Asiento("234", new BigDecimal("300.50"),'V', 'R', new EstadoDisponible(), aerolineaLancita);
	protected Asiento asientoVentanaCaro = new Asiento("200", new BigDecimal("1000"),'V', 'R', new EstadoDisponible(), aerolineaLancita);
	
	protected List<Asiento>retornoImpostor = new ArrayList<Asiento>();
	protected Busqueda unaBusqueda;
	@Before
	public void setUp(){
		retornoImpostor.add(asientoPasillo);
		retornoImpostor.add(asientoVentanaBarato);
		retornoImpostor.add(asientoCentro);
		retornoImpostor.add(asientoVentanaCaro);
		Fecha unaFecha = new Fecha();
		unaBusqueda = new Busqueda("BUE","LA",unaFecha);
	}
	
	@Test
	public void eliminaDeUnaBusqeudaLosAsientosDeVentanilla(){
		unaBusqueda.agregarFiltro(new FiltroUbicacion('V'));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertFalse(listaFiltrada.contains(asientoVentanaBarato));
		Assert.assertFalse(listaFiltrada.contains(asientoVentanaCaro));
	}

	@Test
	public void eliminaDeUnaBusqeudaLosAsientosDeVentanillaYElDePasillo(){
		unaBusqueda.agregarFiltro(new FiltroUbicacion('V'));
		unaBusqueda.agregarFiltro(new FiltroUbicacion('P'));
		List<Asiento> listaFiltrada = unaBusqueda.filtrarAsientos(retornoImpostor);
		Assert.assertFalse(listaFiltrada.contains(asientoVentanaBarato));
		Assert.assertFalse(listaFiltrada.contains(asientoVentanaCaro));
		Assert.assertFalse(listaFiltrada.contains(asientoPasillo));
		Assert.assertTrue(listaFiltrada.contains(asientoCentro));
	}
}
