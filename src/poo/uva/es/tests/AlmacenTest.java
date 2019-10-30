package poo.uva.es.tests;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Almacen;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Colección de test de la clase Almacen 
 * 
 * @author manmend
 * @author carlgom
 * @author migrase
 *
 */

public class AlmacenTest {

	@Test
	public void creaAlmacen() {

		Almacen prueba = new Almacen();
		assertTrue(prueba.vacio());

	}

	@Test
	public void productoAnadido() {
		Almacen prueba = new Almacen();
		Producto pera = new Producto("Pera", "Fruta", 2.0);

		prueba.creaProducto(pera);
		assertTrue(prueba.existe(pera));
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockIncrementadoNulo() {
		Producto pera = new Producto("Pera", "Fruta", 2.0);
		Almacen prueba = new Almacen();
		prueba.incrementarStock(pera, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockIncrementadoProductoNoExiste() {
		Producto pera = new Producto("Pera", "Fruta", 2.0);
		Almacen prueba = new Almacen();
		prueba.incrementarStock(pera, 3);
	}

	@Test
	public void stockIncrementadoCorrecto() {
		Producto pera = new Producto("Pera", "Fruta", 2.0,0);
		Almacen prueba = new Almacen();
		prueba.creaProducto(pera);
		prueba.incrementarStock(pera, 2);
		assertEquals(2,pera.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockReducidoProductoNoExiste() {
		Producto pera = new Producto("Pera", "Fruta", 2.0,3);
		Almacen prueba = new Almacen();
		prueba.removerStock(pera, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockReducirProductoStockNegativo() {
		Producto pera = new Producto("Pera", "Fruta", 2.0,3);
		Almacen prueba = new Almacen();
		prueba.creaProducto(pera);
		prueba.removerStock(pera, -8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stockReducirProductoNoHaySuficienteStock() {
		Producto pera = new Producto("Pera", "Fruta", 2.0, 1);
		Almacen prueba = new Almacen();
		prueba.creaProducto(pera);
		prueba.removerStock(pera, 4);

	}
	
	@Test
	public void stockReducirProducto() {
		Producto pera = new Producto("Pera", "Fruta", 2.0, 6);
		Almacen prueba = new Almacen();
		prueba.creaProducto(pera);
		prueba.removerStock(pera, 4);
		assertEquals(2,pera.unidadesDisponibles());

	}
	@Test(expected = IllegalArgumentException.class)
	public void eliminarProductoNoExiste() {
		Producto pera = new Producto("Pera", "Fruta", 2.0, 6);
		Almacen prueba = new Almacen();
		prueba.eliminar(pera);

	}
	@Test
	public void EliminarProducto() {
		Producto pera = new Producto("Pera", "Fruta", 2, 1);
		Almacen prueba = new Almacen();
		prueba.creaProducto(pera);
		prueba.eliminar(pera);
		assertEquals(false,prueba.existe(pera));

	}
	@Test(expected = IllegalArgumentException.class)
	public void cantidadProductoNoExiste() {
		Almacen prueba = new Almacen();
		Producto pera = new Producto("Pera", "Fruta", 2.0, 6);
		prueba.cantidad(pera);
	}
}
