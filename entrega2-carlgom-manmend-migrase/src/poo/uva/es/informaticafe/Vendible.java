package poo.uva.es.informaticafe;

import java.util.Map;

/**
 * Un vendible representa un producto o coleccion de productos, los cuales
 * pueden ser vendidos a un consumidor y disponen de un stock limitado.
 * 
 * Cada vendible tiene un nombre y descripcion, un stock y un precio.
 * Adicionalmente, cada vendible implementa un metodo para reducir su stock.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public abstract class Vendible {

	private String nombre;
	private String descripcion;

	/**
	 * Constructor de {@code Vendible}
	 * 
	 * @param nombre      Nombre del vendible
	 * @param descripcion Descripción del vendible
	 */
	public Vendible(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el nombre del vendible.
	 * 
	 * @return nombre del vendible
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Devuelve la descripcin del vendible.
	 * 
	 * @return descripcion del vendible
	 */
	public String descripcion() {
		return descripcion;
	}

	/**
	 * Devuelve el precio del vendible.
	 * 
	 * @return precio del vendible
	 */
	public abstract double precio();

	/**
	 * Reduce el stock de un vendible
	 * 
	 * @param cantidad cantidad de veces que substraer el stock de vendible
	 */
	public abstract void reducirStock(int cantidad);

	/**
	 * Devuelve las unidades disponibles del vendible.
	 * 
	 * @return cantidad de unidades disponibles
	 */
	public abstract int unidadesDisponibles();

	/**
	 * Devuelve un desglose de los productos que componen al vendible, y cuantas
	 * unidades de cada producto forman parte de este.
	 * 
	 * @return Map con los productos pertenecientes al vendible, y la cantidad de
	 *         estos
	 */
	public abstract Map<Producto, Integer> desglose();
}
