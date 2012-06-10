package ar.com.aterrizar.modelo;

import java.math.BigDecimal;

import ar.com.aterrizar.modelo.adapter.AerolineaLanchitaAdapter;

public class Asiento {

	protected String codigo;
	protected BigDecimal precio;
	protected char ubicacion;
	protected char tipo;
	protected boolean disponible;
	protected AerolineaLanchitaAdapter unaAerolinea;

	public Boolean soySuperOferta(){
//		Existen asientos que son considerados super ofertas, estos son aquellos asientos que
//		cumplen con alguna de las siguientes condiciones
//		● son de primera clase y su precio total es menor a $8000
//		● son de clase ejecutiva y su precio total es menor a $4000
		if (this.precio.compareTo(new BigDecimal(8000))<0){
			
		}
		throw new RuntimeException();
	}
	
	public void setPrecio(BigDecimal unPrecio){
		this.precio = unPrecio;
	}
	
	public AerolineaLanchitaAdapter getAerolinea(){
		return this.unaAerolinea; 
	}
	public void setAerolinea(AerolineaLanchitaAdapter unaAerolinea){
		this.unaAerolinea = unaAerolinea;
	}
}