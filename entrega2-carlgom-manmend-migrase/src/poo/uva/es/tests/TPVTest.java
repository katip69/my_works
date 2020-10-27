package poo.uva.es.tests;

import java.util.List;

import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Comanda;
import poo.uva.es.informaticafe.ComandaDomicilio;
import poo.uva.es.informaticafe.ComandaLocal;
import poo.uva.es.informaticafe.TPV;
import poo.uva.es.informaticafe.Zona;
import poo.uva.es.informaticafe.Estados;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */
public class TPVTest {

	private static final String NOMBRE = "Nombre";
	private static final String DESCRIPCION = "Descripcion generica";
	private static final String DIRECCION = "Direccion generica";
	private static final String CREDENCIAL_INIT = "A156Bv09_1zXo894";
	private static final String CREDENCIAL_PAGO = "6Z1y00Nm31aA-571";

	@Test
	public void creaTPV() {
		TPV tpv = new TPV();

		assertTrue(tpv.vacia());
	}

	@Test
	public void calcularImporteComandaLocal() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaLocal comanda1 = new ComandaLocal();
		comanda1.addVendible(new Producto(NOMBRE, DESCRIPCION, 1.7, 10), 5); // 5 peras a 1.7 cada una
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);
		assertEquals(4, tarjeta.getSaldoActual(), 0.001);

		ComandaLocal comanda3 = new ComandaLocal();
		comanda3.addVendible(new Producto(NOMBRE, DESCRIPCION, 4.6, 10), 9); // 9 naranjas a 4.6 cada una
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3); // Esta comanda no deberia contar

		assertEquals(1.7 * 5 + 2 * 3, tpv.importeTotal(comanda1.getFecha().toLocalDate()), 0.001);
	}

	@Test
	public void calcularImporteComandasDomicilio() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaDomicilio comanda1 = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		comanda1.addVendible(new Producto(NOMBRE, DESCRIPCION, 1.7, 10), 5); // 5 peras a 1.7 cada una
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaDomicilio comanda2 = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);
		assertEquals(3.45, tarjeta.getSaldoActual(), 0.001);

		ComandaDomicilio comanda3 = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		comanda3.addVendible(new Producto(NOMBRE, DESCRIPCION, 4.6, 10), 9); // 9 naranjas a 4.6 cada una
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3); // Esta comanda no deberia contar

		assertEquals((1.7 * 5 + 0.55) + (2 * 3 + 0.55), tpv.importeTotal(comanda1.getFecha().toLocalDate()), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void abreComandaLocalFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void abreComandaDomicilioFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cierraComandaDomicilioFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		tpv.cierraComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cierraComandaLocalFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.cierraComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaLocalFueraDeTPV() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaLocal comanda = new ComandaLocal();
		tpv.pagaComanda(comanda, tarjeta, CREDENCIAL_PAGO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaDomicilioFueraDeTPV() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		tpv.pagaComanda(comanda, tarjeta, CREDENCIAL_PAGO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void anulaComandaLocalFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void anulaComandaDlFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		tpv.anulaComanda(comanda);
	}

	@Test
	public void estadosComandaLocal() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.addComanda(comanda);

		tpv.cierraComanda(comanda);
		assertEquals(Estados.CERRADO, comanda.getEstado());
		tpv.abreComanda(comanda);
		assertEquals(Estados.ABIERTO, comanda.getEstado());
		tpv.anulaComanda(comanda);
		assertEquals(Estados.ANULADO, comanda.getEstado());
	}

	@Test
	public void estadosComandaDomicilio() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		tpv.addComanda(comanda);

		tpv.cierraComanda(comanda);
		assertEquals(Estados.CERRADO, comanda.getEstado());
		tpv.abreComanda(comanda);
		assertEquals(Estados.ABIERTO, comanda.getEstado());
		tpv.anulaComanda(comanda);
		assertEquals(Estados.ANULADO, comanda.getEstado());
	}

	@Test(expected = IllegalArgumentException.class)
	public void cambioAEstadoAnuladaLocal() {
		TPV tpv = new TPV();
		ComandaLocal comanda = new ComandaLocal();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);
		comanda.addVendible(new Producto(NOMBRE, DESCRIPCION, 1.7, 10), 5); // 5 peras a 1.7 cada una
		tpv.addComanda(comanda);
		tpv.pagaComanda(comanda, tarjeta, CREDENCIAL_PAGO);
		tpv.anulaComanda(comanda);

	}

	@Test(expected = IllegalArgumentException.class)
	public void cambioAEstadoAnuladaDomicilio() {
		TPV tpv = new TPV();
		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.addVendible(new Producto(NOMBRE, DESCRIPCION, 1.7, 10), 5); // 5 peras a 1.7 cada una
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);
		tpv.addComanda(comanda);
		tpv.pagaComanda(comanda, tarjeta, CREDENCIAL_PAGO);
		tpv.anulaComanda(comanda);

	}

	@Test
	public void comandasAnuladasLocal() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaLocal comanda1 = new ComandaLocal();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<Comanda> anuladas = tpv.comandasAnuladas(comanda1.getFecha().toLocalDate());

		assertTrue(anuladas.contains(comanda3));
		assertFalse(anuladas.contains(comanda2));
		assertFalse(anuladas.contains(comanda1));

	}

	@Test
	public void comandasAnuladasDomicilio() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaDomicilio comanda1 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaDomicilio comanda2 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);

		ComandaDomicilio comanda3 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<Comanda> anuladas = tpv.comandasAnuladas(comanda1.getFecha().toLocalDate());

		assertTrue(anuladas.contains(comanda3));
		assertFalse(anuladas.contains(comanda2));
		assertFalse(anuladas.contains(comanda1));

	}

	@Test
	public void comandasPagadasLocal() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaLocal comanda1 = new ComandaLocal();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<Comanda> pagadas = tpv.comandasPagadas(comanda1.getFecha().toLocalDate());

		assertTrue(pagadas.contains(comanda2));
		assertFalse(pagadas.contains(comanda3));
		assertFalse(pagadas.contains(comanda1));

	}

	@Test
	public void comandasPagadasDomicilio() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaDomicilio comanda1 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaDomicilio comanda2 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);

		ComandaDomicilio comanda3 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<Comanda> pagadas = tpv.comandasPagadas(comanda1.getFecha().toLocalDate());

		assertTrue(pagadas.contains(comanda2));
		assertFalse(pagadas.contains(comanda3));
		assertFalse(pagadas.contains(comanda1));

	}

	@Test
	public void comandasCerradasLocal() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaLocal comanda1 = new ComandaLocal();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<Comanda> cerradas = tpv.comandasCerradas(comanda1.getFecha().toLocalDate());

		assertTrue(cerradas.contains(comanda1));
		assertFalse(cerradas.contains(comanda2));
		assertFalse(cerradas.contains(comanda3));

	}

	@Test
	public void comandasCerradasDomicilio() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaDomicilio comanda1 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaDomicilio comanda2 = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		comanda2.addVendible(new Producto(NOMBRE, DESCRIPCION, 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, CREDENCIAL_PAGO);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<Comanda> cerradas = tpv.comandasCerradas(comanda1.getFecha().toLocalDate());

		assertTrue(cerradas.contains(comanda1));
		assertFalse(cerradas.contains(comanda2));
		assertFalse(cerradas.contains(comanda3));

	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaLocalMismoEstadoAbierta() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaDomicilioMismoEstadoAbierta() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaLocalMismoEstadoAnulada() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.ANULADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaDomicilioMismoEstadoAnulada() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setEstado(Estados.ANULADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaLocalMismoEstadoPagado() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.pagaComanda(comanda, tarjeta, CREDENCIAL_PAGO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaDomicilioMismoEstadoPagado() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero(CREDENCIAL_INIT, 10);

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA1);
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.pagaComanda(comanda, tarjeta, CREDENCIAL_PAGO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaLocalPagadaAAbierta() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaDomicilioPagadaAAbierta() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaLocalPagadaAAnulada() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaDomicilioPagadaAAnulada() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.ZONA2);
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaLocalPagadaACerrada() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.cierraComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaDomicilioPagadaACerrada() {
		TPV tpv = new TPV();

		ComandaDomicilio comanda = new ComandaDomicilio(DIRECCION, Zona.RESTO);
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.cierraComanda(comanda);
	}

}
