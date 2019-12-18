package poo.uva.es.informaticafe;

/**
 * Gestión y control de los vendibles, los cuales implementan un nombre, su
 * descripción, un precio en formato double, y la cantidad de unidades
 * disponibles que hay de ese vendible.
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 1.0
 */
public abstract class Vendible {

	private String nombre;
	private String descripcion;

	/**
	 * Constructor del {@code Vendible}
	 * 
	 * @param nombre      Nombre del vendible
	 * @param descripcion Descripción del vendible
	 */
	public Vendible(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el nombre del vendible.
	 * 
	 * @return nombre del vendible
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Devuelve la descripcin del vendible.
	 * 
	 * @return descripcion del vendible
	 */
	public String descripcion() {
		return descripcion;
	}

	/**
	 * Devuelve el precio del vendible.
	 * 
	 * @return precio del vendible
	 */
	public abstract double precio();

	/**
	 * Devuelve las unidades disponibles del vendible.
	 * 
	 * @return cantidad de unidades disponibles
	 */
	public abstract int unidadesDisponibles();

}
