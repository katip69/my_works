package poo.uva.es.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.ComandaLocal;
import poo.uva.es.informaticafe.TPV;
import poo.uva.es.informaticafe.Estados;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
	
/**
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class TPVTest {

	@Test
	public void creaTPV() {
		TPV tpv = new TPV();

		assertTrue(tpv.vacia());
	}

	@Test
	public void calcularImporte() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";



		ComandaLocal comanda1 = new ComandaLocal();
		comanda1.addProducto(new Producto("Pera", "", 1.7, 10), 5); // 5 peras a 1.7 cada una
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addProducto(new Producto("Manzana", "", 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, credencial);
		assertEquals(4, tarjeta.getSaldoActual(), 0.001);

		ComandaLocal comanda3 = new ComandaLocal();
		comanda3.addProducto(new Producto("Naranja", "", 4.6, 10), 9); // 9 naranjas a 4.6 cada una
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3); // Esta comanda no deberia contar

		assertEquals(1.7 * 5 + 2 * 3, tpv.importeTotal(comanda1.getFecha().toLocalDate()), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void abreComandaFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cierraComandaFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.cierraComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaFueraDeTPV() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";


		ComandaLocal comanda = new ComandaLocal();
		tpv.pagaComanda(comanda, tarjeta, credencial);
	}

	@Test(expected = IllegalArgumentException.class)
	public void anulaComandaFueraDeTPV() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.anulaComanda(comanda);
	}

	@Test
	public void estadosComanda() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.addComanda(comanda);
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";

		tpv.cierraComanda(comanda);
		assertEquals(Estados.CERRADO, comanda.getEstado());
		tpv.abreComanda(comanda);
		assertEquals(Estados.ABIERTO, comanda.getEstado());
		tpv.anulaComanda(comanda);
		assertEquals(Estados.ANULADO, comanda.getEstado());
		tpv.pagaComanda(comanda,tarjeta,credencial);
		assertEquals(Estados.PAGADO, comanda.getEstado());
	}

	@Test
	public void comandasAnuladas() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";

		ComandaLocal comanda1 = new ComandaLocal();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addProducto(new Producto("Manzana", "", 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, credencial);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<ComandaLocal> anuladas = tpv.comandasAnuladas(comanda1.getFecha().toLocalDate());

		assertTrue(anuladas.contains(comanda3));
		assertFalse(anuladas.contains(comanda2));
		assertFalse(anuladas.contains(comanda1));

	}

	@Test
	public void comandasPagadas() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";


		ComandaLocal comanda1 = new ComandaLocal();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addProducto(new Producto("Manzana", "", 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, credencial);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<ComandaLocal> pagadas = tpv.comandasPagadas(comanda1.getFecha().toLocalDate());

		assertTrue(pagadas.contains(comanda2));
		assertFalse(pagadas.contains(comanda3));
		assertFalse(pagadas.contains(comanda1));

	}

	@Test
	public void comandasCerradas() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";


		ComandaLocal comanda1 = new ComandaLocal();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		ComandaLocal comanda2 = new ComandaLocal();
		comanda2.addProducto(new Producto("Manzana", "", 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2, tarjeta, credencial);

		ComandaLocal comanda3 = new ComandaLocal();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		List<ComandaLocal> cerradas = tpv.comandasCerradas(comanda1.getFecha().toLocalDate());

		assertTrue(cerradas.contains(comanda1));
		assertFalse(cerradas.contains(comanda2));
		assertFalse(cerradas.contains(comanda3));

	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaMismoEstadoAbierta() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaMismoEstadoAnulada() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.ANULADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaMismoEstadoPagado() {
		TPV tpv = new TPV();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10);
		String credencial = "6Z1y00Nm31aA-571";

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.pagaComanda(comanda, tarjeta, credencial);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaPagadaAAbierta() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaPagadaAAnulada() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaPagadaACerrada() {
		TPV tpv = new TPV();

		ComandaLocal comanda = new ComandaLocal();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.cierraComanda(comanda);
	}

}
