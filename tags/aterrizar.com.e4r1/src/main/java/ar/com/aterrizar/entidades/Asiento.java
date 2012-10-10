package ar.com.aterrizar.entidades;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.TransactionalAndObservable;

import com.aterrizar.fecha.modelo.Fecha;
import com.aterrizar.fecha.modelo.FormatoSimple;

import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.Viaje;
import ar.com.aterrizar.modelo.Vuelo;
import ar.com.aterrizar.modelo.adapter.Aerolinea;
import ar.com.aterrizar.modelo.state.Estado;
import ar.com.aterrizar.modelo.state.EstadoDisponible;
import ar.com.aterrizar.modelo.state.EstadoReservado;

@TransactionalAndObservable
public class Asiento extends Entity {

		private static final long serialVersionUID = 2365011956533498906L;
		
		public static String ORIGEN = "origen";
		public static String DESTINO = "destino";
		public static String FECHA = "fecha";
	
	private String codigoDeVuelo;
	public Integer numeroDeAsiento;
	public String codigo;
	public BigDecimal precio;
	private char ubicacion; //'V' ventanilla, 'C' centro, 'P' pasillo
	private char tipo;	//clase: 'P' primera, 'E' ejecutivo, 'T' turista
	//private boolean disponible;
	public Aerolinea aerolinea;
	public Vuelo vuelo;
	public Viaje viaje;	
	protected Estado estado;
	private List<Usuario> usuariosQueReservan;
	
	public Asiento(String unCodigo, BigDecimal unPrecio,char unaUbicacion, char unTipo, Estado estado, Aerolinea unaAerolinea){
		this.codigo = unCodigo;
		this.precio = unPrecio;
		this.setUbicacion(unaUbicacion);
		this.setTipo(unTipo);
		this.estado = estado;
		this.aerolinea = unaAerolinea;
		this.setUsuariosQueReservan(new ArrayList<Usuario>());
		this.estado.setMiAsiento(this);
	}
	
	public Asiento(){}
	
	public boolean soySuperOferta(){
//		Implementación la condición de super-oferta		
//		Existen asientos que son considerados super ofertas, estos son aquellos asientos que
//		cumplen con alguna de las siguientes condiciones
//		* son de primera clase y su precio total es menor a $8000
//		* son de clase ejecutiva y su precio total es menor a $4000s
		return (this.precio.compareTo(new BigDecimal(8000))<0 && this.getTipo() == 'P')||(this.precio.compareTo(new BigDecimal(4000))<0 && this.getTipo() == 'E');
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
	
	public BigDecimal getPrecio(){
		return this.precio;
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
	
//	public void setDisponibilidad(boolean bol){
//		this.disponible = bol;
//	}
	
	public boolean isDisponible(){
		return (this.getEstado() instanceof EstadoDisponible);
	}
	
	@Override
	public int hashCode() {
		int tipo =(this.getTipo() == '\u0000') ? 0 : (int)this.getTipo() ;
		int ubicacion =(this.getUbicacion() == '\u0000') ? 0 : (int)this.getUbicacion() ;
		return  tipo + ubicacion + ((this.aerolinea == null) ? 0: this.aerolinea.hashCode()) + 
				((this.precio == null) ? 0: this.precio.hashCode()) + 
				((this.codigo == null) ? 0: this.codigo.hashCode()) ;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Asiento))
			return false;
		Asiento otroAsiento = (Asiento) obj;
		return codigo.equals(otroAsiento.getCodigo()) && precio.equals(otroAsiento.precio) && getUbicacion() == otroAsiento.getUbicacion() && getTipo() == otroAsiento.getTipo() && aerolinea.equals(otroAsiento.getAerolinea());
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
		return (long) vuelo.getFechaOrigen().cantidadDeDiasEntre(vuelo.fechaDestino);
	}
	
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Viaje getViaje(){
		return this.viaje;
	}

	public boolean llegaAntesDe(Asiento asiento) {
		//TODO Mejorar la forma en que se calcula el tiempo teniendo en cuenta las horas de ambos asientos
		//por el momento este metodo retornara verdadero
		return true;
//		if(!this.vuelo.getFechaDestino().esAnteriorA(asiento.getVuelo().getFechaOrigen())){
//			return false;
//		}
//		return true;
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
		return (Usuario)((this.getUsuariosQueReservan().get(0)!=null)?this.getUsuariosQueReservan().get(0): new RuntimeException("no hay reservante"))	;
	}

	public void setReservante(Usuario reservante) {
		this.getUsuariosQueReservan().add(reservante); //antes addLast por que era LinkedList :O
	}
	
	public void reservaVencidaAsignarProximaSobreReserva(){
		this.getUsuariosQueReservan().remove(0);
		if(tieneSobreReserva().booleanValue()){
			this.estado.reservar(this, this.getUsuariosQueReservan().get(0));			
		}else {
			this.setEstado(new EstadoDisponible());
		}
	}
	
	public Boolean tieneSobreReserva(){
		return new Boolean(this.getUsuariosQueReservan().size()>1);
	}
	
	public void eliminarReservas(){
		this.setUsuariosQueReservan(new LinkedList<Usuario>());
	}

	public List<Usuario> getUsuariosQueReservan() {
		return usuariosQueReservan;
	}

	public void setUsuariosQueReservan(List<Usuario> usuariosQueReservan) {
		this.usuariosQueReservan = usuariosQueReservan;
	}

	public String getCodigoDeVuelo() {
		return codigoDeVuelo;
	}

	public void setCodigoDeVuelo(String codigoDeVuelo) {
		this.codigoDeVuelo = codigoDeVuelo;
	}

	public char getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(char ubicacion) {
		this.ubicacion = ubicacion;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public String getOrigen(){
		return this.getVuelo().getOrigen();
	}
	
	public void setOrigen(String unOrigen){
		this.getVuelo().setOrigen(unOrigen);
	}
	
	public String getDestino(){
		return this.getVuelo().getDestino();
	}
	public void setDestino(String unDestino){
		this.getVuelo().setDestino(unDestino);
	}
	
	public String getFecha(){
		if (this.getVuelo().getFechaOrigen() == null) {return "";}
		return new SimpleDateFormat("dd/MM/yyyy").format(this.getVuelo().getFechaOrigen().obtenerFecha());
	}
	
	public void setFecha(String unaFecha){
		try{
			this.getVuelo().setFechaOrigen(new Fecha(unaFecha, new FormatoSimple("dd/MM/yyyy")));
		}
		catch (Exception e)
		{
			throw new UserException("La fecha no tiene el formato dd/MM/yyyy");
		}
		
	}

}