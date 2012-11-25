package ar.com.aterrizar.modelo.escalas;

import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Viaje;

public class ExplotadorDeViajes {

	private static ExplotadorDeViajes instance;
	
	private ExplotadorDeViajes(){
		super();
	}
	
	public static ExplotadorDeViajes getInstance(){
		if(instance == null){
			instance = new ExplotadorDeViajes();
		}
		return instance;
	}
	
	public List<Asiento> explotar(List<Viaje> viajes){
		List<Asiento> asientos = new ArrayList<Asiento>();
		for(Viaje viaje : viajes){
			asientos.addAll(viaje.getAsientos());
		}
		return asientos;
	}
}
