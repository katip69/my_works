package poo.uva.es.informaticafe;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Creación de una terminal de trabajo de tipo TPV. Nos permite crear una nueva
 * comanda que será añadida a la lista de comandas, cobrar anular o cerrar
 * comandas, obtener una lista para cada uno de los estados que puede tener la
 * comanda en un día en concreto y obtener el importe total en un día en
 * concreto (con formato año-mes-día).
 * 
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public class TPV {

	private ArrayList<ComandaLocal> comandas;

	/**
	 * Constructor de TPV
	 */
	public TPV() {
		comandas = new ArrayList<>();
	}

	private ArrayList<ComandaLocal> getComandas() {
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
		for (ComandaLocal comanda : getComandas()) {
			// Suma el precio de cada producto por la cantidad al importe
			if (comanda.getFecha().toLocalDate().equals(fecha)) {
				importe += comanda.importe();
			}
		}

		return importe;
	}

	private ArrayList<ComandaLocal> comandasConEstado(LocalDate fecha, Estados estado) {

		ArrayList<ComandaLocal> comandasAnuladas = new ArrayList<>();

		for (ComandaLocal comanda : getComandas()) {
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
	public List<ComandaLocal> comandasAnuladas(LocalDate fecha) {

		return comandasConEstado(fecha, Estados.ANULADO);

	}

	/**
	 * Nos da una lista con las comandas que han sido cerradas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han cerrado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public List<ComandaLocal> comandasCerradas(LocalDate fecha) {
		return comandasConEstado(fecha, Estados.CERRADO);
	}

	/**
	 * Nos da una lista con las comandas que han sido cerradas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han cerrado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public List<ComandaLocal> comandasPagadas(LocalDate fecha) {
		return comandasConEstado(fecha, Estados.PAGADO);
	}

	/**
	 * Añadimos una nueva comanda a la lista de comandas que hay en el TPV
	 * 
	 * @param comanda comanda que queremos añadir a la lista
	 */
	public void addComanda(ComandaLocal comanda) {
		comandas.add(comanda);
	}

	/**
	 * Anula una comanda.
	 * 
	 * @param comanda a ser anulada
	 */
	public void anulaComanda(ComandaLocal comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda que se quiere modificar no es parte de este TPV");
		}
		comanda.setEstado(Estados.ANULADO);
	}

	/**
	 * Marca una comanda como pagada.
	 * 
	 * @param comanda a pagar
	 * @param tarjeta TarjetaMonedero de la que descontar el saldo
	 * @param credencial Credencial para autorizar el pago de la tarjeta
	 * @throws IllegalArgumentException si la comanda a modificar no es parte del TPV
	 */
	public void pagaComanda(ComandaLocal comanda, TarjetaMonedero tarjeta, String credencial) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda no pertenece a este TPV.");
		}
		
		tarjeta.descontarDelSaldo(credencial, comanda.importe());
		comanda.setEstado(Estados.PAGADO);
	}

	/**
	 * Cierra una comanda.
	 * 
	 * @param comanda a ser cerrada
	 */
	public void cierraComanda(ComandaLocal comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda a modificar no es parte de este TPV.");
		}
		comanda.setEstado(Estados.CERRADO);
	}

	/**
	 * Abre una comanda.
	 * 
	 * @param comanda a ser abierta
	 */
	public void abreComanda(ComandaLocal comanda) {
		if (!getComandas().contains(comanda)) {
			throw new IllegalArgumentException("La comanda a modificar no es parte de este TPV.");
		}
		comanda.setEstado(Estados.ABIERTO);
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
