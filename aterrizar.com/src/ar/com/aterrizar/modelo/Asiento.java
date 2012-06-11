package ar.com.aterrizar.modelo;

import java.math.BigDecimal;

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
	
	public Boolean soySuperOferta(){
//		Implementación la condición de super-oferta		
//		Existen asientos que son considerados super ofertas, estos son aquellos asientos que
//		cumplen con alguna de las siguientes condiciones
//		* son de primera clase y su precio total es menor a $8000
//		* son de clase ejecutiva y su precio total es menor a $4000
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
}