package poo.uva.es.informaticafe;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;
import poo.uva.es.informaticafe.Producto;

/**
 * La clase {@link Comanda} nos permite crear nuevas comandas, que se compondra de una fecha (en formato año-mes-día),
 * un diccionario de {@code Productos} y {@code Integers} y un número entero que será el precio de la comanda.
 * Además nos permite conocer la lista de productos que la forman, sus cantidades, el importe total de la comanda,
 * modificar las cantidades de los productos, eliminar productos de las comandas y añadir un producto con su 
 * correspondiente cantidad.
 * 
 * El estado será un número entre 0 y 2, ambos inclusives, de tal manera que el 0 sea que la comanda está anulada,
 * 1 que la comanda está cerrada, y 2 que la comanda está pagada.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */

public class Comanda {

	private int estado;
	private LocalDate fecha;
	@SuppressWarnings("unused")
	private double importe;
	private HashMap<Producto, Integer> productos;

	/**
	 * Crea una comanda vacía sin atributos
	 */

	public Comanda() {
		fecha = LocalDate.now();
		productos = new HashMap<Producto, Integer>();
		importe = 0;
	}

	/**
	 * Constructor de comanda con atributos
	 * 
	 * @param estado
	 *            el estado en el que se encuentra la comanda
	 * @param fecha
	 *            la fecha en la que se pidio la comanda, tendrá la estructura año-mes-día
	 * @param importe
	 *            importe de la comanda
	 * @param productos
	 *            diccionario con los {@code Productos} y los {@code Integers}
	 * @throws IllegalArgumentException cuando el entero que indica el estado es menor que 0
	 * @throws IllegalArgumentException cuando el entero que indica el estado es mayor que 2
	 */
	public Comanda(int estado, LocalDate fecha, HashMap<Producto, Integer> productos) {
		if (estado < 0) {
			throw new IllegalArgumentException("El estado de la comanda debe ser un número entre 0 y 2");
		}
		if (estado >= 3) {
			throw new IllegalArgumentException("El estado de la comanda debe ser un número entre 0 y 2");
		}

		this.estado = estado;
		this.fecha = fecha;

		this.productos = productos;
	}

	/**
	 * Devuelve la fecha de la comanda 
	 * @return Devuelve la fecha de la comanda en formato año-mes-día
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * 
	 * Devuelve el estado de la comanda
	 * 
	 * @return devuelve un número entero que se corresponde al estado de la comanda
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 *  Cambia el estado de la comanda 
	 * @throws IllegalArgumentException cuando el entero que indica el estado es menor que 0
	 * @throws IllegalArgumentException cuando el entero que indica el estado es mayor que 2
	 * @throws IllegalArgumentException cuando el nuevo estado es igual que el que ya tenía la comanda
	 */
	public void setEstado(int estado) {
		if (estado <0) {
			throw new IllegalArgumentException("El estado de la comanda debe ser un número entre 0 y 2");
		}
		if (estado >=3 ) {
			throw new IllegalArgumentException("El estado de la comanda debe ser un número entre 0 y 2");
		}
		if (this.estado == estado) {
			throw new IllegalArgumentException("El estado de la comanda debe ser diferente al actual");
		}
		this.estado = estado;
	}

	private HashMap<Producto, Integer> productos() {
		return productos;
	}

	/**
	 * Calcula el precio total de la comanda actual.
	 * 
	 * @return Valor del importe de la comanda
	 */
	public double importe() {
		double importe = 0;

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
	 *            Cantidad de producto a introducir (0 < cantidad < stock)
	 * @throws IllegalArgumentException cuando el producto ya existe en la comanda
	 * @throws IllegalArgumentException cuando la cantidad es negativa
	 * @throws IllegalArgumentException cuando la cantidad es mayor que el stock disponible
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

		producto.reducirStock(cantidad);
		productos().put(producto, cantidad);
	}

	/**
	 * Remueve un producto de la comanda.
	 * <p>
	 * El stock del producto utilizado en la comanda se vuelve disponible de nuevo.
	 * 
	 * @param producto
	 *            producto a remover de la comanda
	 * @throws IllegalArgumentException cuando el producto no existe
	 */
	public void removeProducto(Producto producto) {
		if (!tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto debe existir.");
		}

		producto.aumentarStock(cantidad(producto)); // Devuelve el stock usado
		productos().remove(producto);

	}

	/**
	 * Dado un producto, devuelve la cantidad de unidades de ese producto asignadas
	 * a la comanda.
	 * 
	 * @param producto
	 *            producto del que se quiere comprobar la cantidad
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
	 * @param producto
	 *            producto a remover de la comanda
	 * @param cantidad
	 *            nueva cantidad de producto a usar en la comanda
	 * @throws IllegalArgumentException cuando el producto no existe en la comanda
	 * @throws IllegalArgumentException cuando la cantidad que se va a usar del producto es negativa
	 * @throws IllegalArgumentException cuando la cantidad que se pide es mayor que el stock disponible
	 */
	public void modificaProducto(Producto producto, int cantidad) {
		if (!tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto no existe en la comanda.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad no puede ser negativa.");
		}
		if (cantidad > producto.unidadesDisponibles() + cantidad(producto)) {
			throw new IllegalArgumentException("La cantidad no puede ser mayor al stock disponible.");
		}

		// Actualiza el stock disponible de acuerdo a la nueva cantidad utilizada
		producto.modificarStock(producto.unidadesDisponibles() + cantidad(producto) - cantidad);
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
