package fr.sgcib.tondeuse.exceptions;
/**
 * Classe exception qui peut etre li�e aux instructions incorrectes, 
 * position incorrecte(d�passe la surface de la pelouse ou coordonnees n�gatives) 
 * ou aussi la position occupee par une autre tondeuse
 */
public class TondeuseException extends Exception {

	public TondeuseException(String message) {
		super(message);
	}
}
