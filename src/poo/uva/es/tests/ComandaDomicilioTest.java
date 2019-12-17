package poo.uva.es.tests;

import poo.uva.es.informaticafe.ComandaDomicilio;
import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Estados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ComandaDomicilioTest {
	
		@Test
		public void comandaValida() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			assertTrue(comanda.vacia());
		}

		@Test
		public void tieneProducto() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto manzana = new Producto("Manzana", "", 2.3, 2);

			comanda.addProducto(manzana, 1);

			assertTrue(comanda.tieneProducto(manzana));
		}

		@Test
		public void noTieneProducto() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto manzana = new Producto("Manzana", "", 2.3, 5);
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(manzana, 3);
			assertFalse(comanda.tieneProducto(pera));
		}

		@Test(expected = IllegalArgumentException.class)
		public void addProductoInvalido() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, -3);
		}

		@Test(expected = IllegalArgumentException.class)
		public void addProductoExistente() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto manzana = new Producto("Manzana", "", 2.3, 5);

			comanda.addProducto(manzana, 2);
			comanda.addProducto(manzana, 3);
		}

		@Test(expected = IllegalArgumentException.class)
		public void addProductoSinStockSuficiente() {
			Producto pera = new Producto("Pera", "", 2.1, 5);

			ComandaDomicilio comanda = new ComandaDomicilio();

			comanda.addProducto(pera, 8);
		}

		@Test
		public void removeProductoValido() {
			Producto pera = new Producto("Pera", "", 2.1, 5);

			ComandaDomicilio comanda = new ComandaDomicilio();

			comanda.addProducto(pera, 3);

			comanda.removeProducto(pera);

			assertFalse(comanda.tieneProducto(pera));
			assertEquals(5, pera.unidadesDisponibles());
		}

		@Test(expected = IllegalArgumentException.class)
		public void removeProductoInexistente() {
			Producto pera = new Producto("Pera", "", 2.1, 5);

			ComandaDomicilio comanda = new ComandaDomicilio();

			comanda.removeProducto(pera);

		}

		@Test
		public void modificaProductoValido() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);
			comanda.modificaProducto(pera, 4);

			assertTrue(comanda.tieneProducto(pera));
			assertEquals(4, comanda.cantidad(pera));

		}

		@Test(expected = IllegalArgumentException.class)
		public void modificaProductoInnvalido() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);
			comanda.modificaProducto(pera, -2);
		}

		@Test(expected = IllegalArgumentException.class)
		public void modificaProductoInexistente() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto manzana = new Producto("Manzana", "", 2.3, 5);

			comanda.modificaProducto(manzana, 2);
		}

		@Test(expected = IllegalArgumentException.class)
		public void modificaProductoSinStockSuficiente() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);
			comanda.modificaProducto(pera, 6);
		}

		@Test(expected = IllegalArgumentException.class)
		public void cantidadProductoNoExistente() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.cantidad(pera);
		}

		@Test
		public void estadoPorDefecto() {
			ComandaDomicilio comanda = new ComandaDomicilio();

			assertEquals(Estados.ABIERTO, comanda.getEstado());
		}

		@Test
		public void fecha() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			comanda.getFecha();
		}

		@Test
		public void importe() {
			ComandaDomicilio comanda=new ComandaDomicilio();
			

		}

		@Test
		public void importeComandaAnulada() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);
			Producto manzana = new Producto("Manzana", "", 1.5, 5);

			comanda.addProducto(pera, 3);
			comanda.addProducto(manzana, 4);

			comanda.setEstado(Estados.ANULADO);

			assertEquals(0, comanda.importe(), 0.001);
			assertEquals(Estados.ANULADO, comanda.getEstado());
		}

		@Test
		public void sirveProductos() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);
			Producto manzana = new Producto("Manzana", "", 1.5, 5);

			comanda.addProducto(pera, 3);
			comanda.addProducto(manzana, 4);

			comanda.setEstado(Estados.PAGADO);

			assertEquals(5 - 3, pera.unidadesDisponibles());
			assertEquals(5 - 4, manzana.unidadesDisponibles());
			assertEquals(Estados.PAGADO, comanda.getEstado());

		}

		@Test(expected = IllegalArgumentException.class)
		public void sirveProductosSinStock() {
			ComandaDomicilio comanda = new ComandaDomicilio();
			Producto pera = new Producto("Pera", "", 2.1, 5);
			Producto manzana = new Producto("Manzana", "", 1.5, 5);

			comanda.addProducto(pera, 3);
			comanda.addProducto(manzana, 4);

			manzana.reducirStock(3);

			comanda.setEstado(Estados.PAGADO);

		}

		@Test(expected = IllegalArgumentException.class)
		public void setEstadoActual() {
			ComandaDomicilio comanda = new ComandaDomicilio();

			comanda.setEstado(Estados.ABIERTO);
		}

		@Test(expected = IllegalArgumentException.class)
		public void setEstadoComandaPagada() {
			ComandaDomicilio comanda = new ComandaDomicilio();

			comanda.setEstado(Estados.PAGADO);
			comanda.setEstado(Estados.CERRADO);

		}
		
		@Test(expected = IllegalArgumentException.class)
		public void setEstadoComandaAnulada() {
			ComandaDomicilio comanda = new ComandaDomicilio();

			comanda.setEstado(Estados.ANULADO);
			comanda.setEstado(Estados.ABIERTO);

		}
	
	}

