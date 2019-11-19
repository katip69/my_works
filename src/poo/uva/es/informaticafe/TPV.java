package poo.uva.es.informaticafe;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Creación de una terminal de trabajo de tipo TPV. Nos permite crear una nueva
 * comanda que será añadida a la lista de comandas, cobrar anular o cerrar
 * comandas, obtener una lista para cada uno de los estados que puede tener la
 * comanda en un día en concreto y obtener el importe total en un día en
 * concreto (con formato año-mes-día)
 * 
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class TPV {

	private ArrayList<Comanda> comandas;

	/**
	 * Constructor de TPV
	 */
	public TPV() {
		comandas = new ArrayList<>();
	}

	private ArrayList<Comanda> getComandas() {
		return comandas;
	}

	/**
	 * Calcula el importe total que se ha ganado en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos saber el importe
	 * @return double con el importe total de ese día
	 */
	public double importeTotal(LocalDate fecha) {
		double importe = 0;

		// Itera sobre los pares HashMap creando un Map individual por cada par
		for (Comanda comanda : getComandas()) {
			// Suma el precio de cada producto por la cantidad al importe
			if (comanda.getFecha().toLocalDate().equals(fecha)) {
				importe += comanda.importe();
			}
		}

		return importe;
	}

	private ArrayList<Comanda> comandasConEstado(LocalDate fecha, int estado) {

		ArrayList<Comanda> comandasAnuladas = new ArrayList<>();

		for (Comanda comanda : getComandas()) {
			if (comanda.getEstado() == estado && comanda.getFecha().toLocalDate().equals(fecha)) {
				comandasAnuladas.add(comanda);
			}
		}

		return comandasAnuladas;

	}

	/**
	 * Nos da una lista con las comandas que han sido anuladas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han anulado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public ArrayList<Comanda> comandasAnuladas(LocalDate fecha) {

		return comandasConEstado(fecha, 3);

	}

	/**
	 * Nos da una lista con las comandas que han sido cerradas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han cerrado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public ArrayList<Comanda> comandasCerradas(LocalDate fecha) {
		return comandasConEstado(fecha, 1);
	}

	/**
	 * Nos da una lista con las comandas que han sido cerradas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han cerrado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public ArrayList<Comanda> comandasPagadas(LocalDate fecha) {
		return comandasConEstado(fecha, 2);
	}

	/**
	 * Añadimos una nueva comanda a la lista de comandas que hay en el TPV
	 * 
	 * @param comanda comanda que queremos añadir a la lista
	 */
	public void addComanda(Comanda comanda) {
		comandas.add(comanda);
	}

	/**
	 * Anula una comanda.
	 * 
	 * @param comanda a ser anulada
	 */
	public void anulaComanda(Comanda comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda a modificar no es parte de este TPV.");
		}
		comanda.setEstado(3);
	}

	/**
	 * Marca una comanda como pagada.
	 * 
	 * @param comanda pagada
	 */
	public void pagaComanda(Comanda comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda a modificar no es parte de este TPV.");
		}
		comanda.setEstado(2);
	}

	/**
	 * Cierra una comanda.
	 * 
	 * @param comanda a ser cerrada
	 */
	public void cierraComanda(Comanda comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda a modificar no es parte de este TPV.");
		}
		comanda.setEstado(1);
	}

	/**
	 * Abre una comanda.
	 * 
	 * @param comanda a ser abierta
	 */
	public void abreComanda(Comanda comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda a modificar no es parte de este TPV.");
		}
		comanda.setEstado(0);
	}

	/**
	 * Comprueba si hay alguna comanda en el TPV.
	 * 
	 * @return true si no hay comandas en el TPV, false en caso contrario
	 */
	public boolean vacia() {
		return getComandas().isEmpty();
	}

}
