package poo.uva.es.informaticafe;

import java.util.HashMap;
import poo.uva.es.informaticafe.Producto;

/**
 * La clase {@link Almacen} es una implementacion de un sistema de inventario.
 * <p>
 * Permite la gestion de productos y las cantidades disponibles de estos.
 * 
 * @author Carlos Gomez
 * @version 1.0
 */
public class Almacen {

	private HashMap<Producto, Integer> inventario;

	/**
	 * Constructor por defecto de la clase {@link Almacen}. Genera un inventario
	 * vacio.
	 */
	public Almacen() {
		inventario = new HashMap<Producto, Integer>();
	}

	/**
	 * Crea una instancia del producto en el almacen, con un stock de 0 unidades.
	 * 
	 * @param producto Nombre del producto que se quiere inventariar
	 */
	public void creaProducto(Producto producto) {
		inventario.put(producto, 0);
	}

	/**
	 * Crea una instancia del producto en el almacen, con un stock especificado.
	 * 
	 * @param producto Nombre del producto que se quiere inventariar
	 * @param stock    Cantidad de producto que se quiere inicializar (ha de ser
	 *                 igual o mayor a 0)
	 */
	public void creaProducto(Producto producto, int stock) {
		if (stock < 0) {
			// TODO: Throw exception due to invalid stock amount
		}

		inventario.put(producto, stock);
	}

	// TODO: Ask about incrementarStock and removerStock merging due to code
	// duplicity

	/**
	 * Incrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere aumentar stock
	 * @param stock    cantidad a aumentar (ha de ser mayor que 0)
	 */
	public void incrementarStock(Producto producto, int stock) {
		if (stock <= 0) {
			// TODO: Throw exception because the stock is invalid
		}

		if (!existe(producto)) {
			// TODO: Throw exception because the product doesn't exist
		}

		creaProducto(producto, cantidad(producto) + stock);
	}

	/**
	 * Decrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere remover stock
	 * @param stock    cantidad a reducir (ha de ser mayor que 0)
	 */
	public void removerStock(Producto producto, int stock) {
		if (stock <= 0) {
			// TODO: Throw exception because the stock is invalid
		}

		if (!existe(producto)) {
			// TODO: Throw exception because the product doesn't exist
		}

		if (stock > cantidad(producto)) {
			// TODO: Throw exception because there's not enough stock
		}

		creaProducto(producto, cantidad(producto) - stock);
	}

	/**
	 * Elimina un producto del almacen.
	 * 
	 * @param producto Nombre del producto a eliminar
	 */
	public void eliminar(Producto producto) {
		if (!existe(producto)) {
			// TODO: Throw exception because the product doesn't exist
		}

		inventario.remove(producto);
	}

	/**
	 * Comprueba la existencia de un producto en el almacen.
	 * 
	 * @param producto Producto del que se quiere comprobar si existe en el almacen
	 * @return true si el producto existe en el almacen, false en caso contrario
	 */
	public boolean existe(Producto producto) {
		return inventario.containsKey(producto);
	}

	/**
	 * Obtiene la cantidad en stock de un producto en el almacen.
	 * 
	 * @param producto Producto del que se quiere comprobar el stock
	 * @return Cantidad de stock disponible del producto
	 */
	public int cantidad(Producto producto) {
		if (!existe(producto)) {
			// TODO: Throw exception because the product doesn't exist
		}
		return inventario.get(producto);
	}
}
