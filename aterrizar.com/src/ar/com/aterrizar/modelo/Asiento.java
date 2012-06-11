package ar.com.aterrizar.modelo;

import java.math.BigDecimal;

public class Asiento {

	protected String codigo;
	protected BigDecimal precio;
	protected char ubicacion;
	protected char tipo;
	protected boolean disponible;
	protected Aerolinea unaAerolinea;

	public Boolean soySuperOferta(){
//		TODO implementar la condición de super-oferta		
//		Existen asientos que son considerados super ofertas, estos son aquellos asientos que
//		cumplen con alguna de las siguientes condiciones
//		â—� son de primera clase y su precio total es menor a $8000
//		â—� son de clase ejecutiva y su precio total es menor a $4000
		if (this.precio.compareTo(new BigDecimal(8000))<0){
			
		}
		throw new RuntimeException();
	}
	
	public void setPrecio(BigDecimal unPrecio){
		this.precio = unPrecio;
	}
	
	public Aerolinea getAerolinea(){
		return this.unaAerolinea; 
	}
	public void setAerolinea(Aerolinea unaAerolinea){
		this.unaAerolinea = unaAerolinea;
	}
}