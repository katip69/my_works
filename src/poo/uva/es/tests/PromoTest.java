package poo.uva.es.tests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

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
public class PromoTest {
	private static final String NOMBRE_PROMO = "Nombre Promocion";
	private static final String NOMBRE_PRODUCTO = "Nombre Producto";
	private static final String DESCRIPCION = "Descripcion generica";

	@Test
	public void promoVacia() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 2, fechaInicio, fechaFin);

		assertEquals(2, promo.precio(), 0.001);
		assertEquals(0, promo.unidadesDisponibles());
		assertEquals(NOMBRE_PROMO, promo.nombre());
		assertEquals(DESCRIPCION, promo.descripcion());
	}

	@Test(expected = IllegalArgumentException.class)
	public void promoConFechasInvalidas() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		new Promo(NOMBRE_PROMO, DESCRIPCION, 0, fechaFin, fechaInicio);

	}

	@Test(expected = IllegalArgumentException.class)
	public void promoConPrecioInvalido() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		new Promo(NOMBRE_PROMO, DESCRIPCION, -20, fechaInicio, fechaFin);

	}

	@Test
	public void promoConProducto() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 2, fechaInicio, fechaFin);
		promo.insertarProducto(new Producto(NOMBRE_PRODUCTO, DESCRIPCION, 10, 200));

		assertEquals(2, promo.precio(), 0.001);
		assertEquals(200, promo.unidadesDisponibles());

	}

	@Test
	public void eliminaProductoDePromo() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		Producto mcDalena = new Producto(NOMBRE_PRODUCTO, DESCRIPCION, 10, 200);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 2, fechaInicio, fechaFin);
		promo.insertarProducto(mcDalena);
		assertTrue(promo.tieneProducto(mcDalena));

		promo.eliminaProducto(mcDalena);
		assertFalse(promo.tieneProducto(mcDalena));
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminaProductoNoExistente() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		Producto mcDalena = new Producto(NOMBRE_PRODUCTO, DESCRIPCION, 10, 200);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 2, fechaInicio, fechaFin);

		promo.eliminaProducto(mcDalena);
	}

	@Test
	public void promoConMultiplesProductos() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 15, fechaInicio, fechaFin);
		promo.insertarProducto(new Producto(NOMBRE_PRODUCTO, DESCRIPCION, 10, 200));
		promo.insertarProducto(new Producto(NOMBRE_PRODUCTO, DESCRIPCION, 20, 40));

		assertEquals(15, promo.precio(), 0.001);
		assertEquals(40, promo.unidadesDisponibles());

	}

	@Test(expected = IllegalArgumentException.class)
	public void promoConMismoProductoRepetido() {
		LocalDateTime fechaInicio = LocalDateTime.of(2019, 12, 1, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(2019, 12, 1, 23, 59);
		Producto producto = new Producto(NOMBRE_PRODUCTO, DESCRIPCION, 10, 200);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 15, fechaInicio, fechaFin);
		promo.insertarProducto(producto);
		promo.insertarProducto(producto);
	}

	@Test
	public void promoDisponible() {

		// La promo empezo hace tres dias, y terminara en tres dias
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(3);
		LocalDateTime fechaFin = LocalDateTime.now().plusDays(3);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 15, fechaInicio, fechaFin);
		assertTrue(promo.disponible());
	}

	@Test
	public void promoAntesDeEstarDisponible() {

		// La promo empezara en dos dias, y terminara en cuatro
		LocalDateTime fechaInicio = LocalDateTime.now().plusDays(2);
		LocalDateTime fechaFin = LocalDateTime.now().plusDays(4);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 15, fechaInicio, fechaFin);
		assertFalse(promo.disponible());
	}

	@Test
	public void promoTrasEstarDisponible() {

		// La promo empezo hace cuatro dias, y termino hace dos dias
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(4);
		LocalDateTime fechaFin = LocalDateTime.now().minusDays(2);

		Promo promo = new Promo(NOMBRE_PROMO, DESCRIPCION, 15, fechaInicio, fechaFin);
		assertFalse(promo.disponible());
	}
}