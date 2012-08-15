package ar.com.aterrizar.modelo;

import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.state.EstadoDisponible;

public class NivelTest {
	
	protected NivelNoPago nivelNoPago;
	protected NivelPago nivelPago;
	protected Usuario usuarioNoVipNoPago;
	protected Usuario usuarioNoVipPago;
	protected Usuario usuarioVip;
	protected List<Asiento> listaDeAsientos;
	protected Asiento unAsiento;
	protected Asiento otroAsiento;
	protected AerolineaLanchitaAdapter unaAerolinea;
	
	@Before
	public void setUp(){
		
		unaAerolinea = mock(AerolineaLanchitaAdapter.class);
		usuarioNoVipNoPago = new Usuario();
		usuarioNoVipNoPago.montoAcumulado = new BigDecimal(0);
		usuarioNoVipNoPago.aumentarMonto(new BigDecimal(200000));
		usuarioVip = new Usuario();
		usuarioVip.montoAcumulado = new BigDecimal(0);
		usuarioVip.aumentarMonto(new BigDecimal(100001));
		usuarioNoVipPago = new Usuario();
		usuarioNoVipPago.montoAcumulado = new BigDecimal(0);
		usuarioNoVipPago.aumentarMonto(new BigDecimal(100));
		nivelNoPago = new NivelNoPago(usuarioNoVipNoPago);
		nivelPago = new NivelPago(usuarioNoVipPago);
		unAsiento = new Asiento("asiento1", new BigDecimal(10000), (char) 0, 'P', new EstadoDisponible(),unaAerolinea);
		otroAsiento = new Asiento("asiento2", new BigDecimal(10), (char) 0, 'E', new EstadoDisponible(), unaAerolinea);
		listaDeAsientos = new ArrayList<Asiento>();
		listaDeAsientos.add(unAsiento);
		listaDeAsientos.add(otroAsiento);

	}
	 
	
	@Test
	public void nivelNoPagoFiltraLasSuperOfertas(){
		List<Asiento> nuevaListaDeAsientos = nivelNoPago.obtenerAsientosListosParaComprar(listaDeAsientos);
		Assert.assertEquals(nuevaListaDeAsientos.size(), 1);
		Assert.assertTrue(nuevaListaDeAsientos.contains(unAsiento));
	}
	
	@Test
	public void nivelPagoUsuarioNoVipFiltraLasSuperOfertas(){
		listaDeAsientos = nivelPago.obtenerAsientosListosParaComprar(listaDeAsientos);
		Assert.assertEquals(listaDeAsientos.size(), 1);
		Assert.assertTrue(listaDeAsientos.contains(unAsiento));
	}
	
	@Test
	public void seLeSumaUnRecargoALosObjetosDeUnNivelNoPago(){
		listaDeAsientos = nivelNoPago.obtenerAsientosListosParaComprar(listaDeAsientos);
		Assert.assertEquals(listaDeAsientos.size(), 1);
		Assert.assertEquals(listaDeAsientos.get(0).precio.intValue(),10020);
	}
	
	@Test
	public void usuarioNoVipPagoSeConvierteAVip(){
		usuarioNoVipPago.aumentarMonto(new BigDecimal(5000000));
		Assert.assertTrue(nivelPago.soyVip());
	}
	
	
	
}
