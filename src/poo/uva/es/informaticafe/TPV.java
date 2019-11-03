package poo.uva.es.informaticafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;

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
	private ArrayList<Comanda> comandasAnuladas;
	private ArrayList<Comanda> comandasCerradas;
	private ArrayList<Comanda> comandasPagadas;
	private double importeTotal;

	public TPV() {
		ArrayList<Comanda> comandas;
		ArrayList<Comanda> comandasAnuladas;
		ArrayList<Comanda> comandasCerradas;
		ArrayList<Comanda> comandasPagadas;
		double importeTotal;
	}

	/**
	 * Setter del estado de las comandas
	 * 
	 * @param estado
	 *            estado al que queremos cambiarlo
	 */
	public void modificaEstado(int estado) {
		comanda.setEstado(estado);
	}

	/**
	 * Lista de comandas totales
	 * 
	 * @param comanda
	 */
	public void listaDeComandas(Comanda comanda) {
		comandas.add(comanda);
	}

	/**
	 * Lista de comandas según su estado en un día en concreto
	 * 
	 * @param comandas
	 *            {@code ArrayList} con todas las comandas
	 * @param fecha
	 *            Fecha de la que se quiere saber la lista
	 */
	private void listaDeComandas(ArrayList<Comanda> comandas, LocalDateTime fecha) {
		if (comandas.isEmpty()) {
			throw new IllegalArgumentException("La lista de comandas no puede estar vacía");
		}
		int tamano = comandas.size();
		for (int i = 0; i < tamano; i++) {
			Comanda comanda = comandas.get(i);
			if (comanda.getEstado() == 0 && comanda.getFecha() == fecha) {
				// comanda anulada
				comandasAnuladas.add(comanda);
			}
			if (comanda.getEstado() == 1 && comanda.getFecha() == fecha) {
				// comanda cerrada
				comandasCerradas.add(comanda);
			}
			if (comanda.getEstado() == 2 && comanda.getFecha() == fecha) {
				// comanda Pagada
				importeTotal += comanda.importe();
				comandasPagadas.add(comanda);
			}
		}

	}

	/**
	 * Lista de las comandas pagadas en un día en concreto
	 * 
	 * @param comandas
	 *            {@code ArrayList} de todas las comandas
	 * @param fecha
	 *            Fecha de la que se quiere saber la lista de comandas pagadas
	 * @return Lista con todas las comandas pagadas en un día en concreto
	 */
	public ArrayList<Comanda> comandasPagadasDeUnDia(ArrayList<Comanda> comandas, LocalDateTime fecha) {
		listaDeComandas(comandas, fecha);
		return comandasPagadas;
	}

	/**
	 * Importe total de las comandas pagadas en un día en concreto
	 * 
	 * @param comandas
	 *            Lista de todas las comandas
	 * @param fecha
	 *            Fecha de la que queremos saber la ganancia que hubo.
	 * @return importe total facturado
	 */
	public double importeTotal(ArrayList<Comanda> comandas, LocalDateTime fecha) {
		listaDeComandas(comandas, fecha);
		return importeTotal;
	}

	/**
	 * Lista de las comandas anuladas en un día en concreto
	 * 
	 * @param comandas
	 *            {@code ArrayList} de todas las comandas
	 * @param fecha
	 *            Fecha de la que queremos saber la lista de comandas anuladas
	 * @return {@code ArrayList} con las comandas anuladas
	 */
	public ArrayList<Comanda> comandasAnuladasDeUnDia(ArrayList<Comanda> comandas, LocalDateTime fecha) {

		listaDeComandas(comandas, fecha);
		return comandasAnuladas;
	}

	/**
	 * Lista de las comandas cerradas en un día en concreto
	 * 
	 * @param comandas
	 *            {@code ArrayList} de todas las comandas que se han hecho
	 * @param fecha
	 *            Fecha de la que queremos saber cuantas comandas se cerraron
	 * @return lista con las comandas cerradas
	 */
	public ArrayList<Comanda> comandasCerradasDeUnDia(ArrayList<Comanda> comandas, LocalDateTime fecha) {
		listaDeComandas(comandas, fecha);
		return comandasCerradas;
	}

}
