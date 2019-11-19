package poo.uva.es.informaticafe;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;
import poo.uva.es.informaticafe.Producto;

/**
 * La clase {@link Comanda} nos permite crear nuevas comandas, que se compondra
 * de una fecha (en formato año-mes-día), un diccionario de {@code Productos} y
 * {@code Integers} y un número entero que será el precio de la comanda. Además
 * nos permite conocer la lista de productos que la forman, sus cantidades, el
 * importe total de la comanda, modificar las cantidades de los productos,
 * eliminar productos de las comandas y añadir un producto con su
 * correspondiente cantidad.
 * 
 * El estado de la comanda sera un int entre 0 y 3. (0 = abierto, 1 = cerrado, 2
 * = pagado, 3 = anulado)
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */

public class Comanda {

	public enum estado{ABIERTO,CERRADO,PAGADO,ANULADO};
	private estado estados;
	private LocalDateTime fecha;
	private HashMap<Producto, Integer> productos;

	/**
	 * Crea una comanda vacía sin atributos
	 */

	public Comanda() {
		fecha = LocalDateTime.now();
		productos = new HashMap<Producto, Integer>();
		estados=estado.ABIERTO;
	}

	/**
	 * Devuelve la fecha de la comanda
	 * 
	 * @return fecha de la comanda
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * 
	 * Devuelve el estado de la comanda
	 * 
	 * @return devuelve un número entero que se corresponde al estado de la comanda
	 */
	public int getEstado() {
		return estados.ordinal();
	}

	/**
	 * Cambia el estado de la comanda
	 * 
	 * @throws IllegalArgumentException cuando el entero que indica el estado es
	 *                                  menor que 0 o mayor que 2
	 * @throws IllegalArgumentException si se intenta cambiar el estado de una
	 *                                  comanda ya pagada
	 * @throws IllegalArgumentException cuando el nuevo estado es igual que el que
	 *                                  ya tenía la comanda
	 */
	public void setEstado(estado estados) {
		if (estados.ordinal() < 0 || estados.ordinal() > 3) {
			throw new IllegalArgumentException("El estado de la comanda debe ser un número entre 0 y 2");
		}

		if (this.estados.ordinal() == 2) {
			throw new IllegalArgumentException("Una comanda pagada no puede ser modificada.");
		}

		if (this.estados == estados) {
			throw new IllegalArgumentException("El estado de la comanda debe ser diferente al actual");
		}

		if (estados.ordinal() == 2) {
			sirveProductos();
		}

		this.estados = estados;
	}

	private void sirveProductos() {
		for (Map.Entry<Producto, Integer> par : productos().entrySet()) {
			if (par.getKey().unidadesDisponibles() < par.getValue()) {
				throw new IllegalArgumentException("No se puede vender una comanda sin stock suficiente");
			}
			
			par.getKey().reducirStock(par.getValue());
		}

	}

	private HashMap<Producto, Integer> productos() {
		return productos;
	}

	/**
	 * Calcula el precio total de la comanda actual.
	 * 
	 * @return Valor del importe de Ila comanda
	 */
	public double importe() {
		double importe = 0;

		if (getEstado() == 3) {
			return importe;
		}

		// Itera sobre los pares HashMap creando un Map individual por cada par
		for (Map.Entry<Producto, Integer> par : productos().entrySet()) {
			// Suma el precio de cada producto por la cantidad al importe
			importe += par.getKey().precio() * par.getValue();
		}

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
	 * @throws IllegalArgumentException cuando el producto ya existe en la comanda
	 * @throws IllegalArgumentException cuando la cantidad es negativa
	 * @throws IllegalArgumentException cuando la cantidad es mayor que el stock
	 *                                  disponible
	 */
	public void addProducto(Producto producto, int cantidad) {
		if (tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto ya existe en la comanda.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad no puede ser negativa.");
		}
		if (cantidad > producto.unidadesDisponibles()) {
			throw new IllegalArgumentException("La cantidad no puede ser mayor al stock disponible.");
		}

		productos().put(producto, cantidad);
	}

	/**
	 * Remueve un producto de la comanda.
	 * <p>
	 * El stock del producto utilizado en la comanda se vuelve disponible de nuevo.
	 * 
	 * @param producto producto a remover de la comanda
	 * @throws IllegalArgumentException cuando el producto no existe
	 */
	public void removeProducto(Producto producto) {
		if (!tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto debe existir.");
		}

		productos().remove(producto);

	}

	/**
	 * Dado un producto, devuelve la cantidad de unidades de ese producto asignadas
	 * a la comanda.
	 * 
	 * @param producto producto del que se quiere comprobar la cantidad
	 * @return cantidad de producto en la comanda
	 * @throws IllegalArgumentException cuando el producto no existe en la comanda
	 */
	public int cantidad(Producto producto) {
		if (!tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto no existe en la comanda.");
		}

		return productos().get(producto);
	}

	/**
	 * Modifica la cantidad de un producto asignada a la comanda.
	 * <p>
	 * 
	 * @param producto producto a remover de la comanda
	 * @param cantidad nueva cantidad de producto a usar en la comanda
	 * @throws IllegalArgumentException cuando el producto no existe en la comanda
	 * @throws IllegalArgumentException cuando la cantidad que se va a usar del
	 *                                  producto es negativa
	 * @throws IllegalArgumentException cuando la cantidad que se pide es mayor que
	 *                                  el stock disponible
	 */
	public void modificaProducto(Producto producto, int cantidad) {
		if (!tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto no existe en la comanda.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad no puede ser negativa.");
		}
		if (cantidad > producto.unidadesDisponibles()) {
			throw new IllegalArgumentException("La cantidad no puede ser mayor al stock disponible.");
		}

		productos().put(producto, cantidad);

	}

	/**
	 * Comprueba si la comanda esta vacia.
	 * 
	 * @return true si la comanda no tiene productos, false en caso contrario.
	 */
	public boolean vacia() {
		return productos().isEmpty();
	}

}
