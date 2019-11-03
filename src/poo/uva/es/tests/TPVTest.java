package poo.uva.es.tests;

import java.time.LocalDate;
import java.util.HashMap;



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
	public void creaComandaSinAtributos() {
		TPV prueba = new TPV();
		prueba.creaComandaSinAtributos();
		assertFalse(prueba.vacia());
	}

	@Test
	public void creaComandaConAtributos() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		int estado=1;
		prueba.creaComandaConAtributos(estado, fecha, productos);

		
		assertFalse(prueba.vacia());
	}

	@Test
	public void modifcaEstado() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		int estado=1;
		prueba.creaComandaConAtributos(estado,fecha,productos);
		prueba.modificaEstado(2);
		assertEquals(2,prueba.getComanda().get(0).getEstado());
	}
	@Test
	public void anadeComandas() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		int estado=1;
		Comanda comandaPrueba=new Comanda(estado,fecha,productos);
		prueba.listaDeComandas(comandaPrueba);
		assertEquals(comandaPrueba,prueba.getComanda().get(0));
	}
	@Test
	public void listaVacia() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		int estado=2;
		Comanda comandaPrueba=new Comanda(estado,fecha,productos);
		prueba.creaComandaConAtributos(estado,fecha,productos);
		assertEquals(comandaPrueba.getEstado(),prueba.comandasPagadasDeUnDia(fecha).get(0).getEstado());
		assertEquals(comandaPrueba.getFecha(),prueba.comandasPagadasDeUnDia(fecha).get(0).getFecha());
	
	}
	
	@Test
	public void precioTotal() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 1);
		int estado=2;
		prueba.creaComandaConAtributos(estado,fecha,productos);
		assertEquals(1.0,prueba.importeTotal(fecha),0.001);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void listaIsEmpty() {
		TPV prueba = new TPV();
		LocalDate fecha = LocalDate.now();
		prueba.comandasPagadasDeUnDia(fecha);
		
	}
	@Test
	public void listaComandasAnuladas() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		int estado=1;
		Comanda comandaPrueba=new Comanda(estado,fecha,productos);
		prueba.creaComandaConAtributos(estado,fecha,productos);
		assertEquals(comandaPrueba.getEstado(),prueba.comandasAnuladasDeUnDia(fecha).get(0).getEstado());
		assertEquals(comandaPrueba.getFecha(),prueba.comandasAnuladasDeUnDia(fecha).get(0).getFecha());
	
	}
	@Test(expected = IllegalArgumentException.class)
	public void listaIsEmptyAnuladas() {
		TPV prueba = new TPV();
		LocalDate fecha = LocalDate.now();
		prueba.comandasAnuladasDeUnDia(fecha);
		
	}
	
	@Test
	public void listaComandasCerradas() {
		TPV prueba = new TPV();
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDate fecha = LocalDate.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		int estado=0;
		Comanda comandaPrueba=new Comanda(estado,fecha,productos);
		prueba.creaComandaConAtributos(estado,fecha,productos);
		assertEquals(comandaPrueba.getEstado(),prueba.comandasCerradasDeUnDia(fecha).get(0).getEstado());
		assertEquals(comandaPrueba.getFecha(),prueba.comandasCerradasDeUnDia(fecha).get(0).getFecha());
	
	}
	@Test(expected = IllegalArgumentException.class)
	public void listaIsEmptyCerradas() {
		TPV prueba = new TPV();
		LocalDate fecha = LocalDate.now();
		prueba.comandasCerradasDeUnDia(fecha);
		
	}
}
