package poo.uva.es.tests;

import poo.uva.es.informaticafe.ComandaDomicilio;
import poo.uva.es.informaticafe.Combo;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Promo;
import poo.uva.es.informaticafe.Zona;
import poo.uva.es.informaticafe.Estados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;

public class ComandaDomicilioTest {

	private static final String NOMBRE = "Nombre";
	private static final String DESCRIPCION = "Descripcion generica";
	private static final String DIRECCION = "Direccion generica";
	private static final LocalDateTime FECHA_INICIO = LocalDateTime.now().minusDays(1);
	private static final LocalDateTime FECHA_FIN = LocalDateTime.now().plusDays(1);

	@Test
	public void comandaValida() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		assertTrue(comanda.vacia());
	}

	@Test
	public void tieneVendible() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 20);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		combo.insertarProducto(manzana);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		comanda.addVendible(manzana, 1);
		comanda.addVendible(combo, 2);
		comanda.addVendible(promo, 1);

		assertTrue(comanda.tieneVendible(manzana));
		assertTrue(comanda.tieneVendible(combo));
		assertTrue(comanda.tieneVendible(promo));
	}

	@Test
	public void notieneVendible() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 5);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		combo.insertarProducto(manzana);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		assertFalse(comanda.tieneVendible(manzana));
		assertFalse(comanda.tieneVendible(combo));
		assertFalse(comanda.tieneVendible(promo));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addVendibleInvalido() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addComboInvalido() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		comanda.addVendible(combo, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addPromoInvalida() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);

		comanda.addVendible(promo, -3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addVendibleExistente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 5);

		comanda.addVendible(manzana, 2);
		comanda.addVendible(manzana, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addPromoExistente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		comanda.addVendible(promo, 2);
		comanda.addVendible(promo, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addComboExistente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		comanda.addVendible(combo, 2);
		comanda.addVendible(combo, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addVendibleSinStockSuficiente() {
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.addVendible(pera, 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addComboSinStockSuficiente() {
		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.addVendible(combo, 8);
	}

	@Test
	public void removeProductoValido() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 2);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		combo.insertarProducto(manzana);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		comanda.addVendible(promo, 1);
		comanda.addVendible(manzana, 1);
		comanda.addVendible(combo, 1);

		comanda.removeVendible(manzana);
		comanda.removeVendible(combo);
		comanda.removeVendible(promo);

		assertFalse(comanda.tieneVendible(manzana));
		assertFalse(comanda.tieneVendible(promo));
		assertFalse(comanda.tieneVendible(combo));

	}

	@Test(expected = IllegalArgumentException.class)
	public void removeProductoInexistente() {
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.removeVendible(pera);

	}

	@Test(expected = IllegalArgumentException.class)
	public void removeComboInexistente() {
		Combo combo = new Combo(NOMBRE, DESCRIPCION);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.removeVendible(combo);

	}

	@Test(expected = IllegalArgumentException.class)
	public void removePromoInexistente() {
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.removeVendible(promo);

	}

	@Test
	public void modificaProductoValido() {
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		combo.insertarProducto(manzana);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		comanda.addVendible(manzana, 3);
		comanda.modificaVendible(manzana, 1);
		comanda.addVendible(promo, 1);
		comanda.modificaVendible(promo, 1);
		comanda.addVendible(combo, 1);
		comanda.modificaVendible(combo, 1);

		assertTrue(comanda.tieneVendible(manzana));
		assertEquals(1, comanda.cantidad(manzana));
		assertTrue(comanda.tieneVendible(promo));
		assertTrue(comanda.tieneVendible(combo));
		assertEquals(1, comanda.cantidad(promo));
		assertEquals(1, comanda.cantidad(combo));

	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInnvalido() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, 3);
		comanda.modificaVendible(pera, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoInexistente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 2.3, 5);

		comanda.modificaVendible(manzana, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaProductoSinStockSuficiente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.addVendible(pera, 3);
		comanda.modificaVendible(pera, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadProductoNoExistente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);

		comanda.cantidad(pera);
	}

	@Test
	public void estadoPorDefecto() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		assertEquals(Estados.ABIERTO, comanda.getEstado());
	}

	@Test
	public void importeZona1() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		assertEquals(0.55, comanda.importe(), 0.001);

	}

	@Test
	public void importeZona2() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		comanda.setBolsas(2);
		assertEquals(1.1, comanda.importe(), 0.001);

	}

	@Test
	public void importeZona3() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setBolsas(2);
		assertEquals(2.6, comanda.importe(), 0.001);

	}

	@Test
	public void importeComandaAnulada() {

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 1.5, 5);
		Combo combo = new Combo(NOMBRE, DESCRIPCION);
		combo.insertarProducto(manzana);
		Promo promo = new Promo(NOMBRE, DESCRIPCION, 2, FECHA_INICIO, FECHA_FIN);
		promo.insertarProducto(new Producto(NOMBRE, DESCRIPCION, 10, 200));

		comanda.addVendible(pera, 3);
		comanda.addVendible(manzana, 4);
		comanda.addVendible(promo, 1);
		comanda.addVendible(combo, 1);

		comanda.setEstado(Estados.ANULADO);

		assertEquals(0, comanda.importe(), 0.001);
		assertEquals(Estados.ANULADO, comanda.getEstado());
	}

	@Test
	public void sirveProductos() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
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
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		Producto pera = new Producto(NOMBRE, DESCRIPCION, 2.1, 5);
		Producto manzana = new Producto(NOMBRE, DESCRIPCION, 1.5, 5);

		comanda.addVendible(pera, 3);
		comanda.addVendible(manzana, 4);

		manzana.reducirStock(3);

		comanda.setEstado(Estados.PAGADO);

	}

	@Test(expected = IllegalArgumentException.class)
	public void setEstadoActual() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.setEstado(Estados.ABIERTO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setEstadoComandaPagada() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.setEstado(Estados.PAGADO);
		comanda.setEstado(Estados.CERRADO);

	}

	@Test(expected = IllegalArgumentException.class)
	public void setEstadoComandaAnulada() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);

		comanda.setEstado(Estados.ANULADO);
		comanda.setEstado(Estados.ABIERTO);

	}

	@Test(expected = IllegalArgumentException.class)
	public void setBolsasNumeroInvalido() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setBolsas(0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void setDireccionNoValida() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.cambiaDireccion(null);

	}

	@Test
	public void cambiaDireccionValida() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.cambiaDireccion("hola");
		assertEquals("hola", comanda.getDireccion());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setZonaNoValida() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setZona(null);
	}

	@Test
	public void cambiaZonaValida() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setZona(Zona.ZONA1);
		assertEquals(Zona.ZONA1, comanda.getZona());
	}

	@Test(expected = IllegalArgumentException.class)
	public void insertarPromoInvalidaEnComanda() {
		LocalDateTime fechaInicio = LocalDateTime.now().minusDays(2);
		LocalDateTime fechaFin = LocalDateTime.now().minusDays(1);
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 2.3, 15);

		Promo promo = new Promo(NOMBRE, DESCRIPCION, 1, fechaInicio, fechaFin);
		promo.insertarProducto(producto);

		comanda.addVendible(promo, 1);

	}

	@Test
	public void importeComandaPagada() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		Producto producto = new Producto(NOMBRE, DESCRIPCION, 2.3, 15);
		comanda.addVendible(producto, 1);

		assertEquals(2.3 + 2.5 + 0.05, comanda.importe(), 0.001);

		comanda.setEstado(Estados.PAGADO);

		producto.cambiaPrecio(200);

		assertEquals(2.3 + 2.5 + 0.05, comanda.importe(), 0.001);

	}

	
	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaMultiplesVendiblesSinStockSuficiente() {
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
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
}
