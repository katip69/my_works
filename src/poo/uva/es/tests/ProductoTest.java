package poo.uva.es.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import poo.uva.es.informaticafe.Producto;

public class ProductoTest {

	@Test
	public void productoValido() {
		String nombre = "Manzana";
		String descripcion = "Fruta pomácea comestible de forma redonda, fruto del manzano";
		double precio = 0.56;
		int cantidad = 5;

		Producto manzana = new Producto(nombre, descripcion, precio, cantidad);

		assertEquals(nombre, manzana.nombre());
		assertEquals(descripcion, manzana.descripcion());
		assertEquals(precio, manzana.precio(), 0.001);
		assertEquals(cantidad, manzana.unidadesDisponibles());
	}

	@Test
	public void nuevoProductoSinStock() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56);

		assertEquals(0, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoProductoConPrecioInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", -0.56);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoProductoConStockInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, -3);
	}
	
	@Test
	public void nuevoPrecio() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56);
		manzana.cambiaPrecio(0.78);

		assertEquals(0.78, manzana.precio(), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoPrecioInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56);
		manzana.cambiaPrecio(-0.78);
	}

	@Test
	public void nuevoStock() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.modificarStock(3);

		assertEquals(3, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevoStockInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.modificarStock(-1);
	}

	@Test
	public void aumentaStock() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.aumentarStock(3);

		assertEquals(7, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void aumentaStockInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.aumentarStock(0);
	}

	@Test
	public void reduceStock() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.reducirStock(3);

		assertEquals(1, manzana.unidadesDisponibles());
	}

	@Test(expected = IllegalArgumentException.class)
	public void reduceStockInvalido() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.reducirStock(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reduceStockSinDisponibilidad() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 0.56, 4);
		manzana.reducirStock(5);
	}

}
