package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.List;

public class Viaje {

	private ArrayList<Asiento> asientos;
	
	public Viaje(){
		super();
		asientos = new ArrayList<Asiento>();
	}
	
	public long getTiempo(Asiento asiento) {
		try{
			Asiento siguienteAsiento = asientos.get(asientos.indexOf(asiento) +1);
			//TODO: comparar la fecha y hora de asiento con siguienteAsiento
		}
		catch (IndexOutOfBoundsException e)
		{
			//era el último, por lo tanto retorna el tiempo del asiento y ya
			return asiento.getTiempoEnElAire();
		}
		return 1;
	}

}
