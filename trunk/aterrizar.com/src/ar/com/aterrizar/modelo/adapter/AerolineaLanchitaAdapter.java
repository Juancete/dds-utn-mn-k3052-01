package ar.com.aterrizar.modelo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.modelo.*;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.EstadoErroneoException;

public class AerolineaLanchitaAdapter extends Aerolinea{

	protected Double porcentajePorCompania;
	private AerolineaLanchita aerolinea;

	public AerolineaLanchitaAdapter(){
		
	}

	/*
	 * String[][] com.lanchita.AerolineaLanchita.asientosDisponibles(String
	 * origen, String destino, String fechaSalida, String horaSalida, String
	 * fechaLlegada, String horaLlegada)
	 */
	public List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) {

		String[][] datosAsientos = this.getAerolinea().asientosDisponibles(unaBusqueda.getOrigen(),
				unaBusqueda.getDestino(), unaBusqueda.getFechaSalida(), unaBusqueda.getHoraSalida(), unaBusqueda.getFechaLlegada(), unaBusqueda.getHoraLlegada());
		if (datosAsientos == null)
			return new ArrayList<Asiento>(0);
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		
		for (int posicionEnElVector = 0; posicionEnElVector < datosAsientos.length; posicionEnElVector++) {
			//instanciar un asiento y sumarlo a la nueva lista 
			listaDeAsientos.add(new Asiento(datosAsientos[posicionEnElVector][0], 
											new BigDecimal(datosAsientos[posicionEnElVector][1]).multiply(new BigDecimal(porcentajePorCompania)),
											datosAsientos[posicionEnElVector][3].charAt(0),
											datosAsientos[posicionEnElVector][2].charAt(0),
											((datosAsientos[posicionEnElVector][4].charAt(0) == 'D') ? true : false),
											this));
		}

		return listaDeAsientos;
	}

	
	/**
	 * <h1>Dado un codigo de Asiento compra efectivamente el asiento 
	 */
	public void comprarAsiento(Asiento unAsiento) {
		try
		{
		getAerolinea().comprar(unAsiento.getCodigo());
		}
		catch (EstadoErroneoException e){
			throw new NoSeEncuentraDisponibleElAsientoException();
		}
		
	}

	public void setPorcentaje(double unPorcentaje) {
		this.porcentajePorCompania = unPorcentaje;
		
	}

	public AerolineaLanchita getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaLanchita aerolinea) {
		this.aerolinea = aerolinea;
	}


}
