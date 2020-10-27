package poo.uva.es.informaticafe;

/**
 * Implementacion de una comanda realizada de manera remota, y que necesita ser
 * repartida a un domicilio.
 * 
 * Toda comanda a domicilio ha de tener asignada una direccion de reparto, y una
 * {@link Zona} para calcular los gastos especificos para el reparto.
 * 
 * Las comandas a domicilio tambien traen un coste relacionado al numero de
 * bolsas de plastico utilizadas para empaquetar la comanda.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 */

public class ComandaDomicilio extends Comanda {

	private String direccion;
	private Zona zona;
	private int bolsas;

	/**
	 * Constructor de una comanda a domicilio.
	 * 
	 * Se asume que se utiliza una unica bolsa para empaquetar la comanda.
	 * 
	 * @param direccion Direccion de entrega del pedido
	 * @param zona      Zona asignada al pedido
	 */
	public ComandaDomicilio(String direccion, Zona zona) {
		super();
		this.direccion = direccion;
		this.zona = zona;
		bolsas = 1;
	}

	/**
	 * Devuelve la zona asignada a la comanda
	 * 
	 * @return zona asignada a la comanda
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Número de bolsas usadas para empaquetar la comanda.
	 * 
	 * @return numero de bolsas utilizadas
	 */
	public int getBolsas() {
		return bolsas;
	}

	/**
	 * Consulta la dirección a la que tiene que ser enviada una
	 * {@link ComandaDomicilio}
	 * 
	 * @return direccion asignada a la comanda
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Cambia el número de bolsas de la comanda
	 * 
	 * @param bolsas nuevo numero de bolsas asignadas a la comanda
	 * @throws IllegalArgumentException si el numero de bolsas es menor a 1
	 */
	public void setBolsas(int bolsas) {
		if (bolsas < 1) {
			throw new IllegalArgumentException("No puedes pedir menos de una bolsa");
		}

		this.bolsas = bolsas;
	}

	/**
	 * Cambia la direccion asignada a la comanda
	 * 
	 * @param direccion nueva direccion
	 * @throws IllegalArgumentException si la direccion no esta inicializada
	 */
	public void cambiaDireccion(String direccion) {
		if (direccion == null) {
			throw new IllegalArgumentException("No puedes poner una direccion nula");
		}
		this.direccion = direccion;
	}

	/**
	 * Cambia la zona de la comanda
	 * 
	 * @param zona nueva zona donde repartir la comanda
	 * @throws IllegalArgumentException si la direccion no esta inicializada
	 * 
	 */
	public void setZona(Zona zona) {
		if (zona == null) {
			throw new IllegalArgumentException("No puedes poner una direccion nula");
		}
		this.zona = zona;
	}

	/**
	 * Calcula el precio del importe de una comanda teniendo en cuenta la zona en la
	 * que se encuentre y el número de bolsas que se han pedido
	 */
	@Override
	public double importe() {
		double dinero = 0;

		if (getEstado() == Estados.ANULADO) {
			return dinero;
		}

		if (getEstado() == Estados.PAGADO) {
			return super.importe();
		}
		
		switch (getZona()) {
		case RESTO:
			dinero += 1.5;
		case ZONA2:
			dinero += 0.5;
		case ZONA1:
			dinero += 0.5 + getBolsas() * 0.05;
			break;
		}

		return dinero + super.importe();
	}
}
