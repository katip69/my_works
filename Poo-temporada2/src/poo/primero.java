package poo;

/**
 * Implementacion del tipo de datos bla bla bla
 * 
 * @author rasero99
 *
 */
public class primero {
	private float x;
	private float y;

	/**
	 * crea un punto en unas coordenadas
	 * 
	 * @param x valor real
	 * @param y valor real
	 */

	public primero(float x, float y) {
		this.x = x;
		this.y = y;

	}
	/** crea un punto en el origen de coordenadas
	 * 
	 */
	public primero() {
		x=0;
		y=0;
	}
	/**
	 * Consulta de la coordenada x
	 * @return x
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Consulta de la coordenada y
	 * @return y
	 */
	public float getY() {
		return y;
	}
	/**
	 * Almacena la coordenada x en el punto
	 * @param x
	 * 
	 */
	public void setX(float x) {
		this.x=x;
	}
	/**
	 * Almacena la coordenada y en el punto
	 * @param y
	 */
	public void setY(float y) {
		this.y=y;
	}
	/**
	 * Sumamos dos puntos
	 * @param otro
	 * 
	 */
	public void suma (primero otro) {
		setX(otro.getX());
		setY(otro.getY());
		
		
	}
	/**
	 * imprime los datos del programa
	 * @return Sila coordenada x es 5.0 y la coordenada y es 7.0 entonces el resultado seria la cadena "(5.0,7.0)"
	 */
	public String cadenas() {
		return "(" +getX() +","+getY()+")";
	}	
	/**
	 * Consulta la distancia entre puntos 
	 * @param otro
	 * @return devuelve la distancia entre puntos 
	 */
	public double distacia(primero otro) {
		return Math.sqrt(Math.pow(getX()-otro.getX(),2)+Math.pow (getY()-otro.getY(),2));
		
		
	}

}
