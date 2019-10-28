package poo.uva.es.tests;

import java.util.HashMap;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Comanda;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Coleccion de tests para la clase Comanda
 * 
 * @author carlgom
 * @author migrase
 * @author manmend
 */
public class ComandaTest {

	@Test
	public void comandaValida() {
		Comanda comanda = new Comanda();
		assertEquals(0, comanda.productos().size());
		assertEquals(0, comanda.importe(), 0.001);
	}

	@Test
	public void comandaValidaDesdeHashMap() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();

		productos.put(new Producto("Manzana", "", 2.3), 1);
		productos.put(new Producto("Pera", "", 2.1), 3);
		productos.put(new Producto("Platano", "", 1.7), 5);

		Comanda comanda = new Comanda(productos);
		assertEquals(productos.size(), comanda.productos().size());
		double precioEsperado = 2.3 + 3 * 2.1 + 5 * 1.7;
		assertEquals(precioEsperado, comanda.importe(), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void hashMapConCantidadesInvalidas() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();

		productos.put(new Producto("Manzana", "", 2.3), -1);
		productos.put(new Producto("Pera", "", 2.1), -3);
		productos.put(new Producto("Platano", "", 1.7), -5);

		Comanda comanda = new Comanda(productos);
	}

	@Test(expected = IllegalArgumentException.class)
	public void hashMapSinStock() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();

		productos.put(new Producto("Manzana", "", 2.3, 1), 1);
		productos.put(new Producto("Pera", "", 2.1, 2), 3);
		productos.put(new Producto("Platano", "", 1.7, 3), 5);

		Comanda comanda = new Comanda(productos);
	}

	@Test
	public void tieneProducto() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto manzana = new Producto("Manzana", "", 2.3);

		productos.put(manzana, 1);

		Comanda comanda = new Comanda(productos);

		assertTrue(comanda.tieneProducto(manzana));
	}

	@Test
	public void noTieneProducto() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto manzana = new Producto("Manzana", "", 2.3);
		Producto pera = new Producto("Pera", "", 2.1);

		productos.put(manzana, 1);

		Comanda comanda = new Comanda(productos);

		assertFalse(comanda.tieneProducto(pera));
	}

	@Test
	public void addProductoValido() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda(productos);

		comanda.addProducto(pera, 3);

		assertTrue(comanda.tieneProducto(pera));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoInvalido() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda(productos);

		comanda.addProducto(pera, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoExistente() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto manzana = new Producto("Manzana", "", 2.3, 5);

		productos.put(manzana, 1);

		Comanda comanda = new Comanda(productos);

		comanda.addProducto(manzana, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoSinStockSuficiente() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, 8);
	}

	@Test
	public void removeProductoValido() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, 3);

		comanda.removeProducto(pera);

		assertFalse(comanda.tieneProducto(pera));
		assertEquals(5, pera.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeProductoInexistente() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.removeProducto(pera);

	}

	@Test
	public void modificaProductoValido() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda(productos);

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, 4);

		assertTrue(comanda.tieneProducto(pera));
		assertEquals(1, pera.unidadesDisponibles());
		assertEquals(4, comanda.cantidad(pera));

	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInnvalido() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda(productos);

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInexistente() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto manzana = new Producto("Manzana", "", 2.3, 5);

		Comanda comanda = new Comanda();

		comanda.modificaProducto(manzana, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoSinStockSuficiente() {
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda(productos);

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, 6);
	}

}
