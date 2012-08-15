package ar.com.aterrizar.modelo.state;


import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.AterrizarApp;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;


public class OperacionesDeCompraYReservaDeAsientoTest {
	
	protected Usuario pepe = new Usuario();
	protected Usuario martin = new Usuario();
	protected Usuario tony = new Usuario();
	protected AerolineaLanchitaAdapter aerolineaMok = new AerolineaLanchitaAdapter();
	protected Asiento asientoDisponible;
	protected static AterrizarApp aplication = AterrizarApp.getInstance();
	
	@Before
	public void setUp(){
		aerolineaMok =  mock(AerolineaLanchitaAdapter.class);
		asientoDisponible = new Asiento("3456", new BigDecimal("343.5"), 'T', 'P', new EstadoDisponible(), aerolineaMok);
		pepe.setDNI("23434234324");
		martin.setDNI("454545454");
		tony.setDNI("324342343");

	}
	
	@Test
	public void cuandoSeCompraUnAsientoDisponibleCambiaDeEstadoAComprado(){
		asientoDisponible.getEstado().comprar(asientoDisponible, pepe);
		Assert.assertFalse(asientoDisponible.isDisponible());
	}

	@Test
	public void cuandoSeReservaUnAsientoDisponibleCambiaAEstadoReservado(){
		asientoDisponible.getEstado().reservar(asientoDisponible, pepe);
		asientoDisponible.getEstado().reservar(asientoDisponible, martin);
		Assert.assertTrue(asientoDisponible.tieneSobreReserva());
		Assert.assertTrue(asientoDisponible.estadoReservado());
	}
	
	@Test
	public void cuandoUnAsientoReservadoConSobreReservasEsCompradosLasSobreReservasSeCancelan(){
		asientoDisponible.getEstado().reservar(asientoDisponible, pepe);
		asientoDisponible.getEstado().reservar(asientoDisponible, martin);
		//primer Assert el asiento esta reservado
		Assert.assertTrue(asientoDisponible.estadoReservado());
		
		//Segundo Assert el asiento tiene sobreReserva
		Assert.assertTrue(asientoDisponible.tieneSobreReserva());
		//se hace efectiva la compra del asiento, debe cambiar el estado y a comprado
		//y las sobrereservas deben eliminarse.
		asientoDisponible.getEstado().comprar(asientoDisponible, pepe);		
		
		//Asiento no tiene sobre reserva
		Assert.assertTrue(!asientoDisponible.tieneSobreReserva());
		
	}
	
	@Test
	public void cuandoUnAsientoDisponibleEsCompradosElAsientoQuedaEnEstadoComprado(){
		//primer Assert el asiento esta disponible
		Assert.assertTrue(asientoDisponible.isDisponible());
		//se compra el asiento
     	asientoDisponible.getEstado().comprar(asientoDisponible, pepe);
		//El asiento quedo con el estado de comprado
		Assert.assertTrue(asientoDisponible.getEstado() instanceof EstadoComprado);
	}
	
	@Test
	public void cuandoUnAsientoReservadoEsCompradosElAsientoQuedaEnEstadoComprado(){
		//primer Assert el asiento esta disponible
		Assert.assertTrue(asientoDisponible.isDisponible());
     	//Reservando el asiento
     	asientoDisponible.getEstado().reservar(asientoDisponible, pepe);     	
     	//Estado reservado
     	Assert.assertTrue(asientoDisponible.estadoReservado());     	
     	//Compra del asiento
     	asientoDisponible.getEstado().comprar(asientoDisponible, pepe);		
		//El asiento quedo con el estado de comprado
		Assert.assertTrue(asientoDisponible.getEstado() instanceof EstadoComprado);		
	}
	
	@Test(expected=NoSeEncuentraDisponibleElAsientoException.class)
	public void noSePuedeComprarUnAsientoyaComprados(){		
     	asientoDisponible.getEstado().comprar(asientoDisponible, pepe);
     	//se intenta comprar nuevamente el asiento, falla y el test captura la excepcion
     	asientoDisponible.getEstado().comprar(asientoDisponible, martin);
     		
	}
	
	@Test(expected=NoSeEncuentraDisponibleElAsientoException.class)
	public void noSePuedeComprarUnAsientoReservadoPorOtro(){		
     	asientoDisponible.getEstado().reservar(asientoDisponible, pepe);
     	//se intenta comprar nuevamente el asiento, falla y el test captura la excepcion
     	asientoDisponible.getEstado().comprar(asientoDisponible, martin);
	}

	@Test
	public void cuandoCancelanUnaReservaLaReservaLaEfectivizaelPrimeroDelaLista(){		
		asientoDisponible.getEstado().reservar(asientoDisponible, pepe);
		asientoDisponible.getEstado().reservar(asientoDisponible, martin);
		asientoDisponible.getEstado().reservar(asientoDisponible, tony);
		//martin es el primero de la lista de sobrereservas
		//aplication.reservaExpirada(unAsiento)
				
		Assert.assertTrue(asientoDisponible.getReservante().getDni().equalsIgnoreCase(pepe.getDni()));
		//Assert el asiento esta reservado		
		//El asiento tiene sobreReserva
		Assert.assertTrue(asientoDisponible.tieneSobreReserva());

		//Se expira la reserva y como hay sobre reservas toma la reserva el primero de la lista que es martin
		aplication.reservaExpirada(asientoDisponible);		
		
		Assert.assertTrue(asientoDisponible.getReservante().getDni().equalsIgnoreCase(martin.getDni()));
		
		Assert.assertTrue(asientoDisponible.estadoReservado());
		
	}
	
	
	
	
}
