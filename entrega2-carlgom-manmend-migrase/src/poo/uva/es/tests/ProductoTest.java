package poo.uva.es.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import poo.uva.es.informaticafe.Producto;

/**
 * Coleccion de tests para la clase Producto
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class ProductoTest {
	private static final String NOMBRE = "Nombre del producto";
	private static final String DESCRIPCION = "Descripcion generica del producto";

	@Test
	public void productoValido() {
		double precio = 0.56;
		int cantidad = 5;

		Producto manzana = new Producto(NOMBRE, DESCRIPCION, precio, cantidad);

		assertEquals(NOMBRE, manzana.nombre());
		assertEquals(DESCRIPCION, manzana.descripcion());
		// Asumimos que un producto no tendra una precision por debajo de 1 centimo
		assertEquals(precio, manzana.precio(), 0.001);
		assertEquals(cantidad, manzana.unidadesDisponibles());
	}

	@Test
	public void nuevoProductoSinStock() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56);

		assertEquals(0, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoProductoConPrecioInvalido() {
		new Producto(NOMBRE, DESCRIPCION, -0.56);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoProductoConStockInvalido() {
		new Producto(NOMBRE, DESCRIPCION, 0.56, -3);
	}

	@Test
	public void nuevoPrecio() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56);
		manzana.cambiaPrecio(0.78);

		assertEquals(0.78, manzana.precio(), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoPrecioInvalido() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56);
		manzana.cambiaPrecio(-0.78);
	}

	@Test
	public void nuevoStock() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.modificarStock(3);

		assertEquals(3, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoStockInvalido() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.modificarStock(-1);
	}

	@Test
	public void aumentaStock() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.aumentarStock(3);

		assertEquals(7, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void aumentaStockInvalido() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.aumentarStock(0);
	}

	@Test
	public void reduceStock() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.reducirStock(3);

		assertEquals(1, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void reduceStockInvalido() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.reducirStock(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reduceStockSinDisponibilidad() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 0.56, 4);
		manzana.reducirStock(5);
	}

}
