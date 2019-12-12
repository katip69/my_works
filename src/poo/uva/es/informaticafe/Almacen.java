package poo.uva.es.informaticafe;

import java.util.ArrayList;

/**
 * La clase {@link Almacen} es una implementacion de un sistema de inventario .
 * <p>
 * Permite la gestión de productos y las cantidades disponibles de estos.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class Almacen {

	private ArrayList<Producto> inventario;

	/**
	 * Constructor por defecto de la clase {@link Almacen}. Genera un inventario
	 * vacío.
	 */
	public Almacen() {
		inventario = new ArrayList<>();
	}

	/**
	 * Crea un {@code Producto} en el almacén.
	 * 
	 * @param producto Nombre del producto que se quiere inventariar
	 */
	public void creaProducto(Producto producto) {
		inventario.add(producto);
	}

	/**
	 * Incrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere aumentar stock
	 * @param stock    cantidad a aumentar (ha de ser mayor que 0)
	 * @throws IllegalArgumentException cuando el stock es menor que 0.
	 * @throws IllegalArgumentException cuando el producto no existe
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
	 * @param producto Producto del que se quiere remover stock
	 * @param stock    cantidad a reducir (ha de ser mayor que 0)
	 * @throws IllegalArgumentException cuando el stock es menor que 0
	 * @throws IllegalArgumentException cuando el producto no existe
	 * @throws IllegalArgumentException cuando no hay suficiente stock
	 */
	public void removerStock(Producto producto, int stock) {
		if (stock <= 0) {
			throw new IllegalArgumentException("La cantidad de producto a aumentar no puede ser negativa");
		}

		if (!existe(producto)) {
			throw new IllegalArgumentException("El producto debe estar");
		}

		if (stock > cantidad(producto)) {
			throw new IllegalArgumentException("No hay suficiente stock");
		}

		producto.reducirStock(stock);
	}

	/**
	 * Elimina un producto del almacen.
	 * 
	 * @param producto Nombre del producto a eliminar
	 * @throws IllegalArgumentException cuando el producto no existe
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
	 * @param producto Producto del que se quiere comprobar si existe en el almacen
	 * @return true si el producto existe en el almacen, false en caso contrario
	 */
	public boolean existe(Producto producto) {
		return inventario.contains(producto);
	}

	/**
	 * Obtiene la cantidad en stock de un producto en el almacen.
	 * 
	 * @param producto Producto del que se quiere comprobar el stock
	 * @return Cantidad de stock disponible del producto
	 * @throws IllegalArgumentException cuando el producto no existe
	 */
	public int cantidad(Producto producto) {
		if (!existe(producto)) {
			throw new IllegalArgumentException("El producto debe existir");
		}
		return producto.unidadesDisponibles();
	}

	/**
	 * 
	 * @return {@code True} si el almacen esta vacío, {@code False} en caso
	 *         contrario
	 */
	public boolean vacio() {

		return inventario.isEmpty();
	}
}
