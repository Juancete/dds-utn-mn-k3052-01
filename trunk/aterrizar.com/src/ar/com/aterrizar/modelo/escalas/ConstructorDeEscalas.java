package ar.com.aterrizar.modelo.escalas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Viaje;
import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.NoHayAsientosDisponiblesParaUnaBusquedaException;

/**
 * 
 * @author charly ConstructorDeEscalas implementa un patron creacional Buldier
 *         Construye un conjunto de viajes
 * 
 */
public class ConstructorDeEscalas {
	
	private List<Viaje> viajes;
	private Busqueda busquedaDeUsuario;
	private Aerolinea aerolinea;

	public ConstructorDeEscalas(Busqueda busquedaDeUsuario, Aerolinea aerolinea) {
		super();
		this.viajes = new ArrayList<Viaje>();
		this.busquedaDeUsuario = busquedaDeUsuario;
		this.aerolinea = aerolinea;
	}

	public void construirPrimeraEscala() {
		Busqueda primerBusqueda = new Busqueda(this.busquedaDeUsuario.getOrigen(), null, this.busquedaDeUsuario.getFecha());
		List<Asiento> asientosIniciales = this.aerolinea.buscarAsientosConComision(primerBusqueda);
		for(Asiento asiento : asientosIniciales){
			Viaje viaje = new Viaje();
			viaje.addAsientoAlFinal(asiento);
			asiento.setViaje(viaje);
			this.viajes.add(viaje);
		}
	}

	public void construirTodasLasEscalasPosibles() {
		// TODO crea todas las escalas posibles para las escalas de la lista y
		// esto lo realiza numeroDeEscalas veces
		// ESTO SE IMPLEMENTARIA PARA UN POSIBLE REQUERIMIENTO DE CONSTRUIR MAS
		// DE DOS ESCALAS
		// NO REALIZAR PARA LA ENTREGA 3 PERO SI USARLO
		Integer numeroDeEscalasIntermedias = busquedaDeUsuario.getEscalas() - 1;
		if (numeroDeEscalasIntermedias < 1){
			return;
		}
		else
		{
			throw new NoSePudenConstruirMasDeDosEscalasExeption();
		}
	}

	public void construirEscalaFinal() {
		//crear todas las combinaciones de destino para todas los viajes
		for(Viaje viaje : this.viajes){
			List<Asiento> asientosFinales;
			ArrayList<Asiento> asientosSinModificar = new ArrayList<Asiento>(viaje.getAsientos());
			Asiento ultimoAsietno = viaje.getUltimoAsiento();
			Busqueda busquedaFinal = new Busqueda(ultimoAsietno.getVuelo().getDestino(), this.busquedaDeUsuario.getDestino(), this.busquedaDeUsuario.getFecha());
			try{
				asientosFinales = this.aerolinea.buscarAsientosConComision(busquedaFinal);
			}
			catch (NoHayAsientosDisponiblesParaUnaBusquedaException e)
			{
				continue;
			}
			Iterator<Asiento> iteartor = asientosFinales.iterator();
			viaje.addAsientoAlFinal(iteartor.next());
			while(iteartor.hasNext())
			{
				Viaje nuevoViaje = new Viaje(asientosSinModificar);
				nuevoViaje.addAsientoAlFinal(iteartor.next());
				this.viajes.add(nuevoViaje);
			}
		}
	}

	public List<Asiento> construir() {
		// TODO filtrar los viajes que no llegan a destino o no cumplen la regla de llegar antes de que salga el proximo vuelo
		List<Viaje> viajesFiltrados = new ArrayList<Viaje>();
		for(Viaje viaje : this.viajes){
			try{
				CriterioDeAceptación.getInstance().esAceptado(viaje, this.busquedaDeUsuario.getDestino());
				viajesFiltrados.add(viaje);
			}
			catch (NoCumpleCriterioDeEscalaExeption e){
				
			}
		}
		
		return ExplotadorDeViajes.getInstance().explotar(viajesFiltrados);
	}
}
