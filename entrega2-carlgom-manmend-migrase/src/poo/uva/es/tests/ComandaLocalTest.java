package poo.uva.es.tests;

import org.junit.Test;
import poo.uva.es.informaticafe.*;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

/**
 * Coleccion de tests para la clase ComandaLocal
 * 
 * @author carlgom
 * @author migrase
 * @author manmend
 * @version 2.0
 */
public class ComandaLocalTest {

	private static final String NOMBRE = "Nombre";
	private static final String DESCRIPCION = "Descripcion generica";
	private static final LocalDateTime FECHA_INICIO = LocalDateTime.now().minusDays(1);
	private static final LocalDateTime FECHA_FIN =LocalDateTime.now().plusDays(1);

	@Test
	public void comandaValida() {
		ComandaLocal comanda = new ComandaLocal();
		assertTrue(comanda.vacia());
	}

	@Test
	public void tieneVendibles() {
		ComandaLocal comanda = new ComandaLocal();
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 20);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		combo.insertarProducto(manzana);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 0, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(manzana);

		comanda.addVendible(manzana, 1);
		comanda.addVendible(combo, 1);
		comanda.addVendible(promo, 1);

		assertTrue(comanda.tieneVendible(manzana));
		assertTrue(comanda.tieneVendible(combo));
		assertTrue(comanda.tieneVendible(promo));

	}

	@Test
	public void noTieneVendible() {
		ComandaLocal comanda = new ComandaLocal();
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 5);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(manzana, 3);
		assertFalse(comanda.tieneVendible(pera));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addVendibleInvalido() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addVendibleExistente() {
		ComandaLocal comanda = new ComandaLocal();
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 5);

		comanda.addVendible(manzana, 2);
		comanda.addVendible(manzana, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addVendibleSinStockSuficiente() {
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		ComandaLocal comanda = new ComandaLocal();

		comanda.addVendible(pera, 8);
	}

	@Test
	public void removeVendibleValido() {
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		ComandaLocal comanda = new ComandaLocal();

		comanda.addVendible(pera, 3);

		comanda.removeVendible(pera);

		assertFalse(comanda.tieneVendible(pera));
		assertEquals(5, pera.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeVendibleInexistente() {
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		ComandaLocal comanda = new ComandaLocal();

		comanda.removeVendible(pera);

	}

	@Test
	public void modificaVendibleValido() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, 3);
		comanda.modificaVendible(pera, 4);

		assertTrue(comanda.tieneVendible(pera));
		assertEquals(4, comanda.cantidad(pera));

	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaVendibleInnvalido() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, 3);
		comanda.modificaVendible(pera, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaVendibleInexistente() {
		ComandaLocal comanda = new ComandaLocal();
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 5);

		comanda.modificaVendible(manzana, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaVendibleSinStockSuficiente() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, 3);
		comanda.modificaVendible(pera, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadProductoNoExistente() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

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
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 1.5, 5);

		comanda.addVendible(pera, 3);
		comanda.addVendible(manzana, 4);

		assertEquals(2.1 * 3 + 1.5 * 4, comanda.importe(), 0.001);
	}

	@Test
	public void importeComandaAnulada() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 1.5, 5);

		comanda.addVendible(pera, 3);
		comanda.addVendible(manzana, 4);

		comanda.setEstado(Estados.ANULADO);

		assertEquals(0, comanda.importe(), 0.001);
		assertEquals(Estados.ANULADO, comanda.getEstado());
	}

	@Test
	public void sirveProductos() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 1.5, 5);

		comanda.addVendible(pera, 3);
		comanda.addVendible(manzana, 4);

		comanda.setEstado(Estados.PAGADO);

		assertEquals((long) 5 - 3, pera.unidadesDisponibles());
		assertEquals((long) 5 - 4, manzana.unidadesDisponibles());
		assertEquals(Estados.PAGADO, comanda.getEstado());

	}

	@Test(expected = IllegalArgumentException.class)
	public void sirveProductosSinStock() {
		ComandaLocal comanda = new ComandaLocal();
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 1.5, 5);

		comanda.addVendible(pera, 3);
		comanda.addVendible(manzana, 4);

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
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, 3);

		assertEquals(2.1 * 3, comanda.importe(), 0.001);

		comanda.setEstado(Estados.PAGADO);

		pera.cambiaPrecio(99.99);

		assertEquals(2.1 * 3, comanda.importe(), 0.001);

	}

	@Test
	public void pagaComandaMultiplesVendibles() {
		// La promo empezo hace cuatro dias, y termino hace dos dias
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(1);
		LocalDateTime fechaFin = LocalDateTime.now().plusDays(1);
		ComandaLocal comanda = new ComandaLocal();
		// Cada producto cuesta 2.3€
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 2.3, 200);

		// El combo contiene 5 unidades del producto, a (5 * 2.3) * 0.9€
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		for (int i = 0; i < 5; i++) {
			combo.insertarProducto(producto);
		}

		// La promocion contiene 1 unidad a 1€, y es valida
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 1, fechaInicio, fechaFin);
		promo.insertarProducto(producto);

		// La comanda tiene 3 unidades del producto (6.9€)
		comanda.addVendible(producto, 3);
		// La comanda tiene 2 combos (10 unidades del producto)
		comanda.addVendible(combo, 2);
		// La comanda tiene 4 promos (4 unidades del producto)
		comanda.addVendible(promo, 4);

		assertEquals(31.6, comanda.importe(), 0.001);

	}

	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaMultiplesVendiblesSinStockSuficiente() {
		ComandaLocal comanda = new ComandaLocal();
		// Hay 15 unidades del producto
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 2.3, 15);

		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		for (int i = 0; i < 5; i++) {
			combo.insertarProducto(producto);
		}

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 1, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(producto);

		comanda.addVendible(producto, 3);
		comanda.addVendible(combo, 2);
		comanda.addVendible(promo, 4);

	}

	@Test
	public void insertarPromoValidaEnComanda() {
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(2);
		LocalDateTime fechaFin = LocalDateTime.now().plusDays(1);
		ComandaLocal comanda = new ComandaLocal();
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 2.3, 15);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 1, fechaInicio, fechaFin);
		promo.insertarProducto(producto);

		comanda.addVendible(promo, 1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void insertarPromoInvalidaEnComanda() {
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(2);
		LocalDateTime fechaFin = LocalDateTime.now().minusDays(1);
		ComandaLocal comanda = new ComandaLocal();
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 2.3, 15);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 1, fechaInicio, fechaFin);
		promo.insertarProducto(producto);

		comanda.addVendible(promo, 1);

	}

}
