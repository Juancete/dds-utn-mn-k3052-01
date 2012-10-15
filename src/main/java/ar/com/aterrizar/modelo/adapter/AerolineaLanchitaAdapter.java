package ar.com.aterrizar.modelo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.Vuelo;
import ar.com.aterrizar.modelo.state.Estado;
import ar.com.aterrizar.modelo.state.EstadoComprado;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;
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
				Asiento unAsiento = new Asiento(datosAsientos[posicionEnElVector][0], 
						new BigDecimal(datosAsientos[posicionEnElVector][1]).multiply(new BigDecimal(porcentajePorCompania)),
						datosAsientos[posicionEnElVector][3].charAt(0),
						datosAsientos[posicionEnElVector][2].charAt(0),
						getEstado(datosAsientos[posicionEnElVector][4].charAt(0)),
						this);
				unAsiento.setVuelo(new Vuelo(datosAsientos[posicionEnElVector][0], 
						datosAsientos[posicionEnElVector][9], 
						datosAsientos[posicionEnElVector][8], 
						new Fecha(datosAsientos[posicionEnElVector][10],new FormatoSimple("dd/MM/yyyy")),
						new Fecha(datosAsientos[posicionEnElVector][11],new FormatoSimple("dd/MM/yyyy"))));
				
				listaDeAsientos.add(unAsiento);
			}
		}
		return listaDeAsientos;
	}
	
	public List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) {

		String[][] datosAsientos = this.getAerolinea().asientos(unaBusqueda.getOrigen(),
				unaBusqueda.getDestino(),this.fechaToAAAAMMDD(unaBusqueda.getFecha()) , null, null, null);
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		listaDeAsientos = convertirArrayDeStringEnListaDeAsientos(datosAsientos);
		
//		datosAsientos = this.getAerolinea().asientos(unaBusqueda.getOrigen(),
//				unaBusqueda.getDestino(), null, null,Integer.toString(unaBusqueda.getFecha().obtenerAnio()).concat(Integer.toString(unaBusqueda.getFecha().obtenerMes())).concat(Integer.toString(unaBusqueda.getFecha().obtenerDia())), null);
//		
//		listaDeAsientos.addAll(convertirArrayDeStringEnListaDeAsientos(datosAsientos));
		
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
			throw new NoSeEncuentraDisponibleElAsientoException("La aerolinea no permite comprar este asiento. Se encuentra reservado o comprado.");
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
//		try{
//			aerolinea.reservar(unAsiento.codigo, unUsuario.getDni());
//		}catch (EstadoErroneoException e){//Exception que lanza lanchita
//			//Asiento de AerolineaLanchita ya reservado
//			throw new NoSeEncuentraDisponibleElAsientoException("Asiento no disponible para reservar en Lanchita");
//		}
		
	}
	
	private Estado getEstado(char estadoAsiento){
	 if(estadoAsiento == 'R'){
		 return new EstadoReservado();
	 }
	 if(estadoAsiento == 'C'){
		 return new EstadoComprado();		 
	 }
	 return new EstadoDisponible();
		
	}

	@Override
	public String getNombre() {
		return "Lanchita";
	}
}