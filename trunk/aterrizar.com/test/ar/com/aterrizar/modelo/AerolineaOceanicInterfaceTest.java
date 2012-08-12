package ar.com.aterrizar.modelo;

import java.awt.List;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.aterrizar.modelo.adapter.AerolineaOceanicAdapter;

import com.oceanic.*;

public class AerolineaOceanicInterfaceTest {
	
	protected static AerolineaOceanic aerolineaOceanic;
	protected Usuario tony;
	protected AerolineaOceanicAdapter aerolineaOceanicAdapter;
	protected Busqueda busquedaBALA20120815;
	protected AerolineaOceanic aerolineaOceanicMock;
	protected ArrayList<AsientoDTO> retornoImpostor;
	
	@BeforeClass
	public static void initClass() throws Exception {
		aerolineaOceanic = AerolineaOceanic.getInstance();
	}
	
	@Before
	public void setUp() throws Exception {		
		this.tony = new Usuario();
		tony.setAPellido("stark");
		tony.setDNI("23.323.537");
		tony.setNivel(new NivelPago(tony));
		
		retornoImpostor = new ArrayList<AsientoDTO>();
		retornoImpostor.add(new AsientoDTO("OC100",10,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"_BS","SLA"));
		retornoImpostor.add(new AsientoDTO("OC100",11,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Centro",false,"_BS","SLA"));		
		retornoImpostor.add(new AsientoDTO("OC100",12,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Ventana",false,"_BS","SLA"));		
		retornoImpostor.add(new AsientoDTO("OC100",30,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("6150.98"),"Primera","Pasillo",true,"_BS","SLA"));		
		
		aerolineaOceanicMock = mock(AerolineaOceanic.class);
		
		when(aerolineaOceanicMock.asientosDisponiblesParaOrigenYDestino("BS", "LA", "20121010")).thenAnswer(new Answer<List<AsientoDTO>>() {
																							public List<AsientoDTO> answer(InvocationOnMock invocation) throws Throwable{
																								return (List<AsientoDTO>) retornoImpostor;
																							}
																				});
		 
		aerolineaOceanicAdapter = new AerolineaOceanicAdapter();
		aerolineaOceanicAdapter.setAerolinea(aerolineaOceanicMock);
		aerolineaOceanicAdapter.setPorcentajePorCompania(1.2);
		busquedaBALA20120815= new Busqueda("BS", "LA","20120815", null, null, null);

		
	}
	
	@Test
	public void losAsientosSonGeneradosCorrectamente(){
		final List<Asiento> asientosMock = new ArrayList<Asiento>();
		asientosMock.add(new Asiento("OC10010", (new BigDecimal("3150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'P', 'E', true , aerolineaOceanicAdapter));
		asientosMock.add(new Asiento("OC10011", (new BigDecimal("3150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'C', 'E', true , aerolineaOceanicAdapter));
		asientosMock.add(new Asiento("OC10012", (new BigDecimal("3150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'V', 'E', true , aerolineaOceanicAdapter));
		asientosMock.add(new Asiento("OC10030", (new BigDecimal("6150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'P', 'P', false , aerolineaOceanicAdapter));
		Assert.assertTrue(aerolineaOceanicAdapter.buscarAsientosConComision(busquedaBALA20120815).containsAll(asientosMock));
	}
	
	@Test
	public void fechacorrecta(){
	int fechaInteger = Integer.parseInt("20120815");
	int anio = fechaInteger/10000;
	int mes = (fechaInteger%10000)/100;
	int dia = (fechaInteger%10000)%100;
	
	String fechaString = Integer.toString(dia).concat("/").concat(Integer.toString(mes)).concat("/").concat(Integer.toString(anio));
	System.out.println(fechaString);
	System.out.print(00+mes);
	}
	
	@Test
	public void reservarAsientoTest() {
		String dni = this.tony.getApellido();
		String codigoDeVuelo = "OC100";
		Integer numeroDeAsiento = new Integer(10);
		//primero debo verificar que el asiento a reservar este disponible
		
		Assert.assertFalse(aerolineaOceanic.estaReservado(codigoDeVuelo, numeroDeAsiento).booleanValue());
		
		Assert.assertTrue(aerolineaOceanic.reservar(dni, codigoDeVuelo, numeroDeAsiento).booleanValue());
		Assert.assertFalse(aerolineaOceanic.reservar(dni, codigoDeVuelo, numeroDeAsiento).booleanValue());
		
		}


}