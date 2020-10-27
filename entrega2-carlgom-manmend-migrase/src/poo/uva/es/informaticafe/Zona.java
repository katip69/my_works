package poo.uva.es.informaticafe;

/**
 * Representacion de las distintas zonas topograficas donde se puede repartir
 * una {@link ComandaDomicilio}.
 * 
 * Cada zona trae asignada un importe distinto, debido a la distancia al negocio
 * u otros factores que puedan influenciar el coste.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 *
 */
public enum Zona {
	/**
	 * La zona 1 es utilizada para representar direcciones cercanas al negocio, por
	 * lo que el coste adicional para el reparto de la comanda no es muy elevado.
	 */
	ZONA1,
	/**
	 * La zona 2 es utilizada para representar direcciones a una distancia moderada
	 * al negocio, por lo que el coste adicional para el reparto de la comanda es
	 * superior a la zona 1.
	 */
	ZONA2,
	/**
	 * La zona resto es utilizada para representar cualquier otra direccion no
	 * contenida en las zonas previas, por lo que el importe para repartir una
	 * comanda en esta zona es considerablemente elevado.
	 */
	RESTO
}
