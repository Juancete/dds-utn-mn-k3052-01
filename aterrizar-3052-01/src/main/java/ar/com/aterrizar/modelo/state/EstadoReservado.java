package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.daos.AsientoReservadoDaoCollectionImpl;
import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public class EstadoReservado extends Estado {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void comprar(Asiento unAsiento, Usuario unUsuario) {
		if(!unAsiento.getReservante().getDni().equalsIgnoreCase(unUsuario.getDni()))
		{
			throw new NoSeEncuentraDisponibleElAsientoException("El asiento esta reservado a otro Usuario");
		}
		unAsiento.aerolinea.comprarAsiento(unAsiento, unUsuario.getDni());
		((AsientoReservadoDaoCollectionImpl) AterrizarCom.getInstance().getHome(EstadoReservado.class)).delete((EstadoReservado) unAsiento.getEstado());
		unAsiento.setEstado(new EstadoComprado());
		unAsiento.getEstado().setMiAsiento(unAsiento);
		unAsiento.eliminarReservas();
		
		//((AsientoDaoCollectionImpl) this.aplication).quitarReserva(unAsiento); TODO sacar la reserva de la home
	}

	@Override
	public void reservar(Asiento unAsiento, Usuario unUsuario) {
		try{
			if(unAsiento.estadoReservado()) throw new NoSeEncuentraDisponibleElAsientoException(); 
			unAsiento.aerolinea.reservarAsiento(unUsuario, unAsiento);		
			unAsiento.setEstado(new EstadoReservado());
			unAsiento.getEstado().setMiAsiento(unAsiento);
		}
		catch(NoSeEncuentraDisponibleElAsientoException e){
			//simplemente esta para que no salga del metodo ya que es una excepcion valida.
			throw new AsientoYaReservadoException();
		}
//		finally
//		{
//			unAsiento.setReservante(unUsuario);
//		}
		}
	
	public void sobreReservar(Asiento unAsiento, Usuario unUsuario){
		unAsiento.aerolinea.reservarAsiento(unUsuario, unAsiento);
		unAsiento.setReservante(unUsuario);
	}
	}


