package ar.com.aterrizar.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class NivelTest {
	
	protected NivelNoPago nivelNoPago;
	protected NivelPago nivelPago;
	protected Usuario usuarioNoVipNoPago;
	protected Usuario usuarioNoVipPago;
	protected Usuario usuarioVip;
	protected List<Asiento> listaDeAsientos;
	protected Asiento unAsiento;
	protected Asiento otroAsiento;
	
	
	@Before
	public void setUp(){
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
		unAsiento = new Asiento(null, new BigDecimal(10000), (char) 0, 'P', true, null);
		otroAsiento = new Asiento(null, new BigDecimal(10), (char) 0, 'E', true, null);
		listaDeAsientos = new ArrayList<Asiento>();
		listaDeAsientos.add(unAsiento);
		listaDeAsientos.add(otroAsiento);

	}
	 
	
	@Test
	public void nivelNoPagoFiltraLasSuperOfertas(){
		listaDeAsientos = nivelNoPago.obtenerAsientosListosParaComprar(listaDeAsientos);
		Assert.assertEquals(listaDeAsientos.size(), 1);
		Assert.assertTrue(listaDeAsientos.contains(unAsiento));
	}
	
	@Test
	public void nivelPagoNoVipFiltraLasSuperOfertas(){
		listaDeAsientos
	}
}
