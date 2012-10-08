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
import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

public class AerolineaOceanicAdapter extends Aerolinea {
	protected AerolineaOceanic aerolinea;
	
	@Override
	public List<Asiento> buscarAsientosConComision(Busqueda unaBusqueda) {
		
		List<AsientoDTO> datosAsientos = new ArrayList<AsientoDTO>();
		String destino = this.evaluarStringYRetornarCorrecto(unaBusqueda.getDestino());
		String origen = this.evaluarStringYRetornarCorrecto(unaBusqueda.getOrigen());
		if(destino == null){
			 datosAsientos = this.getAerolinea().asientosDisponiblesParaOrigen(origen, this.parsearFecha(unaBusqueda.getFecha()));
			}else{
				datosAsientos = this.getAerolinea().asientosDisponiblesParaOrigenYDestino(origen, destino,this.parsearFecha(unaBusqueda.getFecha()));
		}
		
		if(datosAsientos.isEmpty()){
			throw new NoHayAsientosDisponiblesParaUnaBusquedaException();
		}
		
		List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		for(AsientoDTO unAsientoDTO : datosAsientos){
			char clase = unAsientoDTO.getClase().charAt(0);
			if(clase == 'T') clase = 'C';
			//codigo de asiento dado por su codigo de vuelo y numero de asiento concatenados
			Asiento unAsiento = new Asiento(unAsientoDTO.getCodigoDeVuelo().concat(Integer.toString(unAsientoDTO.getNumeroDeAsiento())),
					unAsientoDTO.getPrecio().multiply(new BigDecimal(porcentajePorCompania)),
					unAsientoDTO.getUbicacion().charAt(0),
					clase,
					getEstado(unAsientoDTO.getReservado()),
					this);
			unAsiento.setNumeroDeAsiento(unAsientoDTO.getNumeroDeAsiento());
			boolean elVueloEstaEnLaLista = false;
			for(Vuelo unVuelo:vuelos){
			   
				if(unVuelo.getCodigo().equals(unAsientoDTO.getCodigoDeVuelo())){
					unAsiento.setVuelo(unVuelo);
				}
			}
			
			if(!elVueloEstaEnLaLista){
				Fecha fechaOrigen = new Fecha(unAsientoDTO.getFechaDeSalida(),new FormatoSimple("dd/MM/yyyy"));
				Fecha fechaDestino = new Fecha(unAsientoDTO.getFechaDeLlegada(),new FormatoSimple("dd/MM/yyyy"));
				fechaOrigen = new Fecha(this.fechaToAAAAMMDD(fechaOrigen),new FormatoSimple("yyyyMMdd"));
				fechaDestino = new Fecha(this.fechaToAAAAMMDD(fechaDestino),new FormatoSimple("yyyyMMdd"));
				Vuelo nuevoVuelo = new Vuelo(unAsientoDTO.getCodigoDeVuelo(),
						unAsientoDTO.getOrigen(),unAsientoDTO.getDestino(),
						fechaOrigen,fechaDestino);
				vuelos.add(nuevoVuelo);
				unAsiento.setVuelo(nuevoVuelo);
			}
			
			listaDeAsientos.add(unAsiento);
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
	
	private String parsearFecha(Fecha fecha) {
		//formato correcto("dd/MM/AAAA")
		String fechaString = Integer.toString(fecha.obtenerDia()).concat("/").concat(Integer.toString(fecha.obtenerMes()+1)).concat("/").concat(Integer.toString(fecha.obtenerAnio()));
		return fechaString;
	}

	@Override
	public void comprarAsiento(Asiento unAsiento,String dni)
			throws NoSeEncuentraDisponibleElAsientoException {
		Boolean compraExitosa =  this.getAerolinea().comprarSiHayDisponibilidad(dni, 
				unAsiento.getVuelo().getCodigo() , //codigo vuelo
				unAsiento.getNumeroDeAsiento());
		if(!compraExitosa){
			throw new NoSeEncuentraDisponibleElAsientoException("El asiento no se pudo comprar");
		}
		unAsiento.setEstado(new EstadoComprado());
		//unAsiento.setDisponibilidad(false);
	}
	
	@Override
	public void reservarAsiento(Usuario unUsuario, Asiento unAsiento) {
		if(!this.getAerolinea().reservar(unUsuario.getDni(),unAsiento.getVuelo().getCodigo(), unAsiento.numeroDeAsiento)){
			throw new NoSeEncuentraDisponibleElAsientoException("El asiento no pudo ser reservado");		
		}
		unAsiento.setEstado(new EstadoReservado());
		//unAsiento.setDisponibilidad(false);
	}

	
	public AerolineaOceanic getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaOceanic aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	private Estado getEstado(boolean reservado){
		 if(reservado){
			 return new EstadoReservado();
		 }		 
		 return new EstadoDisponible();			
	}


	@Override
	public String getNombre() {
		return "Oceanic";
	}	
}