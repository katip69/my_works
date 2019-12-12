package poo.uva.es.informaticafe;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class Comanda {

	private Estados estado;
	private LocalDateTime fecha;
	private HashMap<Producto, Integer> productos;
	private double importe;

	/**
	 * Crea una comanda vacía sin atributos
	 */

	public Comanda() {
		fecha = LocalDateTime.now();
		productos = new HashMap<>();
		estado = Estados.ABIERTO;
		importe = 0;
	}

	/**
	 * Devuelve la fecha de la comanda
	 * 
	 * @return fecha de la comanda
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	private void setImporte(double importe) {
		this.importe = importe;
	}

	private double getImporte() {
		return importe;
	}

	/**
	 * 
	 * Devuelve el estado de la comanda
	 * 
	 * @return devuelve el estado actual de la comanda
	 */
	public Estados getEstado() {
		return estado;
	}

	/**
	 * Cambia el estado de la comanda
	 * 
	 * @param estado
	 *            Nuevo estado al que queremos cambiar nuestra comanda
	 * 
	 * @throws IllegalArgumentException
	 *             si se intenta cambiar el estado de una comanda ya pagada
	 * @throws IllegalArgumentException
	 *             si se intenta cambiar el estado de una comanda anulada
	 * @throws IllegalArgumentException
	 *             cuando el nuevo estado es igual que el que ya tenía la comanda
	 */
	public void setEstado(Estados estado) {

		if (getEstado() == Estados.PAGADO) {
			throw new IllegalArgumentException("Una comanda pagada no puede ser modificada.");
		}

		if (getEstado() == Estados.ANULADO) {
			throw new IllegalArgumentException("Una comanda anulada no puede ser modificada.");
		}

		if (getEstado() == estado) {
			throw new IllegalArgumentException("El estado de la comanda debe ser diferente al actual");
		}

		if (estado == Estados.PAGADO) {
			setImporte(importe());
			sirveProductos();
		}
		this.estado = estado;
	}

	/**
	 * Calcula el precio total de la comanda actual.
	 * 
	 * @return Valor del importe de una comanda
	 */
	public double importe() {
		double dinero = 0;

		if (getEstado() == Estados.ANULADO) {
			return dinero;
		}

		if (getEstado() == Estados.PAGADO) {
			return getImporte();
		}

		// Itera sobre los pares HashMap creando un Map individual por cada par
		for (Map.Entry<Producto, Integer> par : productos().entrySet()) {
			// Suma el precio de cada producto por la cantidad al importe
			dinero += par.getKey().precio() * par.getValue();
		}

		return dinero;
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
	 * Comprueba si un producto espeficidado existe en la comanda
	 * 
	 * @param producto
	 *            Producto a comprobar su existencia
	 * @return true si existe en la comanda, false en caso contrario
	 */
	public boolean tieneProducto(Producto producto) {

		return productos().containsKey(producto);
	}

	/**
	 * Introduce un nuevo producto a la comanda.
	 * 
	 * @param producto
	 *            Producto a introducir en la comanda
	 * @param cantidad
	 *            Cantidad de producto a introducir {@code(0 < cantidad < stock)}
	 * @throws IllegalArgumentException
	 *             cuando el producto ya existe en la comanda
	 * @throws IllegalArgumentException
	 *             cuando la cantidad es negativa
	 * @throws IllegalArgumentException
	 *             cuando la cantidad es mayor que el stock disponible
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
	 * @param producto
	 *            producto a remover de la comanda
	 * @throws IllegalArgumentException
	 *             cuando el producto no existe
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
	 * @param producto
	 *            producto del que se quiere comprobar la cantidad
	 * @return cantidad de producto en la comanda
	 * @throws IllegalArgumentException
	 *             cuando el producto no existe en la comanda
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
	 * @param producto
	 *            producto a remover de la comanda
	 * @param cantidad
	 *            nueva cantidad de producto a usar en la comanda
	 * @throws IllegalArgumentException
	 *             cuando el producto no existe en la comanda
	 * @throws IllegalArgumentException
	 *             cuando la cantidad que se va a usar del producto es negativa
	 * @throws IllegalArgumentException
	 *             cuando la cantidad que se pide es mayor que el stock disponible
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
