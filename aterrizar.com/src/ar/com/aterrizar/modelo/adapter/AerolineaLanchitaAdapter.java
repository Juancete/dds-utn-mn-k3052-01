package ar.com.aterrizar.modelo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.modelo.Aerolinea;
import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.EstadoErroneoException;

public class AerolineaLanchitaAdapter extends Aerolinea{

	protected Integer porcentajePorCompania;
	protected AerolineaLanchita aerolinea;

	public AerolineaLanchitaAdapter(){
		
	}

	/*
	 * String[][] com.lanchita.AerolineaLanchita.asientosDisponibles(String
	 * origen, String destino, String fechaSalida, String horaSalida, String
	 * fechaLlegada, String horaLlegada)
	 */
	public List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) {

		//TODO falta implementar la logica, agregar en asiento el parser para la construccion de asciento a partir de una lista de strings
		//		String origen = unaBusqueda.getOrigen();


		String[][] datosAsientos = this.aerolinea.asientosDisponibles(unaBusqueda.getOrigen(),
				unaBusqueda.getDestino(), unaBusqueda.getFechaSalida(), unaBusqueda.getHoraSalida(), unaBusqueda.getFechaLlegada(), unaBusqueda.getHoraLlegada());
		if (datosAsientos == null)
			return new ArrayList<Asiento>(0);
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		
		for (int posicionEnElVector = 0; posicionEnElVector < datosAsientos.length; posicionEnElVector++) {
			//System.out.println(datosAsientos[posicionEnElVector][indexy]);
			//instanciar un asiento y sumarlo a la nueva lista 
			listaDeAsientos.add(new Asiento(datosAsientos[posicionEnElVector][0].charAt(0), 
											new BigDecimal(datosAsientos[posicionEnElVector][1]),
											datosAsientos[posicionEnElVector][3].charAt(0),
											datosAsientos[posicionEnElVector][2].charAt(0),
											((datosAsientos[posicionEnElVector][4].charAt(0) == 'D') ? true : false),
											this);
		}

		return listaDeAsientos;
	}

	
	/**
	 * <h1>Dado un codigo de Asiento compra efectivamente el asiento 
	 */
	public void comprarAsiento(Asiento unAsiento) {
		// TODO Implementar el codigo de compra de asiento
		try
		{
		aerolinea.comprar(unAsiento.getCodigo());
		}
		catch (EstadoErroneoException e){
			throw new NoSeEncuentraDisponibleElAsientoException();
		}
		
	}


}
