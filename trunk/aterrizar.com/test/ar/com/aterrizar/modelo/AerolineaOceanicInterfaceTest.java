package ar.com.aterrizar.modelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oceanic.AerolineaOceanic;

public class AerolineaOceanicInterfaceTest {
	
	protected static AerolineaOceanic aerolinea;
	protected Usuario tony;
	
	@BeforeClass
	public static void initClass() throws Exception {
		aerolinea = AerolineaOceanic.getInstance();
	}
	
	@Before
	public void setUp() throws Exception {		
		this.tony = new Usuario();
		tony.setAPellido("stark");
		tony.setDNI("23.323.537");
		tony.setNivel(new NivelPago(tony));
	}
	
	@Test
	public void reservarAsientoTest() {
		String dni = this.tony.getApellido();
		String codigoDeVuelo = "OC100";
		Integer numeroDeAsiento = new Integer(10);
		//primero debo verificar que el asiento a reservar este disponible
		
		Assert.assertFalse(aerolinea.estaReservado(codigoDeVuelo, numeroDeAsiento).booleanValue());
		
		Assert.assertTrue(aerolinea.reservar(dni, codigoDeVuelo, numeroDeAsiento).booleanValue());
		Assert.assertFalse(aerolinea.reservar(dni, codigoDeVuelo, numeroDeAsiento).booleanValue());
		
		}


}