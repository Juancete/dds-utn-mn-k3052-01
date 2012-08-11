/**UTN FRBA - Tp #1 Manejo de Fechas
 * 
 * Diseño de sistemas - Curso k3052
 * Profesores: fdodino, fscarpa 
 */
package ar.com.aterrizar.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.aterrizar.fecha.excepcion.FechaException;
import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.Formato;
import com.aterrizar.fecha.modelo.FormatoFlexible;
import com.aterrizar.fecha.modelo.FormatoSimple;

public class FechaTest {
	protected String fecha_ISO8601;
	protected String fecha_Latinoamericano;
	protected String fecha_compacta;
	protected Calendar elCalendario = GregorianCalendar.getInstance();
	protected Formato formatoIso8601;
	protected Formato formatoLatinoAmericano;
	protected Formato formatoCompacto;
	protected Fecha hoy;
	protected Fecha ayer;

	@Before
	public void setup() {
		long MILLIS_IN_A_DAY = 1000*60*60*24;
		elCalendario.set(Calendar.YEAR, 2012);
		elCalendario.set(Calendar.MONTH, 5);
		elCalendario.set(Calendar.DAY_OF_MONTH, 13);
		fecha_ISO8601 = "2012-06-13";
		fecha_compacta = "20120613";
		fecha_Latinoamericano = "13/06/2012";
		String ISO8601= "yyyy-MM-dd";
		String LATINO_AMERICANO="dd/MM/yyyy";
		String COMPACTO="yyyyMMdd"; 
		formatoIso8601= new FormatoSimple(ISO8601);
		formatoLatinoAmericano= new FormatoSimple(LATINO_AMERICANO);
		formatoCompacto= new FormatoSimple(COMPACTO);
		
		hoy = new Fecha();
		ayer = new Fecha( new Date(new Date().getTime() - MILLIS_IN_A_DAY));
		
		
	}

	
	@Test
	public void unStringDeFormatoISO8601SeConvierteEnUnaFechaValida() {

		Fecha unaFecha = new Fecha(fecha_ISO8601, formatoIso8601);

		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));
		Assert.assertEquals(unaFecha.obtenerMes(), elCalendario.get(GregorianCalendar.MONTH));
	}

	
	@Test
	public void unStringDeFormatoLatinoamericanoSeConvierteEnUnaFechaValida() {

		Fecha unaFecha = new Fecha(fecha_Latinoamericano,formatoLatinoAmericano);
		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));
		Assert.assertEquals(unaFecha.obtenerMes(), elCalendario.get(GregorianCalendar.MONTH));
	}
	
	
	@Test	
	public void unFormatoFlexibleConIso8601yLatinoamericanoPuedeParsearUnaFechaFormatoLatinoamericano(){
		FormatoFlexible formatoFlexible = new FormatoFlexible();
		formatoFlexible.agregarFormato(formatoIso8601);
		formatoFlexible.agregarFormato(formatoLatinoAmericano);
		
		Fecha unaFecha = new Fecha(fecha_Latinoamericano,formatoFlexible);
		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));
		unaFecha = new Fecha(fecha_ISO8601,formatoFlexible);
		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));
	}	

	
	@Test(expected=FechaException.class)	
	public void unFormatoFlexibleConIso8601yLatinoamericanoNoPuedeParsearUnaFechaFormatoLatinoamericano(){
		FormatoFlexible formatoFlexible = new FormatoFlexible();
		formatoFlexible.agregarFormato(formatoIso8601);

		Fecha unaFecha = new Fecha(fecha_Latinoamericano,formatoFlexible);
		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));		
		unaFecha = new Fecha(fecha_ISO8601,formatoFlexible);
		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));
	}
	
	@Test
	public void unStringDeFormatoCompactoSeConvierteEnUnaFechaValida() {

		Fecha unaFecha = new Fecha(fecha_compacta,formatoCompacto);
		Assert.assertEquals(unaFecha.obtenerDia(), elCalendario.get(GregorianCalendar.DAY_OF_WEEK));
		Assert.assertEquals(unaFecha.obtenerMes(), elCalendario.get(GregorianCalendar.MONTH));
	}


	@Test	
	public void laFechaDeAyerEsAnteriorALaFechaDeHoy(){
		Assert.assertTrue(ayer.esAnteriorA(hoy));
	}

	@Test	
	public void laFechaDeHoyNoEsAnteriorALaDeAyer(){
		Assert.assertTrue(!hoy.esAnteriorA(ayer));
	}
	@Test
	public void entreAyerYHoyHaydiferenciaDe1Dia(){
		Assert.assertEquals(1, hoy.cantidadDeDiasEntre(ayer));
	}
	
	@Test
	public void entreHoyYHoyNoHaydiferenciaDeDia(){
		Assert.assertEquals(0, hoy.cantidadDeDiasEntre(hoy));
	}

}
