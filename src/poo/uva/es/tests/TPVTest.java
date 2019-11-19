package poo.uva.es.tests;

import java.util.ArrayList;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Comanda;
import poo.uva.es.informaticafe.TPV;
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
		TPV prueba = new TPV();

		assertTrue(prueba.vacia());
	}

	@Test
	public void calcularImporte() {
		TPV prueba = new TPV();

		Comanda comanda1 = new Comanda();
		comanda1.addProducto(new Producto("Pera", "", 1.7, 10), 5); // 5 peras a 1.7 cada una
		prueba.addComanda(comanda1);
		prueba.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		comanda2.addProducto(new Producto("Manzana", "", 2, 10), 3); // 3 manzanas a 2 cada una
		prueba.addComanda(comanda2);
		prueba.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		comanda3.addProducto(new Producto("Naranja", "", 4.6, 10), 9); // 9 naranjas a 4.6 cada una
		prueba.addComanda(comanda3);
		prueba.anulaComanda(comanda3); // Esta comanda no deberia contar

		assertEquals(1.7 * 5 + 2 * 3, prueba.importeTotal(comanda1.getFecha().toLocalDate()), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void abreComandaFueraDeTPV() {
		TPV prueba = new TPV();

		Comanda comanda = new Comanda();
		prueba.abreComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cierraComandaFueraDeTPV() {
		TPV prueba = new TPV();

		Comanda comanda = new Comanda();
		prueba.cierraComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void pagaComandaFueraDeTPV() {
		TPV prueba = new TPV();

		Comanda comanda = new Comanda();
		prueba.pagaComanda(comanda);
	}

	@Test(expected = IllegalArgumentException.class)
	public void anulaComandaFueraDeTPV() {
		TPV prueba = new TPV();

		Comanda comanda = new Comanda();
		prueba.anulaComanda(comanda);
	}
	
	@Test
	public void reabreComanda() {
		TPV prueba = new TPV();

		Comanda comanda = new Comanda();
		prueba.addComanda(comanda);
	
		prueba.cierraComanda(comanda);
		prueba.abreComanda(comanda);
	}

	@Test
	public void comandasAnuladas() {
		TPV prueba = new TPV();

		Comanda comanda1 = new Comanda();
		prueba.addComanda(comanda1);
		prueba.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		prueba.addComanda(comanda2);
		prueba.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		prueba.addComanda(comanda3);
		prueba.anulaComanda(comanda3);

		ArrayList<Comanda> anuladas = prueba.comandasAnuladas(comanda1.getFecha().toLocalDate());

		assertTrue(anuladas.contains(comanda3));
		assertFalse(anuladas.contains(comanda2));
		assertFalse(anuladas.contains(comanda1));

	}

	@Test
	public void comandasPagadas() {
		TPV prueba = new TPV();

		Comanda comanda1 = new Comanda();
		prueba.addComanda(comanda1);
		prueba.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		prueba.addComanda(comanda2);
		prueba.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		prueba.addComanda(comanda3);
		prueba.anulaComanda(comanda3);

		ArrayList<Comanda> pagadas = prueba.comandasPagadas(comanda1.getFecha().toLocalDate());

		assertTrue(pagadas.contains(comanda2));
		assertFalse(pagadas.contains(comanda3));
		assertFalse(pagadas.contains(comanda1));

	}

	@Test
	public void comandasCerradas() {
		TPV prueba = new TPV();

		Comanda comanda1 = new Comanda();
		prueba.addComanda(comanda1);
		prueba.cierraComanda(comanda1);

		Comanda comanda2 = new Comanda();
		prueba.addComanda(comanda2);
		prueba.pagaComanda(comanda2);

		Comanda comanda3 = new Comanda();
		prueba.addComanda(comanda3);
		prueba.anulaComanda(comanda3);

		ArrayList<Comanda> cerradas = prueba.comandasCerradas(comanda1.getFecha().toLocalDate());

		assertTrue(cerradas.contains(comanda1));
		assertFalse(cerradas.contains(comanda2));
		assertFalse(cerradas.contains(comanda3));

	}

}
