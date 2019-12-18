package poo.uva.es.tests;

import org.junit.Test;
import poo.uva.es.informaticafe.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

/**
 * Colección de test de la clase Almacen
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */

public class AlmacenTest {

	private static final LocalDateTime PROMO_INICIO = LocalDateTime.of(2019, 12, 10, 0, 0);
	private static final LocalDateTime PROMO_FIN = LocalDateTime.of(2019, 12, 10, 23, 59);
	private static final String NOMBRE_PRODUCTO = "Nombre del producto";
	private static final String DESCRIPCION_PRODUCTO = "Descripcion generica del producto";

	@Test
	public void creaAlmacen() {

		Almacen prueba = new Almacen();
		assertTrue(prueba.vacio());

	}

	@Test
	public void almacenConVendibles() {
		Almacen almacen = new Almacen();

		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 100);
		Promo peraEnOferta = new Promo("Pera en oferta", "Van a caducar", 1, PROMO_INICIO, PROMO_FIN);
		Combo multiplesPeras = new Combo("Pack de peras", "Dos peras juntas");
		multiplesPeras.insertarProducto(pera);
		multiplesPeras.insertarProducto(pera);

		almacen.insertaVendible(pera);
		almacen.insertaVendible(peraEnOferta);
		almacen.insertaVendible(multiplesPeras);

		assertTrue(almacen.existe(pera));
		assertTrue(almacen.existe(peraEnOferta));
		assertTrue(almacen.existe(multiplesPeras));

	}

	@Test(expected = IllegalArgumentException.class)
	public void stockIncrementadoNulo() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0);
		Almacen prueba = new Almacen();
		prueba.incrementarStock(pera, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockIncrementadoProductoNoExiste() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0);
		Almacen prueba = new Almacen();
		prueba.incrementarStock(pera, 3);
	}

	@Test
	public void stockIncrementadoCorrecto() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 0);
		Almacen prueba = new Almacen();
		prueba.insertaVendible(pera);
		prueba.incrementarStock(pera, 2);
		assertEquals(2, pera.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockReducidoProductoNoExiste() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 3);
		Almacen prueba = new Almacen();
		prueba.removerStock(pera, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockReducirProductoStockNegativo() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 3);
		Almacen prueba = new Almacen();
		prueba.insertaVendible(pera);
		prueba.removerStock(pera, -8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockReducirProductoNoHaySuficienteStock() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 1);
		Almacen prueba = new Almacen();
		prueba.insertaVendible(pera);
		prueba.removerStock(pera, 4);

	}

	@Test
	public void stockReducirProducto() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 6);
		Almacen prueba = new Almacen();
		prueba.insertaVendible(pera);
		prueba.removerStock(pera, 4);
		assertEquals(2, pera.unidadesDisponibles());

	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarProductoNoExiste() {
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 6);
		Almacen prueba = new Almacen();
		prueba.eliminar(pera);

	}

	@Test
	public void eliminarProducto() {
		Almacen almacen = new Almacen();
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 100);

		almacen.insertaVendible(pera);
		assertTrue(almacen.existe(pera));
		almacen.eliminar(pera);
		assertFalse(almacen.existe(pera));
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadProductoNoExiste() {
		Almacen prueba = new Almacen();
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 6);
		prueba.cantidad(pera);
	}

	@Test
	public void cantidadProductoExiste() {
		Almacen prueba = new Almacen();
		Producto pera = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, 2.0, 6);
		prueba.insertaVendible(pera);
		assertEquals(6, prueba.cantidad(pera));
	}

}
