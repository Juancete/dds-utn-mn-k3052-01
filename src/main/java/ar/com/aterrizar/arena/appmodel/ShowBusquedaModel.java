package ar.com.aterrizar.arena.appmodel;

import java.text.SimpleDateFormat;

import org.uqbar.commons.model.Home;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Vuelo;

public class ShowBusquedaModel extends ShowModel<Busqueda> {

	Busqueda selected;
	
	public ShowBusquedaModel(Home<Busqueda> home) {
		super(home);
	}
	
	public static Busqueda AsientoToBuqueda(Asiento asiento){
		Busqueda busqueda = new Busqueda();
		if(asiento.getOrigen() != null)
			busqueda.setOrigen(asiento.getOrigen());
		if(asiento.getDestino() != null)
			busqueda.setDestino(asiento.getDestino());
		if(!asiento.getFecha().isEmpty())
			busqueda.setFecha(new Fecha(asiento.getFecha(), new FormatoSimple("dd/MM/yyyy")));
		return busqueda;
	}
	
	public static Asiento BusquedaToAsiento(Busqueda busqueda){
		Asiento asiento = new Asiento();
		asiento.setVuelo(new Vuelo());
		if(busqueda.getOrigen() != null)
			asiento.setOrigen(busqueda.getOrigen());
		if(busqueda.getDestino() != null)
			asiento.setDestino(busqueda.getDestino());
		if(busqueda.getFecha() != null)
			asiento.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(busqueda.getFecha().getFecha()));
		return asiento;
	}

	@Override
	public Busqueda getSelected() {
		return this.selected;
	}

	public void setSelected(Busqueda selected) {
		this.selected = selected;
	}
}
