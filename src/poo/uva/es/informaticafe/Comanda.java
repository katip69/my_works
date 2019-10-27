package poo.uva.es.informaticafe;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import poo.uva.es.informaticafe.Producto;

/**
 * implementacion de una orden de productos para un comensal
 * 
 * @author migrase
 * @author carlgom
 * @author manmend
 */

public class Comanda {

	private int estado;
	private LocalDateTime fecha;
	private double importe;
	private HashMap<Producto, Integer> productos;

	/**
	 * Crea una comanda vacía
	 */

	public Comanda() {
		fecha = LocalDateTime.now();
		productos = new HashMap<Producto, Integer>();
		importe = 0;
	}

	/**
	 * Crea una comanda
	 * 
	 * @param productos lista de productos que solicita el cliente
	 */
	public Comanda(HashMap<Producto, Integer> productos) {
		fecha = LocalDateTime.now();
		this.productos = productos;
		importe = importe();

	}

	/**
	 * Devuelve un HashMap con todos los productos y sus cantidades pertenecientes a
	 * la comanda.
	 * 
	 * @return pares producto-cantidad contenidos en la comanda
	 */
	public HashMap<Producto, Integer> productos() {
		// TODO: This might break things if they modify it. Check with Felix
		return productos;
	}

	private void updateImporte() {
		double importe = 0;

		// Itera sobre los pares HashMap creando un Map individual por cada par
		for (Map.Entry<Producto, Integer> par : productos().entrySet()) {
			// Suma el precio de cada producto por la cantidad al importe
			importe += par.getKey().precio() * par.getValue();
		}

		this.importe = importe;
	}

	/**
	 * Calcula el precio total de la comanda actual.
	 * 
	 * @return Valor (en euros) de la comanda
	 */
	public double importe() {
		updateImporte(); // Asegura que el importe es correcto
		return importe;
	}

	/**
	 * Comprueba si un producto espeficidado existe en la comanda
	 * 
	 * @param producto Producto a comprobar su existencia
	 * @return true si existe en la comanda, false en caso contrario
	 */
	public boolean tieneProducto(Producto producto) {
		return productos().containsKey(producto);
	}

	/**
	 * Introduce un nuevo producto a la comanda.
	 * 
	 * @param producto Producto a introducir en la comanda
	 * @param cantidad Cantidad de producto a introducir (0 < cantidad < stock)
	 */
	public void addProducto(Producto producto, int cantidad) {
		if (tieneProducto(producto)) {
			// TODO: Throw exception since the product already exists
		}
		if (cantidad <= 0) {
			// TODO: Throw exception due to invalid amount
		}
		if (cantidad > producto.unidadesDisponibles()) {
			// TODO: Throw exception due to invalid amount
		}

		producto.reducirStock(cantidad);
		productos().put(producto, cantidad);
	}

	/**
	 * Remueve un producto de la comanda.
	 * <p>
	 * El stock del producto utilizado en la comanda se vuelve disponible de nuevo.
	 * 
	 * @param producto producto a remover de la comanda
	 */
	public void removeProducto(Producto producto) {
		if (!tieneProducto(producto)) {
			// TODO: Throw exception due to removing nonexisting product
		}

		producto.aumentarStock(cantidad(producto)); // Devuelve el stock usado
		productos().remove(producto);

	}

	/**
	 * Dado un producto, devuelve la cantidad de unidades de ese producto asignadas
	 * a la comanda.
	 * 
	 * @param producto producto del que se quiere comprobar la cantidad
	 * @return cantidad de producto en la comanda
	 */
	public int cantidad(Producto producto) {
		if (!tieneProducto(producto)) {
			// TODO: Throw exception due to invalid product
		}

		return productos().get(producto);
	}

	/**
	 * Modifica la cantidad de un producto asignada a la comanda.
	 * <p>
	 * 
	 * @param producto producto a remover de la comanda
	 * @param cantidad nueva cantidad de producto a usar en la comanda
	 */
	public void modificaProducto(Producto producto, int cantidad) {
		if (!tieneProducto(producto)) {
			// TODO: Throw exception due to modifying nonexisting product
		}
		if (cantidad <= 0) {
			// TODO: Throw exception due to invalid amount
		}
		if (cantidad > producto.unidadesDisponibles() + cantidad(producto)) {
			// TODO: Throw exception due to invalid amount
		}

		// Actualiza el stock disponible de acuerdo a la nueva cantidad utilizada
		producto.modificarStock(producto.unidadesDisponibles() + cantidad(producto) - cantidad);
		productos().put(producto, cantidad);

	}

}
