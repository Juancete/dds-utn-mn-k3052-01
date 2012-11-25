package ar.com.aterrizar.modelo.FiltrosDeBusqueda;

import java.math.BigDecimal;

import ar.com.aterrizar.entidades.Asiento;

public class FiltroPrecio extends Filtro {

	private BigDecimal precioMaximo;
	private BigDecimal precioMinimo;
	
	public FiltroPrecio(BigDecimal precioMaximo, BigDecimal precioMinimo){
		this.precioMaximo = precioMaximo;
		this.precioMinimo = precioMinimo;
	}
	
	public void setPrecioMaximo(BigDecimal precioMaximo){
		this.precioMaximo = precioMaximo;
	}
	public void setPrecioMinimo(BigDecimal precioMinimo){
		this.precioMinimo = precioMinimo;
	}
	@Override
	protected boolean evaluarLaCondicion(Asiento unAsiento) {
		//Ni puta idea de como es el between XD
		return (unAsiento.getPrecio().compareTo(precioMaximo)>0 || unAsiento.getPrecio().compareTo(precioMinimo)<0);
	}
}

