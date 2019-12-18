package poo.uva.es.informaticafe;

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

	public ComandaDomicilio(String direccion, Zona zona) {
		super();
		this.direccion = direccion;
		this.zona = zona;
		bolsas = 1;
	}

	private Zona getZona() {
		return zona;
	}
/**
 * Número de bolsas que tiene la {@link ComandaDomicilio}
 * @return Entero que corresponde al numero de bolsas
 */
	public int getBolsas() {
		return bolsas;
	}
/**
 * Consulta la dirección a la que tiene que ser enviada una {@link ComandaDomicilio}
 * @return String correspondiente a la dirección del envio
 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Cambia el número de bolsas de la comanda
	 * 
	 * @param bolsas entero que indica el nuevo número de bolsas
	 * @throws IllegalArgumentException No puedes pedir menos de una bolsa
	 */
	public void setBolsas(int bolsas) {
		if (bolsas < 1) {
			throw new IllegalArgumentException("No puedes pedir menos de una bolsa");
		}

		this.bolsas = bolsas;
	}

	/**
	 * Cambia la direeción de la comanda
	 * 
	 * @param direccion nueva direccion
	 * @throws IllegalArgumentException No puedes poner una dirrecion nula
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
	 * @param zona nuevo zona de la comanda
	 * @throws IllegalArgumentException No puedes poner una direccion nula;
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
		switch (getZona()) {

		case RESTO:
			dinero += 1.5;
		case ZONA2:
			dinero += 0.5;
		case ZONA1:
			dinero += 0.5 + getBolsas() * 0.05;
		}

		return dinero + super.importe();
	}
}
