package ar.com.aterrizar.modelo.escalas;

import java.util.List;

import ar.com.aterrizar.modelo.Vuelo;
import ar.com.aterrizar.modelo.adapter.Aerolinea;

/**
 * 
 * @author charly
 *	BuscadorDeVuelo funciona como un proxy remoto para las escalas de diferentes aerolineas
 *	Se implementara cuando se usen mas de 2 tramos
 */
public class BuscadorDeEscala {
	List<Vuelo> escalas;
	
	public Vuelo buscarEscala(String origen, String destino, Aerolinea aerolinea){
		for(Vuelo escala : escalas){
			if( true//TODO la escala es la que busco
												){
				return escala;
			}
		}
		//TODO buscar la escala guardarla en la lista y devolverla
		return escalas.get(0);//linea con fin de ejemplo
	}
}
