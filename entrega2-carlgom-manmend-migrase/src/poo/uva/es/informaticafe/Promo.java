package poo.uva.es.informaticafe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Una promo es una coleccion de uno o varios productos, los cuales se venden a
 * un precio especificado.
 * 
 * Las promos tienen una fecha de inicio y fin, y solo pueden contener una unica
 * unidad de cada producto.
 * 
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public class Promo extends Vendible {

	private double precio;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private ArrayList<Producto> productos = new ArrayList<>();

	/**
	 * Constructor de una {@code Promo}
	 * 
	 * @param nombre      Nombre de la promocion
	 * @param descripcion Descripción de la promocion
	 * @param precio      Precio de la promocion (no puede ser negativo)
	 * @param fechaInicio Momento en el que la promocion se vuelve disponible
	 * @param fechaFin    Momento en el que la promocion deja de estar disponible.
	 *                    Ha de ser posterior a {@code fechaInicio}.
	 * @throws IllegalArgumentException cuando la fecha de fin de la promocion es
	 *                                  anterior a la fecha de inicio
	 */
	public Promo(String nombre, String descripcion, double precio, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		super(nombre, descripcion);

		if (fechaInicio.isAfter(fechaFin)) {
			throw new IllegalArgumentException("La fecha final ha de ser posterior a la fecha inicial.");
		}
		cambiaPrecio(precio);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	/**
	 * Cambia el precio del producto. El nuevo precio no puede ser negativo.
	 * 
	 * @param precio nuevo precio del producto
	 * @throws IllegalArgumentException cuando el precio es negativo
	 */
	public void cambiaPrecio(double precio) {
		if (precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo.");
		}
		this.precio = precio;
	}

	private ArrayList<Producto> getProductos() {
		return productos;
	}

	private LocalDateTime getFechaInicio() {
		return this.fechaInicio;
	}

	private LocalDateTime getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * Devuelve el stock restante de la promocion.
	 * 
	 * El stock es equivalente al menor stock disponible de todos los productos que
	 * forman parte de la promo.
	 * 
	 * En caso de que la promo no tenga ningun producto, su stock sera 0.
	 * 
	 * @return cantidad de promos disponibles
	 */
	@Override
	public int unidadesDisponibles() {
		if (getProductos().isEmpty()) {
			return 0;
		}
		int stockMinimo = Integer.MAX_VALUE;
		for (Producto producto : getProductos()) {
			stockMinimo = Math.min(stockMinimo, producto.unidadesDisponibles());
		}
		return stockMinimo;
	}

	/**
	 * Comprueba si un producto forma parte de la promocion
	 * 
	 * @param producto producto que se quiere comprobar si es parte de la promo
	 * @return true si el producto es parte de la promo, false en caso contrario
	 */
	public boolean tieneProducto(Producto producto) {
		return getProductos().contains(producto);
	}

	/**
	 * Inserta un {@code Producto} en la promo.
	 * 
	 * Solo puede haber una unidad de cada producto en la promocion
	 * 
	 * @param producto Producto a introducir en el combo
	 * @throws IllegalArgumentException si el Producto ya es parte del combo
	 */
	public void insertarProducto(Producto producto) {
		if (tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto ya es parte del combo");
		}

		getProductos().add(producto);
	}

	/**
	 * Elimina un {@code Producto} de la promo.
	 * 
	 * @param producto Producto a introducir en el combo
	 * @throws IllegalArgumentException si el Producto no es parte del Combo
	 */
	public void eliminaProducto(Producto producto) {
		if (!tieneProducto(producto)) {
			throw new IllegalArgumentException("El producto no es parte del combo");
		}

		getProductos().remove(producto);
	}

	@Override
	public double precio() {
		return precio;
	}

	/**
	 * Obtiene la disponibilidad de la promocion en el momento que se llama a este
	 * metodo.
	 * 
	 * @return true si la promocion esta disponible, false en caso contrario
	 */
	public boolean disponible() {
		LocalDateTime currentDate = LocalDateTime.now();
		return !(getFechaInicio().isAfter(currentDate) || getFechaFin().isBefore(currentDate));

	}

	/**
	 * Reduce en una unidad el stock de los productos que forman parte de la
	 * promocion
	 * 
	 * @param decremento numero de veces que substraer los productos de la promocion
	 * @throws IllegalArgumentException El incremento debe ser un número
	 *                                  estrictamente positivo
	 * @throws IllegalArgumentException Cuando hay más unidades a decrementar de las
	 *                                  unidades disponibles
	 */
	@Override
	public void reducirStock(int decremento) {
		if (decremento <= 0) {
			throw new IllegalArgumentException("El decremento no puede ser igual o menor a 0.");
		}
		if (unidadesDisponibles() - decremento < 0) {
			throw new IllegalArgumentException("El decremento no puede ser mayor a las unidades disponibles.");
		}

		for (int i = 0; i < decremento; i++) {
			for (Producto producto : getProductos()) {
				producto.reducirStock(1);
			}
		}
	}

	/**
	 * Devuelve un desglose de los productos que forman parte de la promocion.
	 * 
	 * @return HashMap que contiene los productos que forman parte de la promocion.
	 *         Como solo puede haber una unidad de cada producto en
	 */
	public Map<Producto, Integer> desglose() {
		HashMap<Producto, Integer> productosPromo = new HashMap<>();
		for (Producto producto : getProductos()) {
			productosPromo.put(producto, 1);
		}

		return productosPromo;

	}
}
