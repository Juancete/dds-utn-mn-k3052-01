package ar.com.aterrizar.arena.appmodel;

import org.uqbar.commons.model.Home;
import org.uqbar.commons.model.SearchByExample;
import org.uqbar.commons.model.UserException;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;

import ar.com.aterrizar.arena.InformationWindow;
import ar.com.aterrizar.arena.InicioWindow;
import ar.com.aterrizar.arena.SobreReservaDialog;
import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;
import ar.com.aterrizar.modelo.state.AsientoYaReservadoException;

public class SearchAsientoByExample extends SearchByExample<Asiento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario miUsuario;
	private InicioWindow w;

	public SearchAsientoByExample(Home<Asiento> home, Usuario unUsuario,
			InicioWindow wOwner) {
		super(home);
		this.miUsuario = unUsuario;
		w = wOwner;
	}

	public void comprar() {
		try {
			this.getSelected().comprar(this.miUsuario);
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		}
		Asiento asientoSel = this.getSelected();
		Asiento unAsiento = new Asiento(asientoSel.getCodigo(), asientoSel.getPrecio(), asientoSel.getUbicacion(), asientoSel.getTipo(), asientoSel.getEstado(), asientoSel.getAerolinea());
		unAsiento.setVuelo(asientoSel.getVuelo());
		AterrizarCom.getInstance().getAsientosComprados().create(unAsiento);
		(new InformationWindow(w, new Usuario(),
				"Su compra se ha realizado exitosamente.")).open();
	}

	public void reservar() {
		try {
			this.getSelected().reservar(this.miUsuario);
			Asiento asientoSel = this.getSelected();
			Asiento unAsiento = new Asiento(asientoSel.getCodigo(), asientoSel.getPrecio(), asientoSel.getUbicacion(), asientoSel.getTipo(), asientoSel.getEstado(), asientoSel.getAerolinea());
			unAsiento.setVuelo(asientoSel.getVuelo());			
			AterrizarCom.getInstance().getAsientosReservados().create(unAsiento);
			(new InformationWindow(w, new Usuario(),
					"Su reserva se ha realizado exitosamente.")).open();
		} catch (NoSeEncuentraDisponibleElAsientoException e) {
			throw new UserException(e.getMessage());
		} catch (AsientoYaReservadoException e) {
			new SobreReservaDialog(w, this.getSelected(), miUsuario)
					.open();
		}
	}
	
	public void search() {
		Busqueda busqueda = new Busqueda();
		if(this.getExample().getOrigen() != null)
			busqueda.setOrigen(this.getExample().getOrigen());
		if(this.getExample().getDestino() != null)
			busqueda.setDestino(this.getExample().getDestino());
		if(!this.getExample().getFecha().isEmpty())
			busqueda.setFecha(new Fecha(this.getExample().getFecha(), new FormatoSimple("dd/MM/yyyy")));
		AterrizarCom.getInstance().getBusquedasRealizadas().create(busqueda);
		super.search();
	}

}
