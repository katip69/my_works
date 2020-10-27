package poo.uva.es.informaticafe;

import java.util.ArrayList;

/**
 * La clase {@link Almacen} es una implementacion de un sistema de inventario.
 * <p>
 * Esta clase permite la gestión de multiples {@link Vendible}. En caso de que
 * el {@link Vendible} sea un {@link Producto}, el almacen implementa
 * funcionalidad para manejar el stock disponible.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public class Almacen {

	private ArrayList<Vendible> inventario;

	/**
	 * Constructor por defecto de la clase {@link Almacen}. Genera un inventario
	 * vacío.
	 */
	public Almacen() {
		inventario = new ArrayList<>();
	}

	/**
	 * Inserta un {@code Vendible} en el almacén.
	 * 
	 * @param vendible {@link Vendible} que se quiere inventariar en el almacen
	 */
	public void insertaVendible(Vendible vendible) {
		inventario.add(vendible);
	}

	/**
	 * Incrementa la cantidad de un {@link Producto} en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere aumentar stock
	 * @param stock    cantidad a aumentar el stock (ha de ser mayor que 0)
	 * @throws IllegalArgumentException cuando el stock es invalido
	 *                                  {@code stock <= 0}
	 * @throws IllegalArgumentException cuando el producto no existe en el almacen
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
	 * Decrementa la cantidad de un {@link Producto} en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere remover stock
	 * @param stock    cantidad a reducir el stock (ha de ser mayor que 0)
	 * @throws IllegalArgumentException cuando el stock es invalido
	 *                                  {@code stock <= 0}
	 * @throws IllegalArgumentException cuando el producto no existe en el almacen
	 * @throws IllegalArgumentException cuando no hay suficiente stock del producto
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
	 * Elimina un {@link Vendible} del almacen.
	 * 
	 * @param vendible Nombre del {@link Vendible} a eliminar
	 * @throws IllegalArgumentException cuando el {@link Vendible} no existe en el
	 *                                  almacen
	 */
	public void eliminar(Vendible vendible) {
		if (!existe(vendible)) {
			throw new IllegalArgumentException("El producto debe existir");
		}

		inventario.remove(vendible);
	}

	/**
	 * Comprueba la existencia de un {@link Vendible} en el almacen.
	 * 
	 * @param vendible {@link Vendible} del que se quiere comprobar si existe en el
	 *                 almacen
	 * @return true si el {@link Vendible} existe en el almacen, false en caso
	 *         contrario
	 */
	public boolean existe(Vendible vendible) {
		return inventario.contains(vendible);
	}

	/**
	 * Obtiene la cantidad en stock de un {@link Vendible} en el almacen.
	 * 
	 * @param vendible {@link Vendible} del que se quiere comprobar el stock
	 * @return Cantidad de stock disponible del {@link Vendible}
	 * @throws IllegalArgumentException cuando el {@link Vendible} no existe
	 */
	public int cantidad(Vendible vendible) {
		if (!existe(vendible)) {
			throw new IllegalArgumentException("El producto debe existir");
		}
		return vendible.unidadesDisponibles();
	}

	/**
	 * Comprueba si el almacen no tiene ningun producto instanciado
	 * 
	 * @return {@code True} si el almacen esta vacío, {@code False} en caso
	 *         contrario
	 */
	public boolean vacio() {

		return inventario.isEmpty();
	}
}
