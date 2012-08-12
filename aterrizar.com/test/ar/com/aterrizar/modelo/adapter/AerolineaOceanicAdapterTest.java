package ar.com.aterrizar.modelo.adapter;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.aterrizar.modelo.NivelPago;
import ar.com.aterrizar.modelo.Usuario;

import com.oceanic.AerolineaOceanic;

public class AerolineaOceanicAdapterTest {
	protected static AerolineaOceanicAdapter adapter = new AerolineaOceanicAdapter();
	protected Usuario tony;
	@BeforeClass
	public static void initAdaptertUp() throws Exception {
		adapter.setAerolinea(AerolineaOceanic.getInstance());
	}
	
	@Before
	public void setUp() throws Exception {		
		this.tony = new Usuario();
		tony.setAPellido("stark");
		tony.setDNI("23.323.537");
		tony.setNivel(new NivelPago(tony));
	}

	//@Test
	public void testBuscarAsientosConComision() {
		fail("Not yet implemented");
	}

	//@Test
	public void testComprarAsiento() {
		fail("Not yet implemented");
	}

	@Test
	public void testReservarAsiento() {
		String dni = this.tony.getApellido();
		String codigoDeVuelo = "OC100";
		Integer numeroDeAsiento = new Integer(10);
		adapter.reservarAsiento(dni, codigoDeVuelo, numeroDeAsiento);
		
	}
	
	@Test
	public void noSePuedeReservarAsientoYaReservado() {
		String dni = this.tony.getApellido();
		String codigoDeVuelo = "OC100";
		Integer numeroDeAsiento = new Integer(10);
		adapter.reservarAsiento(dni, codigoDeVuelo, numeroDeAsiento);
		adapter.reservarAsiento(dni, codigoDeVuelo, numeroDeAsiento);
		
	}
	
	
}
