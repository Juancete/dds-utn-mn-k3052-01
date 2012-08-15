package ar.com.aterrizar.modelo.state;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;


public class TestSobreReserva {
	
	protected Usuario pepe = new Usuario();
	protected AerolineaLanchitaAdapter aerolineaMok = new AerolineaLanchitaAdapter();
	protected Asiento asientoDisponible;
	
	@Before
	public void setUp(){
		aerolineaMok =  mock(AerolineaLanchitaAdapter.class);
		asientoDisponible = new Asiento("3456", new BigDecimal("343.5"), 'T', 'P', new EstadoDisponible(), aerolineaMok);

	}
	
	@Test
	public void cuandoSeCompraUnAsientoDisponibleCambiaDeEstadoAComprado(){
		asientoDisponible.getEstado().comprar(asientoDisponible, pepe);
		Assert.assertFalse(asientoDisponible.isDisponible());
	}

	@Test
	public void cuandoSeReservaUnAsientoDisponibleCambiaAEstadoReservado(){
		asientoDisponible.getEstado().reservar(asientoDisponible, pepe);
		Assert.assertTrue(asientoDisponible.estadoReservado());
	}
	
	
	
}
