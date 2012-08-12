package ar.com.aterrizar.modelo;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AterrizarAppTest {

	protected static AterrizarApp aterrizar;
    protected Usuario usuarioStandard;
    protected Nivel nivel;
	
	@BeforeClass
	public void init() throws Exception {
		aterrizar= AterrizarApp.getInstance();
	}
	
	@Before
	public void setUp()throws Exception{
		usuarioStandard = new Usuario();
		nivel = new NivelPago(usuarioStandard);
		
	}
	//@Test
	public void testRegistarUsuario() {
		fail("Not yet implemented");
	}

	//@Test
	public void testAgregarAerolinea() {
		fail("Not yet implemented");
	}

	@Test
	public void testReservarAsiento() {
		fail("Not yet implemented");
	}

}
