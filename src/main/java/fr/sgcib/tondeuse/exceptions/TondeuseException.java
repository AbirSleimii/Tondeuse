package fr.sgcib.tondeuse.exceptions;
/**
 * Classe exception qui peut etre liée aux instructions incorrectes, 
 * position incorrecte(dépasse la surface de la pelouse ou coordonnees négatives) 
 * ou aussi la position occupee par une autre tondeuse
 */
public class TondeuseException extends Exception {

	public TondeuseException(String message) {
		super(message);
	}
}
