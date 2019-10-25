package poo.uva.es.informaticafe;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * implementacion de una orden de productos para un comensal
 * 
 * @author rasero99
 *
 */

public class Comanda {

	private int id;
	private int estado;
	private LocalDateTime fecha;
	private double importe;
	private HashMap<String, Integer> productos;

	/**
	 * Crea una comanda vacia
	 */

	public Comanda() {

	}

	/**
	 * Crea una comanda
	 * 
	 * @param productos lista de productos que solicita el cliente
	 */
	public Comanda(HashMap<String, Integer> productos) {

	}

	/**
 	* Devuelve la lista de los productos 
 	* @return lista de productos
 	*/
	public HashMap<String, Integer> productos() {
		// TODO: Implementar productos;
		return HashMap<String, Integer>();
	}

}
