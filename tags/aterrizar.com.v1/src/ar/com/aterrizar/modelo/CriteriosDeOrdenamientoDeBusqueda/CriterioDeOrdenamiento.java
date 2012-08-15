package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import java.util.Comparator;

import ar.com.aterrizar.modelo.Asiento;

public abstract class CriterioDeOrdenamiento implements Comparator<Asiento>{
	boolean ascendente;
	
}
