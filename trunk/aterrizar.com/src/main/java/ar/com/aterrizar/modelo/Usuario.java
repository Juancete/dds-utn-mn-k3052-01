package ar.com.aterrizar.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.CriteriosDeOrdenamientoDeBusqueda.CriterioDeOrdenamiento;
import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;
import ar.com.aterrizar.modelo.escalas.ConstructorDeEscalas;

import com.lanchita.excepciones.EstadoErroneoException;

public class Usuario {

	protected String nombre;
	protected String apellido;
	protected String dni;
	protected Nivel nivelDeUsuario;
	protected BigDecimal montoAcumulado;
	protected List<Busqueda> busquedas;
	private CriterioDeOrdenamiento unCriterio;
	
	
	public Usuario(){
		this.montoAcumulado = new BigDecimal(0);
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
			ConstructorDeEscalas constructor = new ConstructorDeEscalas(unaBusqueda, unaAerolinea);
			constructor.construirPrimeraEscala();
			constructor.construirTodasLasEscalasPosibles();
			constructor.construirEscalaFinal();
			unaListaDeAsientos.addAll(constructor.construir());
		}
		//return unaBusqueda.filtrarAsientos(nivelDeUsuario.obtenerAsientosListosParaComprar(unaListaDeAsientos));
		return unaListaDeAsientos;
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
		//llamar al observer para que actualice la sobre reserva! le paso el asiento y el observer con el asiento conoce la sobre
		//en la sobre reserva llamara a aterrizarApp.cancelarSobreReservasDe(asiento);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}	
}