/**UTN FRBA - Tp #1 Manejo de Fechas
 * 
 * Diseño de sistemas - Curso k3052
 * Profesores: fdodino, fscarpa 
 */
package com.aterrizar.fecha.modelo;

import java.util.Date;

/**
 * Formato representa la abtraccion de un formato determinado para una fecha,
 * El formato es lo que le indica a la fecha como debe ser interpretado el string para su posterior
 * conversion a fecha
 * @author iguardines
 * @version 1.0
 *
 */
public abstract class Formato {

	public abstract Date parse(String aDate);

}
