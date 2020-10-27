package poo.uva.es.tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.Test;
import poo.uva.es.informaticafe.*;

/**
 * Coleccion de tests para la clase Promo
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public class PromoTest {
	private static final String NOMBRE = "Nombre generico";
	private static final String DESCRIPCION = "Descripcion generica";
	private static final LocalDateTime FECHA_INICIO = LocalDateTime.of(2019, 12, 1, 0, 0);
	private static final LocalDateTime FECHA_FIN = LocalDateTime.of(2019, 12, 1, 23, 59);

	@Test
	public void promoVacia() {
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);

		assertEquals(2, promo.precio(), 0.001);
		assertEquals(0, promo.unidadesDisponibles());
		assertEquals(NOMBRE, promo.nombre());
		assertEquals(DESCRIPCION, promo.descripcion());
	}

	@Test(expected = IllegalArgumentException.class)
	public void promoConFechasInvalidas() {
		new Promo(NOMBRE, DESCRIPCION, 0, FECHA_FIN, FECHA_INICIO);

	}

	@Test(expected = IllegalArgumentException.class)
	public void promoConPrecioInvalido() {

		new Promo(NOMBRE, DESCRIPCION, -20, FECHA_INICIO, FECHA_FIN);

	}

	@Test
	public void promoConProducto() {

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		assertEquals(2, promo.precio(), 0.001);
		assertEquals(200, promo.unidadesDisponibles());

	}

	@Test
	public void eliminaProductoDePromo() {

		Producto mcDalena = new Producto(NOMBRE, DESCRIPCION, 10, 200);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(mcDalena);
		assertTrue(promo.tieneProducto(mcDalena));

		promo.eliminaProducto(mcDalena);
		assertFalse(promo.tieneProducto(mcDalena));
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminaProductoNoExistente() {
		Producto mcDalena = new Producto(NOMBRE, DESCRIPCION, 10, 200);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);

		promo.eliminaProducto(mcDalena);
	}

	@Test
	public void promoConMultiplesProductos() {

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 20, 40));

		assertEquals(15, promo.precio(), 0.001);
		assertEquals(40, promo.unidadesDisponibles());

	}

	@Test(expected = IllegalArgumentException.class)
	public void promoConMismoProductoRepetido() {

		Producto producto = new Producto(NOMBRE, DESCRIPCION, 10, 200);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(producto);
		promo.insertarProducto(producto);
	}

	@Test
	public void promoDisponible() {

		// La promo empezo hace tres dias, y terminara en tres dias
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(3);
		LocalDateTime fechaFin = LocalDateTime.now().plusDays(3);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, fechaInicio, fechaFin);
		assertTrue(promo.disponible());
	}

	@Test
	public void promoAntesDeEstarDisponible() {

		// La promo empezara en dos dias, y terminara en cuatro
		LocalDateTime fechaInicio = LocalDateTime.now().plusDays(2);
		LocalDateTime fechaFin = LocalDateTime.now().plusDays(4);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, fechaInicio, fechaFin);
		assertFalse(promo.disponible());
	}

	@Test
	public void promoTrasEstarDisponible() {

		// La promo empezo hace cuatro dias, y termino hace dos dias
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(4);
		LocalDateTime fechaFin = LocalDateTime.now().minusDays(2);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, fechaInicio, fechaFin);
		assertFalse(promo.disponible());
	}

	@Test
	public void reducirStockPromo() {
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 10, 200);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);

		promo.insertarProducto(producto);

		promo.reducirStock(1);

		assertEquals(199, producto.unidadesDisponibles());
		assertEquals(199, promo.unidadesDisponibles());
	}

	@Test
	public void reducirStockPromoMultiplesVeces() {
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 10, 200);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);

		promo.insertarProducto(producto);

		promo.reducirStock(20);

		assertEquals(180, producto.unidadesDisponibles());
		assertEquals(180, promo.unidadesDisponibles());
	}

	@Test
	public void reducirStockMultiplesProductos() {
		Producto producto1 = new Producto(NOMBRE, DESCRIPCION, 10, 50);
		Producto producto2 = new Producto(NOMBRE, DESCRIPCION, 10, 30);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);

		promo.insertarProducto(producto1);
		promo.insertarProducto(producto2);

		promo.reducirStock(18);

		assertEquals((long) 50 - 18, producto1.unidadesDisponibles());
		assertEquals((long) 30 - 18, producto2.unidadesDisponibles());
		assertEquals((long) 30 - 18, promo.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void reducirStockPorDebajoDelDisponible() {
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 10, 5);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);

		promo.insertarProducto(producto);

		promo.reducirStock(10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reducirStockInvalido() {
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 10, 5);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 15, FECHA_INICIO, FECHA_FIN);

		promo.insertarProducto(producto);

		promo.reducirStock(-2);
	}

	@Test
	public void desglosePromo() {

		Producto cheeseburger = new Producto(NOMBRE, DESCRIPCION, 3, 200);
		Producto burger = new Producto(NOMBRE, DESCRIPCION, 3, 200);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 0, FECHA_INICIO, FECHA_FIN);

		promo.insertarProducto(cheeseburger);
		promo.insertarProducto(burger);

		HashMap<Producto, Integer> desglose = (HashMap<Producto, Integer>) promo.desglose();

		assertTrue(desglose.containsKey(cheeseburger));
		assertTrue(desglose.containsKey(burger));

	}
}