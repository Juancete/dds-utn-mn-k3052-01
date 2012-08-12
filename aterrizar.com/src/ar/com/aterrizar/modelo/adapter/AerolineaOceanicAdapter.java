package ar.com.aterrizar.modelo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.aterrizar.fecha.modelo.Fecha;
import com.oceanic.*;


import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;

public class AerolineaOceanicAdapter extends Aerolinea {
	protected AerolineaOceanic aerolinea;

	
	@Override
	public List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) {
		
		List<AsientoDTO> datosAsientos = new ArrayList<AsientoDTO>();
		String destino = this.evaluarStringYRetornarCorrecto(unaBusqueda.getDestino());
		String origen = this.evaluarStringYRetornarCorrecto(unaBusqueda.getOrigen());
		String fecha = this.evaluarFechaYRetornarCorrecto(unaBusqueda.getFecha());
		if(destino == null){
			 datosAsientos = this.getAerolinea().asientosDisponiblesParaOrigen(origen, fecha);
			}else{
				datosAsientos = this.getAerolinea().asientosDisponiblesParaOrigenYDestino(origen, destino,fecha);
		}
		
		if(datosAsientos.isEmpty()){
			throw new NoHayAsientosDisponiblesParaUnaBusquedaException();
		}
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		
		for(AsientoDTO unAsientoDTO : datosAsientos){
			char clase = unAsientoDTO.getClase().charAt(0);
			if(clase == 'T') clase = 'C';
			listaDeAsientos.add(new Asiento(Integer.toString(unAsientoDTO.getNumeroDeAsiento()),
					unAsientoDTO.getPrecio().multiply(new BigDecimal(porcentajePorCompania)),
					unAsientoDTO.getUbicacion().charAt(0),
					clase,
					!unAsientoDTO.getReservado(),
					this));
		}
		
		return listaDeAsientos;
	}
	
	private String evaluarStringYRetornarCorrecto(String lugar){
		if(lugar != null && lugar.length() == 2){
			if(lugar.contentEquals("LA")) {
				lugar = new String("SLA");
				}else lugar = new String(new String("_")).concat(lugar);
		}
		return lugar;
	}
	
	private String evaluarFechaYRetornarCorrecto(Fecha fecha) {
		//formato correcto("dd/MM/AAAA")
		String fechaString = Integer.toString(fecha.obtenerDia()).concat("/").concat(Integer.toString(fecha.obtenerMes())).concat("/").concat(Integer.toString(fecha.obtenerAnio()));
		return fechaString;
	}

	@Override
	public void comprarAsiento(Asiento unAsiento,String dni)
			throws NoSeEncuentraDisponibleElAsientoException {
		String codigoAterrizar = unAsiento.getCodigo();//quitar esto
		Boolean compraExitosa =  this.getAerolinea().comprarSiHayDisponibilidad(dni, 
				codigoAterrizar.substring(0, codigoAterrizar.length()) , //codigo vuelo
				Integer.parseInt(unAsiento.getCodigo()));
		if(!compraExitosa){
			throw new NoSeEncuentraDisponibleElAsientoException("El asiento no se pudo comprar");
		}
	}
	
	/**
	 * @author Nacho
	 * @param dni
	 * @param codigoDeVuelo
	 * @param numeroDeAsiento
	 */
	public void reservarAsiento(String dni, String codigoDeVuelo, Integer numeroDeAsiento){
		if(!(aerolinea.estaReservado(codigoDeVuelo, numeroDeAsiento)).booleanValue()){
			if(!aerolinea.reservar(dni, codigoDeVuelo, numeroDeAsiento).booleanValue())
				throw new NoSeEncuentraDisponibleElAsientoException("El asiento no se pudo reservar");
			}
		}
	
	
	public AerolineaOceanic getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaOceanic aerolinea) {
		this.aerolinea = aerolinea;
	}
}