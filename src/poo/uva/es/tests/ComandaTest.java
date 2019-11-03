package poo.uva.es.tests;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Comanda;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Coleccion de tests para la clase Comanda
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class ComandaTest {

	@Test
	public void comandaValida() {
		Comanda comanda = new Comanda();
		assertTrue(comanda.vacia());
	}

	@Test
	public void comandaConAtributosValidos() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(3, fecha, productos);
		double importe = 1 * 3.0;

		assertEquals(3, comanda.getEstado());
		assertEquals(fecha, LocalDate.now());
		assertEquals(importe, comanda.importe(), 0.001);
		assertEquals(3, comanda.cantidad(manzana));
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaConEstadoInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		@SuppressWarnings("unused")
		Comanda comanda = new Comanda(-1, fecha, productos);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaConEstadoInvalidoPorArriba() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		@SuppressWarnings("unused")
		Comanda comanda = new Comanda(3, fecha, productos);
	}

	@Test
	public void getEstado() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		assertEquals(0, comanda.getEstado());

	}

	@Test
	public void getFecha() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		assertEquals(LocalDate.now(), comanda.getFecha());

	}
	@Test
	public void setEstado() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		comanda.setEstado(2);
		assertEquals(2,comanda.getEstado());
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void setEstadoInferiorA0() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		comanda.setEstado(-2);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setEstadoInferiorMayorQue2() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		comanda.setEstado(3);
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void setEstadoIgual() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		comanda.setEstado(0);
		
	}

	@Test
	public void importe() {
		Comanda comanda = new Comanda();
		comanda.addProducto(new Producto("Pera", "", 1.4, 99), 1);
		comanda.addProducto(new Producto("Manzana", "", 1.7, 99), 2);
		comanda.addProducto(new Producto("Platano", "", 0.52, 99), 4);
		comanda.addProducto(new Producto("Tomate", "", 2.38, 99), 7);

		double importe = 1.4 + 2 * 1.7 + 4 * 0.52 + 7 * 2.38;
		assertEquals(importe, comanda.importe(), 0.001);
	}

	@Test
	public void tieneProducto() {
		Producto manzana = new Producto("Manzana", "", 2.3, 2);

		Comanda comanda = new Comanda();

		comanda.addProducto(manzana, 1);

		assertTrue(comanda.tieneProducto(manzana));
	}

	@Test(expected = IllegalArgumentException.class)
	public void noTieneProducto() {
		Producto manzana = new Producto("Manzana", "", 2.3, 5);

		Comanda comanda = new Comanda();

		comanda.tieneProducto(manzana);
	}

	@Test
	public void addProductoValido() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, 3);

		assertTrue(comanda.tieneProducto(pera));
		assertEquals(3, comanda.cantidad(pera));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoInvalido() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoExistente() {
		Producto manzana = new Producto("Manzana", "", 2.3, 5);
		Comanda comanda = new Comanda();

		comanda.addProducto(manzana, 2);
		comanda.addProducto(manzana, 1);

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
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, 4);

		assertTrue(comanda.tieneProducto(pera));
		assertEquals(1, pera.unidadesDisponibles());
		assertEquals(4, comanda.cantidad(pera));

	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInnvalido() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInexistente() {
		Producto manzana = new Producto("Manzana", "", 2.3, 5);

		Comanda comanda = new Comanda();

		comanda.modificaProducto(manzana, 2);
	}

	// FaltaTerminarlo
	@Test(expected = IllegalArgumentException.class)
	public void comandaConCantidadSinAñadirElProductoALaComanda() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		Producto pera = new Producto("Pera", "Fruta etc etc", 1.0);
		int estado = 6;
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(estado, fecha, productos);
		comanda.tieneProducto(pera);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoSinStockSuficiente() {
		Producto pera = new Producto("Pera", "", 2.1, 5);

		Comanda comanda = new Comanda();

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, 6);
	}

}
