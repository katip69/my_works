package poo.uva.es.informaticafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Un combo es una coleccion de multiples {@code Producto}. El precio de un
 * combo es la suma del precio de todos los productos individuales del combo,
 * con un 10% de descuento sobre el total.
 * 
 * El stock esta determinado por el stock de los productos que forman parte del
 * combo.
 * 
 * Un combo puede contener uno o varios productos, y puede contener el mismo
 * producto multiples veces.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public class Combo extends Vendible {

	private HashMap<Producto, Integer> productos = new HashMap<>();

	/**
	 * Constructor de un {@code Combo}.
	 * 
	 * @param nombre      Nombre del combo
	 * @param descripcion Descripción del combo
	 */
	public Combo(String nombre, String descripcion) {
		super(nombre, descripcion);
	}

	private HashMap<Producto, Integer> getProductos() {
		return productos;
	}

	/**
	 * Inserta un {@code Producto} en el combo.
	 * 
	 * En caso de que el producto ya existiera en el combo, se incrementa el numero
	 * de unidades de dicho producto en el combo.
	 * 
	 * @param producto Producto a introducir en el combo
	 */
	public void insertarProducto(Producto producto) {
		insertarProducto(producto, 1);
	}

	/**
	 * Inserta un numero especifico de unidades de un {@code Producto} en el combo.
	 *
	 * En caso de que el producto ya existiera en el combo, se incrementara la
	 * cantidad de unidades del producto en el combo en la cantidad especificada.
	 * 
	 * @param producto Producto a introducir en el combo
	 * @param cantidad Cantidad de unidades a introducir en el combo (min. 1)
	 * @throws IllegalArgumentException cuando la cantidad es menor a 1
	 */
	public void insertarProducto(Producto producto, int cantidad) {
		if (cantidad < 1) {
			throw new IllegalArgumentException("La cantidad no puede ser menor a 1");
		}
		if (!getProductos().containsKey(producto)) {
			getProductos().put(producto, 0);
		}
		modificaCantidad(producto, cantidad + getProductos().get(producto));
	}

	/**
	 * Elimina un {@code Producto} del combo.
	 * 
	 * @param producto Producto a introducir en el combo
	 * @throws IllegalArgumentException cuando la cantidad es menor a 1
	 */
	public void eliminaProducto(Producto producto) {

		if (!getProductos().containsKey(producto)) {
			throw new IllegalArgumentException("El producto no forma parte del Combo");
		}
		getProductos().remove(producto);
	}

	/**
	 * Modifica la cantidad de un {@code Producto} en el combo.
	 * 
	 * @param producto Producto a introducir en el combo
	 * @param cantidad Nueva cantidad de unidades en el combo (min. 1)
	 * @throws IllegalArgumentException cuando el producto no es parte del combo
	 * @throws IllegalArgumentException cuando la cantidad es menor a 1
	 */
	public void modificaCantidad(Producto producto, int cantidad) {
		if (cantidad < 1) {
			throw new IllegalArgumentException("La cantidad no puede ser menor a 1");
		}
		if (!getProductos().containsKey(producto)) {
			throw new IllegalArgumentException("El producto no forma parte de este combo");
		}
		getProductos().put(producto, cantidad);
	}

	/**
	 * Devuelve el importe total del combo.
	 * 
	 * El importe es equivalente al 90% del precio total de los productos que forman
	 * parte del combo.
	 * 
	 * @return importe total del combo
	 */
	@Override
	public double precio() {
		double importeTotal = 0;
		for (Map.Entry<Producto, Integer> producto : getProductos().entrySet()) {
			importeTotal += producto.getKey().precio() * producto.getValue();
		}
		return importeTotal * 0.9;
	}

	/**
	 * Devuelve el stock restante de combos.
	 * 
	 * @return cantidad de combos disponibles
	 */
	@Override
	public int unidadesDisponibles() {
		if (getProductos().isEmpty()) {
			return 0;
		}
		int stockMinimo = Integer.MAX_VALUE;
		for (Map.Entry<Producto, Integer> producto : getProductos().entrySet()) {
			stockMinimo = Math.min(stockMinimo, (int) (producto.getKey().unidadesDisponibles() / producto.getValue()));
		}
		return stockMinimo;
	}

	/**
	 * Comprueba si el combo no tiene productos.
	 * 
	 * @return true si el combo esta vacio, false en caso contrario
	 */
	public boolean vacio() {
		return getProductos().isEmpty();
	}

	/**
	 * Reduce el stock de los productos que forman parte del combo.
	 * 
	 * El numero de unidades reducidas de cada producto es equivalente al numero de
	 * unidades de ese producto que han sido introducidas en el combo.
	 * 
	 * @param decremento numero de veces que substraer las unidades de combo de los
	 *                   productos
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
			for (Map.Entry<Producto, Integer> par : getProductos().entrySet()) {
				par.getKey().reducirStock(par.getValue());
			}
		}
	}

	/**
	 * Devuelve un desglose de los productos que forman parte del combo, y cuantas
	 * unidades son parte de este.
	 * 
	 * @return Map con todos los productos que forman parte del combo, y cuantas
	 *         unidades de cada uno entran en el combo.
	 */
	@Override
	public Map<Producto, Integer> desglose() {
		return new HashMap<>(getProductos());
	}
}
