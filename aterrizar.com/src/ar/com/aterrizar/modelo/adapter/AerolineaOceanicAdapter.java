package ar.com.aterrizar.modelo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		String fechaSalida = this.evaluarFechaYRetornarCorrecto(unaBusqueda.getFechaSalida());
		if(destino == null){
			 datosAsientos = this.getAerolinea().asientosDisponiblesParaOrigen(origen, unaBusqueda.getFecha());
			}else{
				datosAsientos = this.getAerolinea().asientosDisponiblesParaOrigenYDestino(origen, destino, unaBusqueda.getFecha());
		}
		
		if(datosAsientos.isEmpty()){
			throw new NoHayAsientosDisponiblesParaUnaBusquedaException();
		}
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		
		for(AsientoDTO unAsientoDTO : datosAsientos){
			char clase = unAsientoDTO.getClase().charAt(0);
			if(clase == 'T') clase = 'C';
			//notar que codigo de Vuelo del asiento de aterrizar.com es distinto al codigo de vuelo de Oceanic	
			listaDeAsientos.add(new Asiento(unAsientoDTO.getCodigoDeVuelo().concat(Integer.toString(unAsientoDTO.getNumeroDeAsiento())),
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
	
	private String evaluarFechaYRetornarCorrecto(String fechaSalida) {
		//formato correcto("dd/MM/AAAA")
		int fechaInteger = Integer.parseInt(fechaSalida);
		int anio = fechaInteger/10000;
		int mes = (fechaInteger%10000)/100;
		String mesString  = String.format("%02d",mes);
		int dia = (fechaInteger%10000)%100;
		//dia = String.format(,dia);
		String fechaString = Integer.toString(dia).concat("/").concat(mesString).concat("/").concat(Integer.toString(anio));
		return fechaString;
	}

	@Override
	public void comprarAsiento(Asiento unAsiento,String dni)
			throws NoSeEncuentraDisponibleElAsientoException {
		//TODO modificar los metodos para que reciban el dni de la persona q compra
		String codigoAterrizar = unAsiento.getCodigo();
		Boolean compraExitosa =  this.getAerolinea().comprarSiHayDisponibilidad(dni, 
				codigoAterrizar.substring(0, codigoAterrizar.length()) , 
				Integer.parseInt(codigoAterrizar.substring(codigoAterrizar.length() - 1)));
		if(!compraExitosa){
			throw new NoSeEncuentraDisponibleElAsientoException();
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
				throw new AsientoNoDisponibleException("El asiento no se pudo reservar");
			}
		}
	
	
	public AerolineaOceanic getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaOceanic aerolinea) {
		this.aerolinea = aerolinea;
	}
}