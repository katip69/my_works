package poo.uva.es.informaticafe;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Representacion de un Terminal de Punto de Venta (TPV).
 * 
 * El TPV se encarga de gestionar la creacion de nuevas comandas, su transicion
 * a distintos estados, el cobro de comandas y la organizacion de comandas por
 * fecha.
 * 
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
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

	private ArrayList<Comanda> comandasConEstado(LocalDate fecha, Estados estado) {

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
	public List<Comanda> comandasAnuladas(LocalDate fecha) {

		return comandasConEstado(fecha, Estados.ANULADO);

	}

	/**
	 * Nos da una lista con las comandas que han sido cerradas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han cerrado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public List<Comanda> comandasCerradas(LocalDate fecha) {
		return comandasConEstado(fecha, Estados.CERRADO);
	}

	/**
	 * Nos da una lista con las comandas que han sido pagadas en un día en concreto
	 * 
	 * @param fecha fecha de la que queremos conocer que comandas se han pagado en
	 *              un día en concreto
	 * @return Una lista de comandas
	 */
	public List<Comanda> comandasPagadas(LocalDate fecha) {
		return comandasConEstado(fecha, Estados.PAGADO);
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
			throw new IllegalArgumentException("La comanda que se quiere modificar no es parte de este TPV");
		}
		comanda.setEstado(Estados.ANULADO);
	}

	/**
	 * Marca una comanda como pagada.
	 * 
	 * @param comanda    a pagar
	 * @param tarjeta    TarjetaMonedero de la que descontar el saldo
	 * @param credencial Credencial para autorizar el pago de la tarjeta
	 * @throws IllegalArgumentException si la comanda a modificar no es parte del
	 *                                  TPV
	 */
	public void pagaComanda(Comanda comanda, TarjetaMonedero tarjeta, String credencial) {
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
	public void cierraComanda(Comanda comanda) {
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
	public void abreComanda(Comanda comanda) {
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
