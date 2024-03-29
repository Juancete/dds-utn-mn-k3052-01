package ar.com.aterrizar.modelo;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.NoHayAsientosDisponiblesParaUnaBusquedaException;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;

public class UsuarioTest {

	private NivelPago nivelPagoVIP;
	private NivelPago nivelPagoNoVIP;
	private NivelNoPago nivelNoPago;
	private Usuario usuarioPagoVIP;
	private Usuario usuarioPagoNoVip;
	private Usuario usuarioNoPago;
	private Asiento asientoSuperOferta;
	private Asiento asientoNormal;
	private Asiento asientoNoDisponible;
	private Aerolinea aerolineaAdapterMock;
	private Busqueda unaBusqueda;
	private Busqueda otraBusqueda;
	
	@Before
	public void setUp() throws Exception {
		
		unaBusqueda= new Busqueda("BUE", "LA", new Fecha("20121010",new FormatoSimple("yyyyMMdd")));
		otraBusqueda = new Busqueda("BUE","RIO", new Fecha("20121111",new FormatoSimple("yyyyMMdd")));
		
		//usuario Pago VIP
		usuarioPagoVIP = new Usuario();
		nivelPagoVIP = new NivelPago(usuarioPagoVIP);
		usuarioPagoVIP.setNivel(nivelPagoVIP);
		usuarioPagoVIP.montoAcumulado = new BigDecimal(1000000);
		
		//usuario Pago No Vip
		usuarioPagoNoVip = new Usuario();
		nivelPagoNoVIP = new NivelPago(usuarioPagoNoVip);
		usuarioPagoNoVip.setNivel(nivelPagoNoVIP);
		usuarioPagoNoVip.montoAcumulado = new BigDecimal(10);
		
		usuarioNoPago = new Usuario();
		nivelNoPago = new NivelNoPago(usuarioNoPago);
		usuarioNoPago.setNivel(nivelNoPago);
		usuarioNoPago.montoAcumulado = new BigDecimal(0);
		
		aerolineaAdapterMock = mock(Aerolinea.class);
		asientoSuperOferta = new Asiento("Asiento Super Ofertoso", new BigDecimal(1000), 'V', 'P', new EstadoDisponible(), aerolineaAdapterMock);
		asientoNormal = new Asiento("Asiento comun", new BigDecimal(10000), 'V', 'P', new EstadoDisponible(), aerolineaAdapterMock);
		asientoNoDisponible = new Asiento("Asiento Super Ofertoso", new BigDecimal(1000), 'V', 'P', new EstadoReservado(), aerolineaAdapterMock);
		List<Asiento> listaImpostora = new ArrayList<Asiento>();
		listaImpostora.add(asientoSuperOferta);
		listaImpostora.add(asientoNormal);
		listaImpostora.add(asientoNoDisponible);
		
		doThrow(new NoSeEncuentraDisponibleElAsientoException()).when(aerolineaAdapterMock).comprarAsiento(asientoNoDisponible,null);
		when(aerolineaAdapterMock.buscarAsientosConComision(unaBusqueda)).thenReturn(listaImpostora);
		when(aerolineaAdapterMock.buscarAsientosConComision(otraBusqueda)).thenThrow(new NoHayAsientosDisponiblesParaUnaBusquedaException());
		
	}

	@Test
	public void siUnUsuarioCompraUnAsientoDisponibleSeModificaSuMonto(){	
		usuarioPagoVIP.comprarUnAsiento(asientoNormal);
		Assert.assertTrue(usuarioPagoVIP.montoAcumulado.equals(new BigDecimal(1010000)));
	}
	
	@Test (expected=NoSeEncuentraDisponibleElAsientoException.class)
	public void siUnUsuarioCompraUnAsientoNoDisponibleDisparaUnaNoSeEncuentraDisponibleElAsientoException(){
		usuarioPagoVIP.comprarUnAsiento(asientoNoDisponible);
	}
	
	@Test (expected=NoHayAsientosDisponiblesParaUnaBusquedaException.class)
	public void siUnUsuarioBuscayNoHayDisponibilidadesEmiteUnaException(){
		usuarioPagoVIP.buscarAsiento(otraBusqueda, aerolineaAdapterMock);
	}	
	
	@Test
	public void siUnUsuarioRealizaUnaBusquedaSeAgregaLaBusquedaASuLista(){
		usuarioNoPago.buscarAsiento(unaBusqueda, aerolineaAdapterMock);
		Assert.assertTrue(usuarioNoPago.getBusquedasRealizadas().contains(unaBusqueda));
	}
	
	@Test
	public void siUnUsuarioCompraUnAsientoNoDisponibleNoSumaMontoAcumulado(){
		try
		{
			usuarioPagoNoVip.comprarUnAsiento(asientoNoDisponible);
		}
		catch (NoSeEncuentraDisponibleElAsientoException e){
			
		}
		Assert.assertTrue(usuarioPagoNoVip.montoAcumulado.equals(new BigDecimal(10)));

	}
	

}
