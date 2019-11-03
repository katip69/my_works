package poo.uva.es.tests;

import org.junit.Test;
import poo.uva.es.informaticafe.Producto;
import poo.uva.es.informaticafe.Almacen;
import poo.uva.es.informaticafe.Comanda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class TPVTest {
	
	@Test
	public void modifica() {
		Producto manzana = new Producto("Manzana", "Fruta etc etc", 1.0);
		LocalDateTime fecha = LocalDateTime.now();
		HashMap<Producto, Integer> productos = new HashMap<Producto, Integer>();
		productos.put(manzana, 3);
		Comanda comanda = new Comanda(0, fecha, productos);
		comanda.setEstado(2);
		assertEquals(2,comanda.getEstado());
		
	}


}
