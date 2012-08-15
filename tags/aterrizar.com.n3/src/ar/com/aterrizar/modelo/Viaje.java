package ar.com.aterrizar.modelo;

import java.util.ArrayList;

public class Viaje {

	private ArrayList<Asiento> asientos;
	
	public Viaje(){
		super();
		asientos = new ArrayList<Asiento>();
	}
	
	public Viaje(ArrayList<Asiento> asientosIniciales){
		super();
		this.asientos = new ArrayList<Asiento>(asientosIniciales);
	}
	
	public long getTiempo(Asiento asiento) {
		try{
			Asiento siguienteAsiento = asientos.get(asientos.indexOf(asiento) +1);
			//TODO: comparar la fecha y hora de asiento con siguienteAsiento
		}
		catch (IndexOutOfBoundsException e)
		{
			//era el �ltimo, por lo tanto retorna el tiempo del asiento y ya
			return asiento.getTiempoEnElAire();
		}
		return 1;
	}

	public void addAsientoAlFinal(Asiento asiento){
		asientos.add(asiento);
	}
	
	public Asiento getUltimoAsiento(){
		return this.asientos.get(this.asientos.size() - 1);
	}
	
	public ArrayList<Asiento> getAsientos(){
		return this.asientos;
	}
}
