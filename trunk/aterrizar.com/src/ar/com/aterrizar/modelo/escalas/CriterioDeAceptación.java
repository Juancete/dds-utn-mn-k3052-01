package ar.com.aterrizar.modelo.escalas;

import java.util.Iterator;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Viaje;

public class CriterioDeAceptación {

	private static CriterioDeAceptación instance;
	
	private CriterioDeAceptación(){
		super();
	}
	public static CriterioDeAceptación getInstance(){
		if(instance == null){
			instance = new CriterioDeAceptación();
		}
		return instance;
	}
	
	public void esAceptado(Viaje viaje, String Destino){
		Iterator<Asiento> iterator = viaje.getAsientos().iterator();
		Asiento asiento = iterator.next();
		while(iterator.hasNext()){
			Asiento asientoSiguiente = iterator.next();
			if(!asiento.llegaAntesDe(asientoSiguiente)){
				throw new NoCumpleCriterioDeEscalaExeption();
			}
			asiento = asientoSiguiente;
		}
		if(!asiento.getVuelo().getDestino().equals(Destino)){
			throw new NoCumpleCriterioDeEscalaExeption();
		}
		return;
	}
}
