package poo.uva.es.informaticafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Un producto representa un item especifico e individual.
 * 
 * Cada producto tiene un precio predeterminado, el cual puede ser cambiado en
 * el futuro. Los productos tambien implementan multiples metodos para manipular
 * su stock.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public class Producto extends Vendible {

	private double precio;
	private int stock;

	/**
	 * Constructor de {@code Producto}. El stock del producto es 0.
	 * 
	 * @param nombre      Nombre del producto
	 * @param descripcion Descripción del producto
	 * @param precio      Precio del prodcuto ({@code precio >= 0})
	 */
	public Producto(String nombre, String descripcion, double precio) {
		super(nombre, descripcion);
		cambiaPrecio(precio);
		stock = 0;
	}

	/**
	 * Constructor de {@code Producto}.
	 * 
	 * @param nombre      Nombre del producto
	 * @param descripcion Descripción del producto
	 * @param precio      Precio del producto ({@code precio >= 0})
	 * @param stock       Cantidad de productos disponible ({@code stock >= 0})
	 */
	public Producto(String nombre, String descripcion, double precio, int stock) {
		super(nombre, descripcion);
		cambiaPrecio(precio);
		modificarStock(stock);
	}

	/**
	 * Modifica el stock del producto.
	 * 
	 * El nuevo stock ha de ser mayor o igual a 0.
	 * 
	 * @param stock nuevo numero de unidades disponibles del producto
	 * @throws IllegalArgumentException si el stock provisto es negativo
	 */
	public void modificarStock(int stock) {
		if (stock < 0) {
			throw new IllegalArgumentException("El nuevo stock no puede ser negativo.");
		}
		this.stock = stock;

	}

	/**
	 * Aumenta el stock del producto en i unidades. i debe ser mayor que 0
	 * 
	 * @param incremento numero de unidades a aumentar.
	 * @throws IllegalArgumentException El incremento tiene que ser un número
	 *                                  estrictamente positivo
	 */
	public void aumentarStock(int incremento) {
		if (incremento <= 0) {
			throw new IllegalArgumentException("El incremento no puede ser menor o igual a 0.");
		}
		modificarStock(unidadesDisponibles() + incremento);
	}

	/**
	 * Reduce el stock del producto en i unidades. i debe ser mayor que 0
	 * 
	 * @param decremento número de unidades a reducir.
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
		modificarStock(unidadesDisponibles() - decremento);
	}

	/**
	 * Cambia el precio del producto, no puede ser negativo
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

	/**
	 * Devuelve el precio del producto
	 * 
	 * @return precio del producto
	 */
	@Override
	public double precio() {
		return precio;
	}

	/**
	 * Devuelve el stock del producto
	 * 
	 * @return stock del producto
	 */
	@Override
	public int unidadesDisponibles() {
		return stock;
	}

	/**
	 * Devuelve un desglose del producto.
	 * 
	 * @return HashMap que contiene el producto, con una unica unidad de si mismo.
	 */
	public Map<Producto, Integer> desglose() {
		HashMap<Producto, Integer> producto = new HashMap<>();

		producto.put(this, 1);

		return producto;

	}
}
