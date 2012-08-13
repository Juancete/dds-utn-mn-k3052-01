package ar.com.aterrizar.modelo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.aterrizar.modelo.adapter.AerolineaOceanicAdapter;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;
import com.oceanic.*;
import static org.mockito.Mockito.when;
public class AerolineaOceanicInterfaceTest {
	
	protected static AerolineaOceanic aerolineaOceanic;
	protected Usuario tony;
	protected AerolineaOceanicAdapter aerolineaOceanicAdapter;
	protected Busqueda busquedaBALA20121010;
	protected AerolineaOceanic aerolineaOceanicMock;
	protected List<AsientoDTO> retornoImpostor;
	
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
		retornoImpostor.add(new AsientoDTO("OC100",10,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"_BS","SLA"));
		retornoImpostor.add(new AsientoDTO("OC100",11,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Centro",false,"_BS","SLA"));		
		retornoImpostor.add(new AsientoDTO("OC100",12,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Ventana",false,"_BS","SLA"));		
		retornoImpostor.add(new AsientoDTO("OC100",30,"10/10/2012","10/10/2012","10:35","05:35",new BigDecimal("6150.98"),"Primera","Pasillo",true,"_BS","SLA"));		
		
		aerolineaOceanicMock = mock(AerolineaOceanic.class);
		
		when(aerolineaOceanicMock.asientosDisponiblesParaOrigenYDestino("_BS", "SLA", "10/10/2012")).thenAnswer(new Answer<List<AsientoDTO>>() {
																							public List<AsientoDTO> answer(InvocationOnMock invocation) throws Throwable{
																								return (List<AsientoDTO>) retornoImpostor;
																							}
																				});
		
		when(aerolineaOceanicMock.reservar("23.323.537","0C100",10)).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				return (Boolean) true;
			}
								});
		
		aerolineaOceanicAdapter = new AerolineaOceanicAdapter();
		aerolineaOceanicAdapter.setAerolinea(aerolineaOceanicMock);
		aerolineaOceanicAdapter.setPorcentajePorCompania(1.2);
		busquedaBALA20121010= new Busqueda("BS", "LA", new Fecha("20121010",new FormatoSimple("yyyyMMdd")));

		
	}
	
	@Test
	public void losAsientosSonGeneradosCorrectamente(){//0C100
		final List<Asiento> asientosMock = new ArrayList<Asiento>();
		asientosMock.add(new Asiento("OC10010", (new BigDecimal("3150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'P', 'E', true , aerolineaOceanicAdapter));
		asientosMock.add(new Asiento("OC10011", (new BigDecimal("3150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'C', 'E', true , aerolineaOceanicAdapter));
		asientosMock.add(new Asiento("OC10012", (new BigDecimal("3150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'V', 'E', true , aerolineaOceanicAdapter));
		asientosMock.add(new Asiento("OC10030", (new BigDecimal("6150.98")).multiply(new BigDecimal(aerolineaOceanicAdapter.getPorcentajePorCompania())), 'P', 'P', false , aerolineaOceanicAdapter));
		List<Asiento> asientosDisponibles = aerolineaOceanicAdapter.buscarAsientosConComision(busquedaBALA20121010);
		Assert.assertTrue(asientosDisponibles.containsAll(asientosMock));
	}	
	
	
	
	@Test
	public void fechaCorrecta(){
		System.out.println(aerolineaOceanicAdapter.parsearFecha(busquedaBALA20121010.getFecha()));
	}
	
	@Test
	public void reservarAsientoTest() {
		Asiento unAsiento = new Asiento("1000",new BigDecimal(11),'E','V',true,aerolineaOceanicAdapter);
		Vuelo unVuelo = new Vuelo("0C100","_BS","SLA",null,null);
		unAsiento.setVuelo(unVuelo);
		unAsiento.setNumeroDeAsiento(new Integer(10));
		
		aerolineaOceanicAdapter.reservarAsiento(tony, unAsiento);
		Assert.assertFalse(unAsiento.isDisponible());
		}


}