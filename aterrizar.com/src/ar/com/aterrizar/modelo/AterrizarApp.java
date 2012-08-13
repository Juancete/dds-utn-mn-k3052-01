package ar.com.aterrizar.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;
import ar.com.aterrizar.modelo.adapter.AerolineaOceanicAdapter;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

import com.lanchita.AerolineaLanchita;
import com.oceanic.AerolineaOceanic;

public class AterrizarApp {

	protected static AterrizarApp instance;
	protected Collection<Usuario> usuarios;
	protected List<Aerolinea> aerolineas;
	protected HashMap<Asiento,SobreReserva> sobreReservas;

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
		try {
			for (Aerolinea aerolinea: this.aerolineas){
				aerolinea.reservarAsiento(unUsuario, unAsiento);
			}
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			crearOagregarSobreReserva(unUsuario,unAsiento);
		}
	}

	public void crearOagregarSobreReserva(Usuario unUsuario, Asiento unAsiento) {
		SobreReserva unaSobreReserva = sobreReservas.get(unAsiento);
		if(unaSobreReserva == null){
			unaSobreReserva = new SobreReserva(unAsiento);
		}
		unaSobreReserva.addUsuarioASobreReserva(unUsuario);				
	}	
}
