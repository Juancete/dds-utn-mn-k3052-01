package ar.com.aterrizar.modelo;

import java.math.BigDecimal;
import java.util.LinkedList;

import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.state.Estado;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

public class Asiento {

	public String codigoDeVuelo;
	public Integer numeroDeAsiento;
	public String codigo;
	public BigDecimal precio;
	public char ubicacion; //'V' ventanilla, 'C' centro, 'P' pasillo
	public char tipo;	//clase: 'P' primera, 'E' ejecutivo, 'T' turista
	public boolean disponible;
	public Aerolinea aerolinea;
	private Vuelo vuelo;
	private Viaje viaje;	
	protected Estado estado;
	protected LinkedList<Usuario> usuariosQueReservan;
	
	public Asiento(String unCodigo, BigDecimal unPrecio,char unaUbicacion, char unTipo, Estado estado, Aerolinea unaAerolinea){
		this.codigo = unCodigo;
		this.precio = unPrecio;
		this.ubicacion = unaUbicacion;
		this.tipo = unTipo;
		this.estado = estado;
		this.aerolinea = unaAerolinea;
		this.usuariosQueReservan = new LinkedList<Usuario>();
	}
	
	public boolean soySuperOferta(){
//		Implementación la condición de super-oferta		
//		Existen asientos que son considerados super ofertas, estos son aquellos asientos que
//		cumplen con alguna de las siguientes condiciones
//		* son de primera clase y su precio total es menor a $8000
//		* son de clase ejecutiva y su precio total es menor a $4000s
		return (this.precio.compareTo(new BigDecimal(8000))<0 && this.tipo == 'P')||(this.precio.compareTo(new BigDecimal(4000))<0 && this.tipo == 'E');
	}
	public void setCodigo(String unCodigo){
		this.codigo = unCodigo;
	}	

	public String getCodigo(){
		return this.codigo;
	}
	
	public void setPrecio(BigDecimal unPrecio){
		this.precio = unPrecio;
	}
	
	public Aerolinea getAerolinea(){
		return this.aerolinea; 
	}
	public void setAerolinea(Aerolinea unaAerolinea){
		this.aerolinea = unaAerolinea;
	}
	
	public void setNumeroDeAsiento(Integer numero){
		this.numeroDeAsiento = numero;
	}
	
	public Integer getNumeroDeAsiento(){
		return this.numeroDeAsiento;
	}
	
	public void setDisponibilidad(boolean bol){
		this.disponible = bol;
	}
	
	public boolean isDisponible(){
		return (this.getEstado() instanceof EstadoDisponible);
	}
	
	@Override
	public int hashCode() {
		int enteroDisponible= 0;
		if(this.disponible) enteroDisponible = 1;
		return (int)this.tipo + (int)this.ubicacion + this.aerolinea.hashCode() + this.precio.hashCode() + this.codigo.hashCode()+ enteroDisponible ;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Asiento))
			return false;
		Asiento otroAsiento = (Asiento) obj;
		return codigo.equals(otroAsiento.getCodigo()) && precio.equals(otroAsiento.precio) && ubicacion == otroAsiento.ubicacion && tipo == otroAsiento.tipo && disponible == otroAsiento.disponible && aerolinea.equals(otroAsiento.getAerolinea());
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	
	public long getTiempo() {
		return viaje.getTiempo(this);
		
	}
	public Long getTiempoEnElAire(){
		//TODO: está hardcodeado por que deberíamos implementar el manejo de fecha con horario también.
		return (long) vuelo.fechaOrigen.cantidadDeDiasEntre(vuelo.fechaDestino);
	}
	
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public boolean llegaAntesDe(Asiento asiento) {
		//TODO Mejorar la forma en que se calcula el tiempo teniendo en cuenta las horas de ambos asientos
		if(!this.vuelo.getFechaDestino().esAnteriorA(asiento.getVuelo().getFechaOrigen())){
			return false;
		}
		return true;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Boolean estadoReservado(){
		return (this.getEstado() instanceof EstadoReservado);
	}

	public Usuario getReservante() {
		return (Usuario)((this.usuariosQueReservan.get(0)!=null)?this.usuariosQueReservan.get(0): new RuntimeException("no hay reservante"))	;
	}

	public void setReservante(Usuario reservante) {
		this.usuariosQueReservan.addLast(reservante);
	}
	
	public void reservaVencidaAsignarProximaSobreReserva(){
		this.usuariosQueReservan.remove(0);
		if(tieneSobreReserva().booleanValue()){
			this.estado.reservar(this, this.usuariosQueReservan.get(0));			
		}else {
			this.setEstado(new EstadoDisponible());
		}
	}
	
	public Boolean tieneSobreReserva(){
		return new Boolean(this.usuariosQueReservan.size()>1);
	}
	
	public void eliminarReservas(){
		this.usuariosQueReservan= new LinkedList<Usuario>();
	}
}