package ar.com.aterrizar.modelo;

import java.util.List;
import java.math.*;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;

public class Usuario {

	protected String nombre;
	protected String apellido;
	protected String dni;
	protected Nivel nivelDeUsuario;
	protected BigDecimal montoAcumulado;
	protected List<Busqueda> busquedas;
	
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
	
	List <Asiento> buscarAsiento(Busqueda unaBusqueda,AerolineaLanchitaAdapter unaAerolinea){
		busquedas.add(unaBusqueda);
		List <Asiento> unaListaDeAsientos = unaAerolinea.buscarAsientosConComision(unaBusqueda);
		return nivelDeUsuario.obtenerAsientosListosParaComprar(unaListaDeAsientos);
	}
	
	void comprarUnAsiento(Asiento unAsiento) throws Exception {
		AerolineaLanchitaAdapter instanciaDeAerolinea = unAsiento.getAerolinea();
		//Si no puede comprar, la excepción se trata mas afuera.
		instanciaDeAerolinea.comprarAsiento(unAsiento);
		aumentarMonto(unAsiento.precio);
	}
	
	void aumentarMonto(BigDecimal unPrecio){
		this.montoAcumulado.add(unPrecio);
	}
	
}
