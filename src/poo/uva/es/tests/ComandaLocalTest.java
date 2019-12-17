package poo.uva.es.tests;


	import org.junit.Test;
	import poo.uva.es.informaticafe.Producto;
	import poo.uva.es.informaticafe.ComandaLocal;
	import poo.uva.es.informaticafe.Estados;

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
	public class ComandaLocalTest {

		@Test
		public void comandaValida() {
			ComandaLocal comanda = new ComandaLocal();
			assertTrue(comanda.vacia());
		}

		@Test
		public void tieneProducto() {
			ComandaLocal comanda = new ComandaLocal();
			Producto manzana = new Producto("Manzana", "", 2.3, 2);

			comanda.addProducto(manzana, 1);

			assertTrue(comanda.tieneProducto(manzana));
		}

		@Test
		public void noTieneProducto() {
			ComandaLocal comanda = new ComandaLocal();
			Producto manzana = new Producto("Manzana", "", 2.3, 5);
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(manzana, 3);
			assertFalse(comanda.tieneProducto(pera));
		}

		@Test(expected = IllegalArgumentException.class)
		public void addProductoInvalido() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, -3);
		}

		@Test(expected = IllegalArgumentException.class)
		public void addProductoExistente() {
			ComandaLocal comanda = new ComandaLocal();
			Producto manzana = new Producto("Manzana", "", 2.3, 5);

			comanda.addProducto(manzana, 2);
			comanda.addProducto(manzana, 3);
		}

		@Test(expected = IllegalArgumentException.class)
		public void addProductoSinStockSuficiente() {
			Producto pera = new Producto("Pera", "", 2.1, 5);

			ComandaLocal comanda = new ComandaLocal();

			comanda.addProducto(pera, 8);
		}

		@Test
		public void removeProductoValido() {
			Producto pera = new Producto("Pera", "", 2.1, 5);

			ComandaLocal comanda = new ComandaLocal();

			comanda.addProducto(pera, 3);

			comanda.removeProducto(pera);

			assertFalse(comanda.tieneProducto(pera));
			assertEquals(5, pera.unidadesDisponibles());
		}

		@Test(expected = IllegalArgumentException.class)
		public void removeProductoInexistente() {
			Producto pera = new Producto("Pera", "", 2.1, 5);

			ComandaLocal comanda = new ComandaLocal();

			comanda.removeProducto(pera);

		}

		@Test
		public void modificaProductoValido() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);
			comanda.modificaProducto(pera, 4);

			assertTrue(comanda.tieneProducto(pera));
			assertEquals(4, comanda.cantidad(pera));

		}

		@Test(expected = IllegalArgumentException.class)
		public void modificaProductoInnvalido() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);
			comanda.modificaProducto(pera, -2);
		}

		@Test(expected = IllegalArgumentException.class)
		public void modificaProductoInexistente() {
			ComandaLocal comanda = new ComandaLocal();
			Producto manzana = new Producto("Manzana", "", 2.3, 5);

			comanda.modificaProducto(manzana, 2);
		}

		@Test(expected = IllegalArgumentException.class)
		public void modificaProductoSinStockSuficiente() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);
			comanda.modificaProducto(pera, 6);
		}

		@Test(expected = IllegalArgumentException.class)
		public void cantidadProductoNoExistente() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.cantidad(pera);
		}

		@Test
		public void estadoPorDefecto() {
			ComandaLocal comanda = new ComandaLocal();

			assertEquals(Estados.ABIERTO, comanda.getEstado());
		}

		@Test
		public void fecha() {
			ComandaLocal comanda = new ComandaLocal();
			comanda.getFecha();
		}

		@Test
		public void importe() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);
			Producto manzana = new Producto("Manzana", "", 1.5, 5);

			comanda.addProducto(pera, 3);
			comanda.addProducto(manzana, 4);

			assertEquals(2.1 * 3 + 1.5 * 4, comanda.importe(), 0.001);
		}

		@Test
		public void importeComandaAnulada() {
			ComandaLocal comanda = new ComandaLocal();
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
			ComandaLocal comanda = new ComandaLocal();
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
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);
			Producto manzana = new Producto("Manzana", "", 1.5, 5);

			comanda.addProducto(pera, 3);
			comanda.addProducto(manzana, 4);

			manzana.reducirStock(3);

			comanda.setEstado(Estados.PAGADO);

		}

		@Test(expected = IllegalArgumentException.class)
		public void setEstadoActual() {
			ComandaLocal comanda = new ComandaLocal();

			comanda.setEstado(Estados.ABIERTO);
		}

		@Test(expected = IllegalArgumentException.class)
		public void setEstadoComandaPagada() {
			ComandaLocal comanda = new ComandaLocal();

			comanda.setEstado(Estados.PAGADO);
			comanda.setEstado(Estados.CERRADO);

		}
		
		@Test(expected = IllegalArgumentException.class)
		public void setEstadoComandaAnulada() {
			ComandaLocal comanda = new ComandaLocal();

			comanda.setEstado(Estados.ANULADO);
			comanda.setEstado(Estados.ABIERTO);

		}

		@Test
		public void precioComandaTrasCambioPrecioProducto() {
			ComandaLocal comanda = new ComandaLocal();
			Producto pera = new Producto("Pera", "", 2.1, 5);

			comanda.addProducto(pera, 3);

			assertEquals(2.1 * 3, comanda.importe(), 0.001);

			comanda.setEstado(Estados.PAGADO);

			pera.cambiaPrecio(99.99);

			assertEquals(2.1 * 3, comanda.importe(), 0.001);

		}

	}
