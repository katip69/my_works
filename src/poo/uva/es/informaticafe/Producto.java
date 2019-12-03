package poo.uva.es.informaticafe; 



/**
 * Gestión y control de los producto, los cuales tendran 4 parametros, un nombre, su descripción, un precio 
 * en formato double, y la cantidad de unidades disponibles que hay de ese producto
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
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
	 * @param precio      Precio del prodcuto (no puede ser negativo)
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
	 * @param stock       Cantidad de productos disponible (no puede ser negativo)
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
	 * Devuelve la descripcin del producto.
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
	 * Cambia el precio del producto, no puede ser negativo
	 * 
	 * @param precio nuevo precio del producto
	 */

	public void cambiaPrecio(double precio) {
		if (precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo.");
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
	 * @param stock cantidad de unidades del producto a aumentar
	 * @throws IllegalArgumentException cuando el nuevo stock que queremos poner es negativo
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
	 * @throws IllegalArgumentException El incremento tiene que ser un número estrictamente positivo
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
	 * @throws IllegalArgumentException El incremento debe ser un número estrictamente positivo
	 * @throws IllegalArgumentException Cuando hay más unidades a decrementar de las unidades disponibles
	 */
	public void reducirStock(int decremento) {
		if (decremento <= 0) {
			throw new IllegalArgumentException("El decremento no puede ser igual o menor a 0.");
		}
		if (unidadesDisponibles() - decremento < 0) {
			throw new IllegalArgumentException("El decremento no puede ser mayor a las unidades disponibles.");
		}
		modificarStock(unidadesDisponibles() - decremento);
	}

}