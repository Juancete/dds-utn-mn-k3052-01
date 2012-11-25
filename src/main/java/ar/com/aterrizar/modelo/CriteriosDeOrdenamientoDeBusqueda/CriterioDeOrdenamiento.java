package ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda;

import java.util.Comparator;

import ar.com.aterrizar.entidades.Asiento;

public abstract class CriterioDeOrdenamiento implements Comparator<Asiento>{
	boolean ascendente;
	
}
