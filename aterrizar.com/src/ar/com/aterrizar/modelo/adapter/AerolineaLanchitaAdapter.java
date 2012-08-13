package ar.com.aterrizar.modelo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.modelo.*;

import com.lanchita.AerolineaLanchita;
import com.lanchita.excepciones.EstadoErroneoException;

public class AerolineaLanchitaAdapter extends Aerolinea{

	protected AerolineaLanchita aerolinea;
	
	/*
	 * String[][] com.lanchita.AerolineaLanchita.asientosDisponibles(String
	 * origen, String destino, String fechaSalida, String horaSalida, String
	 * fechaLlegada, String horaLlegada)
	 */
	
	private List<Asiento> convertirArrayDeStringEnListaDeAsientos(String[][] datosAsientos){
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		if (datosAsientos != null)
		{
			for (int posicionEnElVector = 0; posicionEnElVector < datosAsientos.length; posicionEnElVector++) {
				//instanciar un asiento y sumarlo a la nueva lista 
				listaDeAsientos.add(new Asiento(datosAsientos[posicionEnElVector][0], 
												new BigDecimal(datosAsientos[posicionEnElVector][1]).multiply(new BigDecimal(porcentajePorCompania)),
												datosAsientos[posicionEnElVector][3].charAt(0),
												datosAsientos[posicionEnElVector][2].charAt(0),
												((datosAsientos[posicionEnElVector][4].charAt(0) == 'D') ? true : false),
												this));
			}
		}
		return listaDeAsientos;
	}
	
	public List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) {

		String[][] datosAsientos = this.getAerolinea().asientosDisponibles(unaBusqueda.getOrigen(),
				unaBusqueda.getDestino(), Integer.toString(unaBusqueda.getFecha().obtenerAnio()).concat(Integer.toString(unaBusqueda.getFecha().obtenerMes())).concat(Integer.toString(unaBusqueda.getFecha().obtenerDia())), null, null, null);
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		listaDeAsientos = convertirArrayDeStringEnListaDeAsientos(datosAsientos);
		
		datosAsientos = this.getAerolinea().asientosDisponibles(unaBusqueda.getOrigen(),
				unaBusqueda.getDestino(), null, null,Integer.toString(unaBusqueda.getFecha().obtenerAnio()).concat(Integer.toString(unaBusqueda.getFecha().obtenerMes())).concat(Integer.toString(unaBusqueda.getFecha().obtenerDia())), null);
		
		listaDeAsientos.addAll(convertirArrayDeStringEnListaDeAsientos(datosAsientos));
		
		if (listaDeAsientos.isEmpty())
			throw new NoHayAsientosDisponiblesParaUnaBusquedaException();	
		
		return listaDeAsientos;
	}

	
	/**
	 * <h1>Dado un codigo de Asiento compra efectivamente el asiento 
	 */
	public void comprarAsiento(Asiento unAsiento,String dni) {
		try
		{
		getAerolinea().comprar(unAsiento.getCodigo());
		}
		catch (EstadoErroneoException e){
			throw new NoSeEncuentraDisponibleElAsientoException();
		}
		
	}

	public AerolineaLanchita getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaLanchita aerolinea) {
		this.aerolinea = aerolinea;
	}

	@Override
	public void reservarAsiento(Usuario unUsuario, Asiento unAsiento) {
		// TODO Auto-generated method stub
		
	}

}
