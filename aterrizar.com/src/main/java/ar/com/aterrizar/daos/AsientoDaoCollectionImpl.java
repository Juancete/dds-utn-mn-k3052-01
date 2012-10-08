package ar.com.aterrizar.daos;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.AndPredicate;
import org.uqbar.commons.model.CollectionBasedHome;

import com.aterrizar.fecha.modelo.Fecha;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.AterrizarApp;
import ar.com.aterrizar.modelo.Busqueda;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.Vuelo;


public class AsientoDaoCollectionImpl  extends CollectionBasedHome<Asiento> {

	private Usuario usuario;
	
	@Override
	public Class<Asiento> getEntityType() {
		return Asiento.class;
	}

	@Override
	public Asiento createExample() {
		Asiento unAsiento = new Asiento();
		unAsiento.setVuelo(new Vuelo(null, null, null, null, null));
		return unAsiento;
	}

	@Override
	protected Predicate getCriterio(Asiento example) {
		Predicate<Asiento> resultPredicate = this.getCriterioTodas();
		
		String origen = example.getVuelo().getOrigen();
		String destino = example.getVuelo().getDestino();
		Fecha fecha = example.getVuelo().getFechaOrigen();
		
		if (origen != null){
			resultPredicate = new AndPredicate<Asiento>(resultPredicate, this.getCriterioByOrigen(origen));
		}
		if (destino != null){
			resultPredicate = new AndPredicate<Asiento>(resultPredicate, this.getCriterioByDestino(destino));
		}
		if (fecha != null){
			resultPredicate = new AndPredicate<Asiento>(resultPredicate, this.getCriterioByFecha(fecha));
		}		

		return resultPredicate;
	}

	private Predicate<? super Asiento> getCriterioByFecha(final Fecha fecha) {
		return new Predicate(){
			@Override
			public boolean evaluate(Object arg){
				Asiento unAsiento = (Asiento) arg;
				return (unAsiento.getVuelo().getFechaOrigen().cantidadDeDiasEntre(fecha)==0);
			}
		};
	}

	private Predicate<? super Asiento> getCriterioByDestino(final String destino) {
		return new Predicate(){
			@Override
			public boolean evaluate(Object arg){
				Asiento unAsiento = (Asiento) arg;
				return (unAsiento.getVuelo().getDestino().contains(destino));
			}
		};
	}

	private Predicate<Asiento> getCriterioByOrigen(final String origen) {
		return new Predicate(){
			@Override
			public boolean evaluate(Object arg){
				Asiento unAsiento = (Asiento) arg;
				return (unAsiento.getVuelo().getOrigen().contains(origen));
			}
		};
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario unUsuario) {
		if (this.getUsuario() == null){ 
			this.usuario = unUsuario;
			List<Asiento> listaDeAsientos = new ArrayList<Asiento>();
			Busqueda unaBusqueda = new Busqueda();
			unaBusqueda.setEscalas(0);
			listaDeAsientos = AterrizarApp.getInstance().buscarVuelosPara(this.usuario, unaBusqueda);
			for (Asiento unAsiento : listaDeAsientos){
				this.create(unAsiento);
			}		
		}
	}

	}

