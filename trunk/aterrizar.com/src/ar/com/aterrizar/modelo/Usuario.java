package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.math.*;

import com.lanchita.excepciones.EstadoErroneoException;

import ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda.CriterioDeOrdenamiento;
import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public class Usuario {

	protected String nombre;
	protected String apellido;
	protected String dni;
	protected Nivel nivelDeUsuario;
	protected BigDecimal montoAcumulado;
	protected List<Busqueda> busquedas;
	private CriterioDeOrdenamiento unCriterio;
	
	
	public Usuario(){
		this.busquedas = new ArrayList<Busqueda>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public List<Busqueda> getBusquedasRealizadas() {
		return busquedas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAPellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}

	public void setNivel(Nivel unNivel) {
		this.nivelDeUsuario = unNivel;
	}
	
	List <Asiento> buscarAsiento(Busqueda unaBusqueda,Aerolinea unaAerolinea){
		busquedas.add(unaBusqueda);
		List <Asiento> unaListaDeAsientos = new ArrayList<Asiento>();
		if (unaBusqueda.getEscalas() == 0){
			unaListaDeAsientos.addAll(unaAerolinea.buscarAsientosConComision(unaBusqueda));
		}
		else
		{
			//TODO:El objeto de charly busca escalas
			return null;
		}
		return unaBusqueda.filtrarAsientos(nivelDeUsuario.obtenerAsientosListosParaComprar(unaListaDeAsientos));
	}
	
	void comprarUnAsiento(Asiento unAsiento) {
		Aerolinea instanciaDeAerolinea = unAsiento.getAerolinea();
		//Si no puede comprar, la excepción se trata mas afuera.
		
		try
		{
			instanciaDeAerolinea.comprarAsiento(unAsiento,this.getDni());
		}
		catch (EstadoErroneoException e){
			//Tiro la exception para arriba  
			throw new NoSeEncuentraDisponibleElAsientoException();
		}
		//No tuvo problemas, entonces advierto a los observers y cambio el monto.
		
		//TODO:realizar llamados a los observers.
		
		aumentarMonto(unAsiento.precio);
	}
	
	void aumentarMonto(BigDecimal unPrecio){
		 this.montoAcumulado=this.montoAcumulado.add(unPrecio);
	}

	public CriterioDeOrdenamiento getCriterio() {
		return unCriterio;
	}

	public void setCriterio(CriterioDeOrdenamiento unCriterio) {
		this.unCriterio = unCriterio;
	}
	

	
}
