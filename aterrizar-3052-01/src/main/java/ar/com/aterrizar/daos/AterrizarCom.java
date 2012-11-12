package ar.com.aterrizar.daos;

public class AterrizarCom  {
	private static AterrizarCom instance;
	private AsientoDaoCollectionImpl asientosAterrizar;
	private AsientoHistorialDaoCollectionImpl asientosReservados;
	private AsientoHistorialDaoCollectionImpl asientosComprados;
		
	public static synchronized AterrizarCom initialize() {
		instance = new AterrizarCom();
		instance.asientosAterrizar = new AsientoDaoCollectionImpl();
		instance.asientosComprados = new AsientoHistorialDaoCollectionImpl();
		instance.asientosReservados =  new AsientoHistorialDaoCollectionImpl();
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
		return instance.asientosComprados;
	}
	public AsientoHistorialDaoCollectionImpl getAsientosReservados(){
		return instance.asientosReservados;
	}	
}
