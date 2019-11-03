package poo.uva.es.informaticafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Creación de una terminal de trabajo de tipo TPV. Nos permite crear una nueva comanda que será añadida
 * a la lista de comandas, cobrar anular o cerrar comandas, obtener una lista para cada uno de los estados
 * que puede tener la comanda en un día en concreto y obtener el importe total en un día en concreto
 * (con formato año-mes-día)
 * 
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

	/**
	 * Constructor de TPV
	 */
	public TPV() {
		ArrayList<Comanda> comandas;
	}

	/**
	 * Creación de una comanda sin atributos y la añade al TPV
	 */
	public void creaComandaSinAtributos() {
		comandas.add(comanda);
	}

	/**
	 * Creación de una comanda con atributos y la añade al TPV
	 * 
	 * @param estado
	 *            estado de la comanda, debe ser un entero entre 0 y 2
	 * @param fecha
	 *            fecha en formato año-mes-día
	 * @param productos
	 *            diccionario de {@code Producto} como key e {@code Integer}
	 */
	public void creaComandaConAtributos(int estado, LocalDate fecha, HashMap<Producto, Integer> productos) {
		Comanda comanda = new Comanda(estado, fecha, productos);
		comandas.add(comanda);
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
	 * Añadimos una nueva comanda a la lista de comandas que hay en el TPV
	 * 
	 * @param comanda
	 *            comanda que queremos añadir a la lista
	 */
	public void listaDeComandas(Comanda comanda) {
		comandas.add(comanda);
	}

	
	/**
	 * Lista de las comandas pagadas en un día en concreto
	 * @param fecha fecha de la que queremos saber la lista, debe estar en formato año-mes-día
	 * @return Una lista de comandas
	 *@throws IllegalArgumentException La lista de comandas debe tener comandas, si está vacia no puede generar ninguna lista
	 */
	public ArrayList<Comanda> comandasPagadasDeUnDia(LocalDate fecha) {
		
		if (comandas.isEmpty()) {
			throw new IllegalArgumentException("La lista de comandas no puede estar vacía");
		}
		int tamano = comandas.size();
		for (int i = 0; i < tamano; i++) {
			if (comanda.getEstado() == 2 && comanda.getFecha() == fecha) {
				importeTotal += comanda.importe();
				comandasPagadas.add(comanda);
			}
		}
		return comandasPagadas;
	}

	/**
	 * Calcula el importe total que se ha ganado en un día en concreto
	 * @param fecha fecha de la que queremos saber el importe en formato año-mes-día
	 * @return double con el importe total de ese día
	 */
	public double importeTotal(LocalDate fecha) {
		comandasPagadasDeUnDia(fecha);
		return importeTotal;
	}

	/**
	 * Nos da una lista con las comandas que han sido anuladas en un día en concreto
	 * @param fecha fecha de la que queremos conocer que comandas se han anulado en un día en concreto (con formato año-mes-día)
	 * @return Una lista de comandas
	 * @throws IllegalArgumentException La lista de comandas debe tener comandas, si está vacia no puede generar ninguna lista

	 */
	public ArrayList<Comanda> comandasAnuladasDeUnDia(LocalDate fecha) {
		if (comandas.isEmpty()) {
			throw new IllegalArgumentException("La lista de comandas no puede estar vacía");
		}
		int tamano = comandas.size();
		for (int i = 0; i < tamano; i++) {
			if (comanda.getEstado() == 1 && comanda.getFecha() == fecha) {
				importeTotal += comanda.importe();
				comandasAnuladas.add(comanda);
			}
		}
		return comandasAnuladas;
		
	}

	/**
	 * Nos da una lista con las comandas que han sido cerradas en un día en concreto
	 * @param fecha fecha de la que queremos conocer que comandas se han cerrado en un día en concreto (con formato año-mes-día)
	 * @return Una lista de comandas
	 * @throws IllegalArgumentException La lista de comandas debe tener comandas, si está vacia no puede generar ninguna lista
	 */
	public ArrayList<Comanda> comandasCerradasDeUnDia(LocalDate fecha) {
		if (comandas.isEmpty()) {
			throw new IllegalArgumentException("La lista de comandas no puede estar vacía");
		}
		int tamano = comandas.size();
		for (int i = 0; i < tamano; i++) {
			if (comanda.getEstado() == 0 && comanda.getFecha() == fecha) {
				importeTotal += comanda.importe();
				comandasCerradas.add(comanda);
			}
		}
		return comandasCerradas;
	}

}
