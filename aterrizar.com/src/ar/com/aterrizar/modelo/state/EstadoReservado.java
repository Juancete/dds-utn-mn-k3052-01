package ar.com.aterrizar.modelo.state;

import ar.com.aterrizar.modelo.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.adapter.NoSeEncuentraDisponibleElAsientoException;

public class EstadoReservado extends Estado {

	@Override
	public void comprar(Asiento unAsiento, Usuario unUsuario) {
		if(!unAsiento.getReservante().getDni().equalsIgnoreCase(unUsuario.getDni()))
		{
			throw new NoSeEncuentraDisponibleElAsientoException("El asiento esta reservado a otro Usuario");
		}
		unAsiento.aerolinea.comprarAsiento(unAsiento, unUsuario.getDni());
		unAsiento.setEstado(new EstadoComprado());
		unAsiento.eliminarReservas();
		this.aplication.quitarReserva(unAsiento);
	}

	@Override
	public void reservar(Asiento unAsiento, Usuario unUsuario) {
		try{
			if(unAsiento.estadoReservado()) throw new NoSeEncuentraDisponibleElAsientoException(); 
			unAsiento.aerolinea.reservarAsiento(unUsuario, unAsiento);		
			unAsiento.setEstado(new EstadoReservado());
		}
		catch(NoSeEncuentraDisponibleElAsientoException e){
			//simplemente esta para que no salga del metodo ya que es una excepcion valida.
		}finally
		{
			unAsiento.setReservante(unUsuario);
		}
		}
	}


