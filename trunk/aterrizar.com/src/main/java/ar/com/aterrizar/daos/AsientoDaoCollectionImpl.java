package ar.com.aterrizar.daos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedHome;

import ar.com.aterrizar.entidades.Asiento;


public class AsientoDaoCollectionImpl  extends CollectionBasedHome<Asiento> {


	protected List<Asiento> reservas = new ArrayList<Asiento>();
	protected List<Asiento> compras = new ArrayList<Asiento>();
	protected List<Asiento> sobreReservas = new ArrayList<Asiento>();
		// ********************************************************
		// ** Constructor
		// ********************************************************

		public AsientoDaoCollectionImpl() {
//			this.create(new Socio("Fernando Dodino", "Nazca 333", "fer", "fer"));
//			this.create(new Socio("Nicolas Passerini", "Urquiza 444", "nico", "nico"));
//			this.create(new Socio("Debora Fortini", "Medrano 555", "deby", "deby"));
//			Socio sergio = new Socio("Sergio Magnacco", "urquiza 553", "ser", "ser");
//			sergio.setEstado(Estado.INACTIVO);
//			this.create(sergio);
		}

		@Override
		public Class<Asiento> getEntityType() {
			return Asiento.class;
		}

		@Override
		public Asiento createExample() {
			return new Asiento();
		}

		// ********************************************************
		// ** Criterios de búsqueda específicos
		// ********************************************************

		@Override
		public Predicate<Asiento> getCriterio(final Asiento asientoBuscado) {
			Predicate<Asiento> resultPredicate = this.getCriterioTodas();
			
//			String nombre = socioBuscado.getNombre();
//			String direccion = socioBuscado.getDireccion();
//			Integer id = socioBuscado.getId();
//			Estado estado = socioBuscado.getEstado();
			
			/*
			if (id != null) {
				resultPredicate = new AndPredicate<Socio>(resultPredicate, this.getCriterioPorId(id));
			}
			
			if (nombre != null) {
				resultPredicate = new AndPredicate<Socio>(resultPredicate, this.getCriterioSocioPorNombre(socioBuscado));
			}

			if (direccion != null) {
				resultPredicate = new AndPredicate<Socio>(resultPredicate, this.getCriterioSocioPorDireccion(socioBuscado));
			}
			
			if (estado != null) {
				resultPredicate = new AndPredicate<Socio>(resultPredicate, this.getCriterioSocioPorEstado(socioBuscado));
			}
			*/

			return resultPredicate;
		}

		public void agregarReservaOSobreReserva(Asiento unaReservaOSobreReserva) {
			if(!this.reservas.contains(unaReservaOSobreReserva)){			
				this.reservas.add(unaReservaOSobreReserva);
			}
			
		}

		public void quitarReserva(Asiento unAsiento) {
			this.reservas.remove(unAsiento);
			unAsiento = null;
			
		}

		
		/*
		protected Predicate<Socio> getCriterioSocioPorDireccion(final Socio socioBuscado) {
			return new Predicate() {
				@Override
				public boolean evaluate(Object arg) {
					Socio unSocio = (Socio) arg;
					return unSocio.getDireccion().toLowerCase().contains(socioBuscado.getDireccion().toLowerCase());
				}
			};
		}

		protected Predicate<Socio> getCriterioSocioPorNombre(final Socio socioBuscado) {
			return new Predicate() {
				@Override
				public boolean evaluate(Object arg) {
					Socio unSocio = (Socio) arg;
					return unSocio.getNombre().toLowerCase().contains(socioBuscado.getNombre().toLowerCase());
				}
			};
		}
		
		private Predicate<Socio> getCriterioSocioPorEstado(final Socio socioBuscado) {
			return new Predicate() {
				@Override
				public boolean evaluate(Object arg) {
					Socio unSocio = (Socio) arg;
					return unSocio.getEstado() == null || unSocio.getEstado().equals(socioBuscado.getEstado());
				}
			};
		}
		*/
		
	}

