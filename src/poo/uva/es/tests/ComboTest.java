package poo.uva.es.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import poo.uva.es.informaticafe.*;

/**
 * Coleccion de tests para la clase Producto
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class ComboTest {
	private final String NOMBRE = "McCombo";
	private final String DESCRIPCION = "DESCRIPCION generica de McCombo";

	@Test
	public void comboVacio() {
		Combo comboVacio = new Combo(NOMBRE, DESCRIPCION);

		assertEquals(NOMBRE, comboVacio.nombre());
		assertEquals(DESCRIPCION, comboVacio.descripcion());
		// Asumimos que un producto no tendra una precision por debajo de 1 centimo
		assertEquals(0, comboVacio.precio(), 0.001);
		assertEquals(0, comboVacio.unidadesDisponibles());
		assertTrue(comboVacio.vacio());
	}

	@Test
	public void comboConProducto() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger);
		assertEquals(3 * 0.9, combo.precio(), 0.001);
		assertEquals(200, combo.unidadesDisponibles());
		assertFalse(combo.vacio());
	}

	@Test
	public void comboConMismoProductoMultiplesVeces() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger, 3);

		assertEquals(3 * 3 * 0.9, combo.precio(), 0.001);
		assertEquals((int) (200 / 3), combo.unidadesDisponibles());
	}

	@Test
	public void comboConDistintoProducto() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);
		// Producto a 2.4 euros (350 ud.)
		Producto burger = new Producto("Burger", "Hamburguesa sin nada", 2.4, 350);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger);
		combo.insertarProducto(burger);

		assertEquals((3 + 2.4) * 0.9, combo.precio(), 0.001);
		assertEquals(200, combo.unidadesDisponibles());
	}

	@Test
	public void comboConDistintoProductoMultiplesUnidades() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);
		// Producto a 2.4 euros (350 ud.)
		Producto burger = new Producto("Burger", "Hamburguesa sin nada", 2.4, 350);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger, 1);
		combo.insertarProducto(burger, 2);

		assertEquals((3 + 2.4 * 2) * 0.9, combo.precio(), 0.001);
		assertEquals((int) (350 / 2), combo.unidadesDisponibles());
	}

	@Test
	public void incrementaCantidadProducto() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger);
		combo.insertarProducto(cheeseburger, 3);
		assertEquals(3 * 4 * 0.9, combo.precio(), 0.001);
		assertEquals((int) (200 / 4), combo.unidadesDisponibles());
	}

	@Test
	public void eliminaProductoDeCombo() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger);
		combo.eliminaProducto(cheeseburger);

		assertEquals(0, combo.precio(), 0.001);
		assertEquals(0, combo.unidadesDisponibles());
		assertTrue(combo.vacio());
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminaProductoInexistente() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.eliminaProducto(cheeseburger);
	}

	@Test
	public void eliminaProductoConMultiplesUnidadesDeCombo() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger, 5);
		combo.eliminaProducto(cheeseburger);

		assertEquals(0, combo.precio(), 0.001);
		assertEquals(0, combo.unidadesDisponibles());
		assertTrue(combo.vacio());
	}

	@Test
	public void modificaUnidadesProducto() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger, 3);
		combo.modificaCantidad(cheeseburger, 5);

		assertEquals(3 * 5 * 0.9, combo.precio(), 0.001);
		assertEquals((int) (200 / 5), combo.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaUnidadesProductoInvalido() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger, 3);
		combo.modificaCantidad(cheeseburger, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaUnidadesProductoInexistente() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.modificaCantidad(cheeseburger, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void insertarProductoConCantidadInvalida() {

		// Producto a 3 euros (200 ud.)
		Producto cheeseburger = new Producto("Cheeseburger", "Hamburguesa con queso", 3, 200);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		combo.insertarProducto(cheeseburger, 0);

	}
}