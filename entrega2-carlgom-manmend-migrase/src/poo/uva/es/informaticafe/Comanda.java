package poo.uva.es.informaticafe;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Una comanda representa un pedido hecho por un cliente, el cual esta formado
 * por una coleccion de {@link Vendible}.
 * 
 * Las comandas tienen distintos {@link Estados} dependiendo de en que fase
 * esten siendo procesados.
 * 
 * Toda comanda tiene un importe total, y guarda la fecha en la que fue creada.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public abstract class Comanda {

	private Estados estado;
	private LocalDateTime fecha;
	private HashMap<Vendible, Integer> vendibles;
	private double importe;

	/**
	 * Constructor de comanda
	 */
	public Comanda() {
		fecha = LocalDateTime.now();
		vendibles = new HashMap<>();
		estado = Estados.ABIERTO;
		importe = 0;
	}

	/**
	 * Devuelve la fecha en la que fue creada la comanda
	 * 
	 * @return fecha de creacion de la comanda
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
	 * Cambia el estado de la comanda.
	 * 
	 * No es posible cambiar el estado de una comanda que hubiera sido pagada o
	 * anulada.
	 * 
	 * En caso de que la comanda pase a estar pagada, se procedera a servir los
	 * productos que forman parte de la comanda, retirando el stock del almacen.
	 * 
	 * @param estado Nuevo estado al que queremos cambiar nuestra comanda
	 * 
	 * @throws IllegalArgumentException si se intenta cambiar el estado de una
	 *                                  comanda ya pagada
	 * @throws IllegalArgumentException si se intenta cambiar el estado de una
	 *                                  comanda anulada
	 * @throws IllegalArgumentException cuando el nuevo estado es igual que el que
	 *                                  ya tenía la comanda
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
			sirveVendibles();
		}
		this.estado = estado;
	}

	/**
	 * Obtiene el importe total de la comanda.
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
		for (Map.Entry<Vendible, Integer> par : consultaVendibles().entrySet()) {
			// Suma el precio de cada Vendible por la cantidad al importe
			dinero += par.getKey().precio() * par.getValue();
		}

		return dinero;
	}

	private void sirveVendibles() {
		for (Map.Entry<Vendible, Integer> par : consultaVendibles().entrySet()) {
			if (par.getKey().unidadesDisponibles() < par.getValue()) {
				throw new IllegalArgumentException("No se puede vender una comanda sin stock suficiente");
			}

			par.getKey().reducirStock(par.getValue());
		}

	}

	private HashMap<Vendible, Integer> consultaVendibles() {
		return vendibles;
	}

	/**
	 * Comprueba si un {@link Vendible} existe en la comanda
	 * 
	 * @param vendible Vendible a comprobar su existencia
	 * @return true si existe en la comanda, false en caso contrario
	 */
	public boolean tieneVendible(Vendible vendible) {

		return consultaVendibles().containsKey(vendible);
	}

	/**
	 * Introduce un nuevo {@link Vendible} a la comanda.
	 * 
	 * @param vendible Vendible a introducir en la comanda
	 * @param cantidad Cantidad de Vendible a introducir {@code 0 < cantidad <
	 *                 stock}
	 * @throws IllegalArgumentException cuando el Vendible ya existe en la comanda
	 * @throws IllegalArgumentException cuando la cantidad es negativa
	 * @throws IllegalArgumentException cuando la cantidad es mayor que el stock
	 *                                  disponible
	 * @throws IllegalArgumentException si se intenta introducir una promocion no
	 *                                  disponible en la comanda
	 */
	public void addVendible(Vendible vendible, int cantidad) {
		if (tieneVendible(vendible)) {
			throw new IllegalArgumentException("El Vendible ya existe en la comanda.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad no puede ser negativa.");
		}
		if (vendible instanceof Promo && !((Promo) vendible).disponible()) {
			throw new IllegalArgumentException("No se puede introducir una promocion no disponible en la comanda.");
		}
		for (Map.Entry<Producto, Integer> par : vendible.desglose().entrySet()) {
			if (cantidad * par.getValue() + cantidadProductoTotal(par.getKey()) > par.getKey().unidadesDisponibles()) {
				throw new IllegalArgumentException(
						"No hay stock suficiente del producto para introducir en la comanda.");
			}
		}
		if (cantidad > vendible.unidadesDisponibles()) {
			throw new IllegalArgumentException("La cantidad no puede ser mayor al stock disponible.");
		}

		consultaVendibles().put(vendible, cantidad);
	}

	private int cantidadProductoTotal(Producto producto) {
		int cantidad = 0;
		for (Map.Entry<Vendible, Integer> parVendible : consultaVendibles().entrySet()) {
			for (Map.Entry<Producto, Integer> parProducto : parVendible.getKey().desglose().entrySet()) {
				if (producto == parProducto.getKey()) {
					cantidad += parProducto.getValue() * parVendible.getValue();
				}
			}
		}
		return cantidad;
	}

	/**
	 * Remueve un {@link Vendible} de la comanda.
	 * 
	 * @param vendible vendible a remover de la comanda
	 * @throws IllegalArgumentException si el vendible no forma parte de la comanda
	 */
	public void removeVendible(Vendible vendible) {
		if (!tieneVendible(vendible)) {
			throw new IllegalArgumentException("El vendible debe existir en la comanda.");
		}

		consultaVendibles().remove(vendible);

	}

	/**
	 * Dado un vendible, devuelve la cantidad de unidades de ese Vendible asignadas
	 * a la comanda.
	 * 
	 * @param vendible vendible del que se quiere comprobar la cantidad
	 * @return cantidad de Vendible en la comanda
	 * @throws IllegalArgumentException cuando el vendible no existe en la comanda
	 */
	public int cantidad(Vendible vendible) {
		if (!tieneVendible(vendible)) {
			throw new IllegalArgumentException("El Vendible no existe en la comanda.");
		}

		return consultaVendibles().get(vendible);
	}

	/**
	 * Modifica la cantidad de un vendible asignada a la comanda.
	 * <p>
	 * 
	 * @param vendible vendible a remover de la comanda
	 * @param cantidad nueva cantidad de Vendible a usar en la comanda
	 * @throws IllegalArgumentException cuando el Vendible no existe en la comanda
	 * @throws IllegalArgumentException cuando la cantidad que se va a usar del
	 *                                  vendible es negativa
	 * @throws IllegalArgumentException cuando la cantidad que se pide es mayor que
	 *                                  el stock disponible
	 */
	public void modificaVendible(Vendible vendible, int cantidad) {
		if (!tieneVendible(vendible)) {
			throw new IllegalArgumentException("El Vendible no existe en la comanda.");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad no puede ser negativa.");
		}
		if (cantidad > vendible.unidadesDisponibles()) {
			throw new IllegalArgumentException("La cantidad no puede ser mayor al stock disponible.");
		}

		consultaVendibles().put(vendible, cantidad);

	}

	/**
	 * Comprueba si la comanda esta vacia.
	 * 
	 * @return true si la comanda no tiene vendibles, false en caso contrario.
	 */
	public boolean vacia() {
		return consultaVendibles().isEmpty();
	}
}
