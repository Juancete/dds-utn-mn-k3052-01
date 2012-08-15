package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.adapter.AerolineaOceanicAdapter;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

import com.lanchita.AerolineaLanchita;
import com.oceanic.AerolineaOceanic;

public class AterrizarApp {

	protected static AterrizarApp instance;
	protected List<Vuelo> vuelosVendidos = new ArrayList<Vuelo>();
	protected Collection<Usuario> usuarios;
	protected List<Aerolinea> aerolineas;
	protected ArrayList<Asiento> reservas = new ArrayList<Asiento>();

	private AterrizarApp() {
		super();
		usuarios = new ArrayList<Usuario>();
		aerolineas = new ArrayList<Aerolinea>();

		AerolineaLanchitaAdapter aerolineaLanchita = new AerolineaLanchitaAdapter(); 
		aerolineaLanchita.setAerolinea(AerolineaLanchita.getInstance());

		AerolineaOceanicAdapter aerolineaOceanic = new AerolineaOceanicAdapter();
		aerolineaOceanic.setAerolinea(AerolineaOceanic.getInstance());
		
		this.agregarAerolinea(aerolineaLanchita);
		this.agregarAerolinea(aerolineaOceanic);		
	
	}

	public static AterrizarApp getInstance() {
		if (instance == null)
			instance = new AterrizarApp();
		return instance;
	}

	public void registarUsuario(String nombre, String apellido, String dni) {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setAPellido(apellido);
		nuevoUsuario.setDNI(dni);
		usuarios.add(nuevoUsuario);
	}

	public void agregarAerolinea(Aerolinea unaAerolinea) {
		aerolineas.add(unaAerolinea);
	}

	public void reservarAsiento(Usuario unUsuario, Asiento unAsiento) {
		try{
			//unAsiento.reservarAsientoPara(unUsuario);
			
			unAsiento.aerolinea.reservarAsiento(unUsuario, unAsiento);
			
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			
		}
	}

	public void agregarUnVueloVendido(Asiento unAsiento){
		if (vuelosVendidos.contains(unAsiento.getVuelo())){
			vuelosVendidos.get(vuelosVendidos.indexOf(unAsiento)).incrementarPopularidad();
		}
		else
		{
			unAsiento.getVuelo().incrementarPopularidad();
			vuelosVendidos.add(unAsiento.getVuelo());
		}
	}

	public void agregarReservaOSobreReserva(Asiento unaReservaOSobreReserva){
		
		if(!this.reservas.contains(unaReservaOSobreReserva)){			
			this.reservas.add(unaReservaOSobreReserva);
		}
	}
	
	public List<Asiento> buscarVuelosPara(Usuario unUsuario, Busqueda unaBusqueda){
		List<Asiento> asientosPedidos = new ArrayList<Asiento>();
		for (Aerolinea unaAerolinea: aerolineas ){
			asientosPedidos.addAll(unUsuario.buscarAsiento(unaBusqueda, unaAerolinea));
		}
		//acá ordeno
		Collections.sort(asientosPedidos,unUsuario.getCriterio());
		return asientosPedidos;
		
	}

	public void quitarReserva(Asiento unAsiento) {	
		this.reservas.remove(unAsiento);
		unAsiento = null;
		
	}
	
	public void reservaExpirada(Asiento unAsiento){
		unAsiento.reservaVencidaAsignarProximaSobreReserva();
		
	};
	
}
