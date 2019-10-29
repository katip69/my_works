package poo.uva.es.informaticafe;

import java.util.ArrayList;
import poo.uva.es.informaticafe.Producto;

/**
 * La clase {@link Almacen} es una implementacion de un sistema de inventario.
 * <p>
 * Permite la gestion de productos y las cantidades disponibles de estos.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class Almacen {

	private ArrayList <Producto> inventario;

	/**
	 * Constructor por defecto de la clase {@link Almacen}. Genera un inventario
	 * vacio.
	 */
	public Almacen() {
		inventario = new ArrayList <Producto>();
	}

	/**
	 * Crea un {@code Producto} en el almacen.
	 * 
	 * @param producto
	 *            Nombre del producto que se quiere inventariar
	 */
	public void creaProducto(Producto producto) {
		inventario.add(producto);
	}

	
	/**
	 * Incrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto
	 *            Producto del que se quiere aumentar stock
	 * @param stock
	 *            cantidad a aumentar (ha de ser mayor que 0)
	 */
	public void incrementarStock(Producto producto, int stock) {
		if (stock <= 0) {
			throw new IllegalArgumentException("El stock debe ser mayor que 0");
		}

		if (!existe(producto)) {
			throw new IllegalArgumentException("El producto no existe");
		}

		producto.aumentarStock(stock);
	}

	/**
	 * Decrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto
	 *            Producto del que se quiere remover stock
	 * @param stock
	 *            cantidad a reducir (ha de ser mayor que 0)
	 */
	public void removerStock(Producto producto, int stock) {
		if (stock <= 0) {
			throw new IllegalArgumentException("La cantidad de producto a aumentar no puede ser negativa");
		}

		if (!existe(producto)) {
			throw new IllegalArgumentException("El producto debe existir");
		}

		if (stock > cantidad(producto)) {
			throw new IllegalArgumentException("No hay suficiente stock");
		}

		producto.reducirStock(stock);
	}

	/**
	 * Elimina un producto del almacen.
	 * 
	 * @param producto
	 *            Nombre del producto a eliminar
	 */
	public void eliminar(Producto producto) {
		if (!existe(producto)) {
			throw new IllegalArgumentException("El producto debe existir");
		}

		inventario.remove(producto);
	}

	/**
	 * Comprueba la existencia de un producto en el almacen.
	 * 
	 * @param producto
	 *            Producto del que se quiere comprobar si existe en el almacen
	 * @return true si el producto existe en el almacen, false en caso contrario
	 */
	public boolean existe(Producto producto) {
		return inventario.contains(producto);
	}

	/**
	 * Obtiene la cantidad en stock de un producto en el almacen.
	 * 
	 * @param producto
	 *            Producto del que se quiere comprobar el stock
	 * @return Cantidad de stock disponible del producto
	 */
	public int cantidad(Producto producto) {
		if (!existe(producto)) {
			throw new IllegalArgumentException("El producto debe existir");
		}
		return producto.unidadesDisponibles();
	}
	/**
	 * 
	 * @return {@code True} si el almacen esta vacío, {@code False} en caso contrario
	 */
	public boolean vacio() {
		
		return inventario.isEmpty();
	}
}
