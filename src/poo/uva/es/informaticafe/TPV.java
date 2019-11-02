package poo.uva.es.informaticafe;

import java.util.ArrayList;

/**
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class TPV {

	private Comanda comanda;
	private ArrayList<Comanda> comandas;

	/**
	 * modificamos el estado de las comandas
	 */
	public void modificaEstado(int estado) {

		if (comanda.getEstado() == 2) {
			comanda.setEstado(estado);
		}
	}

	/**
	 * guardamos las comandas del dia
	 * 
	 */
	public void listaDeComandas(Comanda comanda) {
		comandas.add(comanda);
	}

	/**
	 * guardamos las comandas segun su estado
	 */
	public void listaDeLasComandas(ArrayList<Comanda> comandas) {
		double totalImporte = 0;
		int tamaño = comandas.size();
		for (int i = 0; i < tamaño; i++) {
			Comanda comanda = comandas.get(i);
			if (comanda.getEstado() == 0) {
				// comanda anulada
				ArrayList<Comanda> comandasAnuladas = new ArrayList<Comanda>();
				comandasAnuladas.add(comanda);
			}
			if (comanda.getEstado() == 1) {
				// comanda cerrada
				ArrayList<Comanda> comandasCerradas = new ArrayList<Comanda>();
				comandasCerradas.add(comanda);
			}
			if (comanda.getEstado() == 3) {
				// comanda Pagada
				ArrayList<Comanda> comandasPagadas = new ArrayList<Comanda>();
				totalImporte = totalImporte + comanda.importe();
				comandasPagadas.add(comanda);
			}
		}

	}

}
