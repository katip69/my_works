package poo.uva.es.informaticafe;

import java.util.Map;

/**
 * la clase {@link ComandaDomicilio} hereda de Comanda, resuelve el calculo del
 * importe e implementa algunos métodos nuevos
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */

public class ComandaDomicilio extends Comanda {

	private String direccion;
	private Zona zona;
	private int bolsas;

	public ComandaDomicilio() {
		super();
		direccion = " ";
		zona = Zona.ZONA1;
		bolsas = 0;
	}

	private Zona getZona() {
		return zona;
	}

	private int getBolsas() {
		return bolsas;
	}

	/**
	 * Cambia el número de bolsas de la comanda
	 * 
	 * @param bolsas
	 *            entero que indica el nuevo número de bolsas
	 */
	public void setBolsas(int bolsas) {
		this.bolsas = bolsas;
	}

	/**
	 * Cambia la direeción de la comanda
	 * 
	 * @param direcion
	 *            nueva direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Cambia la zona de la comanda
	 * 
	 * @param zona
	 *            nuevo zona de la comanda
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}

	/**
	 * Calcula el precio del importe de una comanda teniendo en cuenta la zona en la
	 * que se encuentre y el número de bolsas que se han pedido
	 */
	@Override
	public double importe() {
		double dinero = 0;

		if (super.getEstado() == Estados.ANULADO) {
			return dinero;
		}

		if (super.getEstado() == Estados.PAGADO) {

			switch (getZona()) {

			case RESTO:
				dinero += 1.5;
			case ZONA2:
				dinero += 0.5;
			case ZONA1:
				dinero += 0.5 + getBolsas() * 0.05;
			}

		}

		return dinero;
	}
}
