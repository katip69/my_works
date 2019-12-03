package poo.uva.es.tests;

import java.util.ArrayList;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Comanda;
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

		Comanda comanda1 = new Comanda();
		comanda1.addProducto(new Producto("Pera", "", 1.7, 10), 5); // 5 peras a 1.7 cada una
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		comanda2.addProducto(new Producto("Manzana", "", 2, 10), 3); // 3 manzanas a 2 cada una
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		comanda3.addProducto(new Producto("Naranja", "", 4.6, 10), 9); // 9 naranjas a 4.6 cada una
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3); // Esta comanda no deberia contar

		assertEquals(1.7 * 5 + 2 * 3, tpv.importeTotal(comanda1.getFecha().toLocalDate()), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void abreComandaFueraDeTPV() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		tpv.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cierraComandaFueraDeTPV() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		tpv.cierraComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaFueraDeTPV() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		tpv.pagaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void anulaComandaFueraDeTPV() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		tpv.anulaComanda(comanda);
	}

	@Test
	public void reabreComanda() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		tpv.addComanda(comanda);

		tpv.cierraComanda(comanda);
		assertEquals(Estados.CERRADO, comanda.getEstado());
		tpv.abreComanda(comanda);
		assertEquals(Estados.ABIERTO, comanda.getEstado());
	}

	@Test
	public void comandasAnuladas() {
		TPV tpv = new TPV();

		Comanda comanda1 = new Comanda();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		ArrayList<Comanda> anuladas = tpv.comandasAnuladas(comanda1.getFecha().toLocalDate());

		assertTrue(anuladas.contains(comanda3));
		assertFalse(anuladas.contains(comanda2));
		assertFalse(anuladas.contains(comanda1));

	}

	@Test
	public void comandasPagadas() {
		TPV tpv = new TPV();

		Comanda comanda1 = new Comanda();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		ArrayList<Comanda> pagadas = tpv.comandasPagadas(comanda1.getFecha().toLocalDate());

		assertTrue(pagadas.contains(comanda2));
		assertFalse(pagadas.contains(comanda3));
		assertFalse(pagadas.contains(comanda1));

	}

	@Test
	public void comandasCerradas() {
		TPV tpv = new TPV();

		Comanda comanda1 = new Comanda();
		tpv.addComanda(comanda1);
		tpv.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		tpv.addComanda(comanda2);
		tpv.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		tpv.addComanda(comanda3);
		tpv.anulaComanda(comanda3);

		ArrayList<Comanda> cerradas = tpv.comandasCerradas(comanda1.getFecha().toLocalDate());

		assertTrue(cerradas.contains(comanda1));
		assertFalse(cerradas.contains(comanda2));
		assertFalse(cerradas.contains(comanda3));

	}

	@Test(expected = IllegalArgumentException.class)
	public void comandaMismoEstadoAbierta() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void comandaMismoEstadoAnulada() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		comanda.setEstado(Estados.ANULADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}
	@Test(expected = IllegalArgumentException.class)
	public void comandaMismoEstadoPagado() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.pagaComanda(comanda);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaPagadaAAbierta() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.abreComanda(comanda);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaPagadaAAnulada() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.anulaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void modificaComandaPagadaACerrada() {
		TPV tpv = new TPV();

		Comanda comanda = new Comanda();
		comanda.setEstado(Estados.PAGADO);
		tpv.addComanda(comanda);
		tpv.cierraComanda(comanda);
	}
	
}
