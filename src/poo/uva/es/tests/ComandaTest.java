package poo.uva.es.tests;

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
 * @author migrase
 * @author manmend
 */
public class ComandaTest {

	@Test
	public void comandaValida() {
		Comanda comanda = new Comanda();
		assertTrue(comanda.vacia());
	}

	@Test
	public void tieneProducto() {
		Comanda comanda = new Comanda();
		Producto manzana = new Producto("Manzana", "", 2.3, 2);

		comanda.addProducto(manzana, 1);

		assertTrue(comanda.tieneProducto(manzana));
	}

	@Test
	public void noTieneProducto() {
		Comanda comanda = new Comanda();
		Producto manzana = new Producto("Manzana", "", 2.3, 5);
		Producto pera = new Producto("Pera", "", 2.1, 5);

		comanda.addProducto(manzana, 3);
		assertFalse(comanda.tieneProducto(pera));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoInvalido() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		comanda.addProducto(pera, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProductoExistente() {
		Comanda comanda = new Comanda();
		Producto manzana = new Producto("Manzana", "", 2.3, 5);

		comanda.addProducto(manzana, 2);
		comanda.addProducto(manzana, 3);
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
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, 4);

		assertTrue(comanda.tieneProducto(pera));
		assertEquals(4, comanda.cantidad(pera));

	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInnvalido() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInexistente() {
		Comanda comanda = new Comanda();
		Producto manzana = new Producto("Manzana", "", 2.3, 5);

		comanda.modificaProducto(manzana, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoSinStockSuficiente() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		comanda.addProducto(pera, 3);
		comanda.modificaProducto(pera, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadProductoNoExistente() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);

		comanda.cantidad(pera);
	}

	@Test
	public void estadoPorDefecto() {
		Comanda comanda = new Comanda();

		assertEquals(0, comanda.getEstado());
	}

	@Test
	public void fecha() {
		Comanda comanda = new Comanda();
		comanda.getFecha();
	}

	@Test
	public void importe() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);
		Producto manzana = new Producto("Manzana", "", 1.5, 5);

		comanda.addProducto(pera, 3);
		comanda.addProducto(manzana, 4);

		assertEquals(2.1 * 3 + 1.5 * 4, comanda.importe(), 0.001);
	}

	@Test
	public void importeComandaAnulada() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);
		Producto manzana = new Producto("Manzana", "", 1.5, 5);

		comanda.addProducto(pera, 3);
		comanda.addProducto(manzana, 4);

		comanda.setEstado(3);

		assertEquals(0, comanda.importe(), 0.001);
	}

	@Test
	public void sirveProductos() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);
		Producto manzana = new Producto("Manzana", "", 1.5, 5);

		comanda.addProducto(pera, 3);
		comanda.addProducto(manzana, 4);

		comanda.setEstado(2);

		assertEquals(5 - 3, pera.unidadesDisponibles());
		assertEquals(5 - 4, manzana.unidadesDisponibles());

	}

	@Test(expected = IllegalArgumentException.class)
	public void sirveProductosSinStock() {
		Comanda comanda = new Comanda();
		Producto pera = new Producto("Pera", "", 2.1, 5);
		Producto manzana = new Producto("Manzana", "", 1.5, 5);

		comanda.addProducto(pera, 3);
		comanda.addProducto(manzana, 4);

		manzana.reducirStock(3);

		comanda.setEstado(2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void setEstadoActual() {
		Comanda comanda = new Comanda();

		comanda.setEstado(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setEstadoComandaPagada() {
		Comanda comanda = new Comanda();

		comanda.setEstado(2);
		comanda.setEstado(1);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setEstadoNegativo() {
		Comanda comanda = new Comanda();

		comanda.setEstado(-1);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setEstadoInvalido() {
		Comanda comanda = new Comanda();

		comanda.setEstado(5);

	}
}