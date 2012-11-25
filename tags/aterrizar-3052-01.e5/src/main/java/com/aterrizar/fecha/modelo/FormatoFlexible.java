/**UTN FRBA - Tp #1 Manejo de Fechas
 * 
 * Diseño de sistemas - Curso k3052
 * Profesores: fdodino, fscarpa 
 */
package com.aterrizar.fecha.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.aterrizar.fecha.excepcion.FechaException;

/**
 * FormatoFlexible representa un formato que posee diferentes formatos, dada una representacion string n
 * Formato flexible busca internamente por los diferentes formatos que posee para ver cual le corresponde.
 * @author iguardines
 *
 */
public class FormatoFlexible extends Formato {

	protected Set<Formato> formatos;

	public FormatoFlexible() {
		this.formatos = new HashSet<Formato>();
	}

	@Override
	public Date parse(String aDate) {

		for (Formato unFormato : this.formatos) {
			try {
				// si el formato es encotrado hace el parse y retorna el
				// resultado.
				return unFormato.parse(aDate);
			} catch (RuntimeException formatException) {
				// formato no encontrado.
			}
		}
		throw new FechaException(
				"El formato no esta incluido en grupo de Formatos flexiblas");
	}

	public void agregarFormato(Formato unFormato) {
		this.formatos.add(unFormato);
	}
	
	public void quitarFormato(Formato unFormato){
		this.formatos.remove(unFormato);
	}

}
