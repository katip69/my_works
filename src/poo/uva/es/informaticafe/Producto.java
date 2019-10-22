package poo.uva.es.informaticafe;

/**
 * Gestión y control de los productos.
 * 
 * @author rasero99
 *
 */
public class Producto {

	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;

	/**
	 * Constructor del {@code Producto} sin stock
	 * 
	 * @param nombre      Nombre del producto
	 * @param descripcion Descripción del producto
	 * @param precio      Precio del prodcuto
	 */
	public Producto(String nombre, String descripcion, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		modificarStock(0);
		cambiaPrecio(precio);
	}

	/**
	 * Contructor del producto con stock
	 * 
	 * @param nombre      Nombre del producto
	 * @param descripcion Descripción del producto
	 * @param precio      Precio del producto
	 * @param stock       Cantidad de productos disponible no puede ser negativo
	 */
	public Producto(String nombre, String descripcion, double precio, int stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		modificarStock(stock);
		cambiaPrecio(precio);
	}

	/**
	 * Devuelve el nombre del producto.
	 * 
	 * @return nombre del producto
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Devuelve la descripcion del producto.
	 * 
	 * @return descripcion del producto
	 */
	public String descripcion() {
		return descripcion;
	}

	/**
	 * Devuelve el precio del producto.
	 * 
	 * @return precio del producto
	 */
	public double precio() {
		return precio;
	}

	/**
	 * Cambia el precio del producto,no puede ser negativo
	 * 
	 * @param precio nuevo precio del producto
	 */

	public void cambiaPrecio(double precio) {
		if (precio < 0) {
			// TODO: Implementar excepcion
		}
		this.precio = precio;
	}

	/**
	 * Devuelve las unidades disponibles del producto.
	 * 
	 * @return cantidad de unidades disponibles
	 */
	public int unidadesDisponibles() {

		return stock;
	}

	/**
	 * Modifica el stock del producto.
	 * 
	 * El nuevo stock ha de ser mayor o igual a 0.
	 * 
	 * @param stock aumentamos su valor.
	 */
	private void modificarStock(int stock) {
		if (stock < 0) {
			// implementar excepción
		}
		this.stock = stock;

	}

	/**
	 * Aumenta el stock del producto en i unidades. i debe ser mayor que 0
	 * 
	 * @param incremento numero de unidades a aumentar.
	 */
	public void aumentarStock(int incremento) {
		if (incremento <= 0) {
			// Excepción
		}
		modificarStock(unidadesDisponibles() + incremento);
	}

	/**
	 * Reduce el stock del producto en i unidades. i debe ser mayor que 0
	 * 
	 * @param decremento número de unidades a reducir.
	 */
	public void reducirStock(int decremento) {
		if (decremento <= 0) {
			// Lanzar Excepción
		}
		if (unidadesDisponibles() - decremento < 0) {
			// Lanzar excepción.
		}
		modificarStock(unidadesDisponibles() - decremento);
	}

}
