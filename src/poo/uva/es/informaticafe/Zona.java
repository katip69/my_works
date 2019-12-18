package poo.uva.es.informaticafe;


/**
 * La clase {@link Zona} nos permite establecer una de las 3 zonas que utilizará la clase
 * {@link ComandaDomicilio}. Dependiendo de la zona seleccionada se tendra en cuenta unos precios u otros. 
 * Estos precios también depende del numero de bolsas que tenga {@link ComandaDomicilio} de tal forma que cada bolsa
 * aumentara el precio en 0.05.
 * 
 * La Zona1 tiene un precio inicial de 0.5
 * La Zona2 tiene un precio inicial de 1
 * El resto de zonas tienen un precio inicial de 2.5
 * 
 * 
 * @author carlgom
 * @author manmend
 * @author migrase
 * @version 2.0
 *
 */
public enum Zona {
	ZONA1,ZONA2,RESTO
}
