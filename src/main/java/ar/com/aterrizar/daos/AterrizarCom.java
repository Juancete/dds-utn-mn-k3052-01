package ar.com.aterrizar.daos;

public class AterrizarCom  {
	private static AterrizarCom instance;
	private AsientoDaoCollectionImpl asientosAterrizar;
	private AsientoHistorialDaoCollectionImpl asientosCompradosoReservados;
	private BusquedaDaoCollection busquedasRealizadas;
		
	public static synchronized AterrizarCom initialize() {
		instance = new AterrizarCom();
		instance.asientosAterrizar = new AsientoDaoCollectionImpl();
		instance.asientosCompradosoReservados = new AsientoHistorialDaoCollectionImpl();
		instance.busquedasRealizadas = new BusquedaDaoCollection();
		return instance;
	}
	
	public static synchronized AterrizarCom getInstance() {
		if (instance == null) {
			instance = initialize();
		}
		return instance;
	}

	public AsientoDaoCollectionImpl getAsientosAterrizar(){
		return instance.asientosAterrizar;
	}
	public AsientoHistorialDaoCollectionImpl getAsientosComprados(){
		return instance.asientosCompradosoReservados;
	}
	public AsientoHistorialDaoCollectionImpl getAsientosReservados(){
		return instance.asientosCompradosoReservados;
	}	
	public BusquedaDaoCollection getBusquedasRealizadas(){
		return instance.busquedasRealizadas;
	}
}
