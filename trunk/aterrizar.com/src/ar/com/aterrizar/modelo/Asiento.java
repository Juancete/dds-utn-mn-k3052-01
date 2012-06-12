package ar.com.aterrizar.modelo;

import java.math.BigDecimal;

import ar.com.aterrizar.modelo.adapter.Aerolinea;

public class Asiento {

	protected String codigo;
	protected BigDecimal precio;
	protected char ubicacion;
	protected char tipo;
	protected boolean disponible;
	protected Aerolinea aerolinea;

	
	public Asiento(String unCodigo, BigDecimal unPrecio,char unaUbicacion, char unTipo, boolean estaDisponible, Aerolinea unaAerolinea){
		this.codigo = unCodigo;
		this.precio = unPrecio;
		this.ubicacion = unaUbicacion;
		this.tipo = unTipo;
		this.disponible = estaDisponible;
		this.aerolinea = unaAerolinea;
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
	
	@Override
	public int hashCode() {
		int enteroDisponible= 0;
		if(this.disponible) enteroDisponible = 1;
		return (int)this.tipo + (int)this.ubicacion + (int)this.aerolinea.hashCode() + this.precio.hashCode() + this.codigo.hashCode()+ enteroDisponible ;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Asiento))
			return false;
		Asiento otroAsiento = (Asiento) obj;
		return codigo.equals(otroAsiento.getCodigo()) && precio.equals(otroAsiento.precio) && ubicacion == otroAsiento.ubicacion && tipo == otroAsiento.tipo && disponible == otroAsiento.disponible && aerolinea.equals(otroAsiento.getAerolinea());
	}
}