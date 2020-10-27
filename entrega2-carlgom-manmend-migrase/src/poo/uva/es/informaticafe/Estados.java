package poo.uva.es.informaticafe;

/**
 * 
 * Representacion de los distintos estados de procesamiento en los que se puede
 * encontrar una comanda durante su ciclo de vida.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 *
 * 
 */
public enum Estados {
	/**
	 * El estado abierto es utilizaco cuando una comanda todavia esta en proceso de
	 * ser terminada, y aun quedan productos que insertar.
	 */
	ABIERTO,
	/**
	 * El estado abierto es utilizado cuando una comanda ya tiene todos los
	 * vendibles que el cliente desea comprar, pero todavia no se ha procesado el
	 * pago de la comanda.
	 */
	CERRADO,
	/**
	 * El estado pagado es utilizado cuando una comanda ya ha sido pagada por el
	 * cliente, y se puede proceder a servir los productos de la comanda.
	 */
	PAGADO,
	/**
	 * El estado anulado es utilizado cuando una comanda ha sido anulada por
	 * cualquier motivo.
	 */
	ANULADO
}
