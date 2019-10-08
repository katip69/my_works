package poo.uva.es.informaticafe;

/**
 * La clase {@link Almacen} es una implementacion de un sistema de inventario.
 * <p>
 * Permite la gestion de productos y las cantidades disponibles de estos.
 * 
 * @author Carlos Gomez
 * @version 1.0
 */
public class Almacen {

	// TODO: Replace void type with proper one (Map, HashMap, ArrayList...)
	private Void inventario;

	/**
	 * Constructor por defecto de la clase {@link Almacen}. Genera un inventario
	 * vacio.
	 */
	public Almacen() {
		// TODO: Implement constructor Almacen()
	}

	/**
	 * Crea una instancia del producto en el almacen, con un stock de 0 unidades.
	 * 
	 * @param producto Nombre del producto que se quiere inventariar
	 */
	public void creaProducto(String producto) {
		// TODO: Implement creaProducto()
	}

	/**
	 * Crea una instancia del producto en el almacen, con un stock especificado.
	 * 
	 * @param producto   Nombre del producto que se quiere inventariar
	 * @param inventario Cantidad de producto que se quiere inicializar
	 */
	public void creaProducto(String producto, int inventario) {
		// TODO: Implement creaProducto()
	}

	/**
	 * Incrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere aumentar stock
	 * @param stock    cantidad a aumentar (ha de ser mayor que 0)
	 */
	public void incrementarStock(String producto, int stock) {
		// TODO:Implement incrementarStock()
	}

	/**
	 * Decrementa la cantidad de un producto en el almacen.
	 * 
	 * 
	 * @param producto Producto del que se quiere remover stock
	 * @param stock    cantidad a reducir (ha de ser mayor que 0)
	 */
	public void removerStock(String producto, int stock) {
		// TODO: Implement removerStock()
	}

	/**
	 * Elimina un producto del almacen.
	 * 
	 * @param producto Nombre del producto a eliminar
	 */
	public void eliminar(String producto) {
		// TODO: Implement eliminar()
	}

	/**
	 * Comprueba la existencia de un producto en el almacen.
	 * 
	 * @param producto Producto del que se quiere comprobar si existe en el almacen
	 * @return true si el producto existe en el almacen, false en caso contrario
	 */
	public boolean existe(String producto) {
		// TODO: Implement existe()
		return true;
	}

	/**
	 * Obtiene la cantidad en stock de un producto en el almacen.
	 * 
	 * @param producto Producto del que se quiere comprobar el stock
	 * @return Cantidad de stock disponible del producto
	 */
	public int cantidad(String producto) {
		// TODO: Implement cantidad()
		return 0;
	}
}
