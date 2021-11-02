package fr.sgcib.tondeuse.entities;

import org.apache.commons.lang.Validate;

public class Position {
	/**
	 * La position de la tondeuse est représentée par une combinaison de coordonnées (x,y)
	 */
	
	/**
	 * Coordonnee sur la surface selon l'axe horizontal.
	 */
	private int x;
	
	/**
	 * Coordonnee sur la surface selon l'axe vertical.
	 */
	private int y;
	
	private Orientation orientation;
	
	public static final String POSITION_INCORRECTE = "position incorrecte";
	
	/**
	 * Constructeur
	 * 
	 * @param x
	 *            Coordonnee sur la surface selon l'axe horizontal.
	 * @param y
	 *            Coordonnee sur la surface selon l'axe vertical.
	 */
	public Position(int x, int y, Orientation orientation) {
		Validate.notNull(orientation, "Direction can't be null.");
		
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	
	/**
	 * Retourne la coordonnee selon l'axe horizontal
	 * 
	 * @return x, la coordonnee de l'axe horizontal
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Retourne la coordonnee selon l'axe vertical
	 * 
	 * @return y, la coordonnee de l'axe vertical
	 */
	public int getY() {
		return y;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	/**
	 * Compare des coordonnees.
	 * 
	 * @param obj
	 *            Les coordonnees à comparer avec celle de l'objet courant.
	 * 
	 * @return true si les coordonnées sont les mêmes, false sinon
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean isEquals = false;
		if (obj instanceof Position) {
			Position coord = (Position) obj;
			isEquals = (this.x == coord.getX()) && (this.y == coord.getY()) && (this.orientation == coord.getOrientation());
		}
		return isEquals;
	}
	
	/**
	 * Tourner la tondeuse a droite selon l'orientation actuelle
	 * @return newOrientation : nouvelle orientation
	 */
	public Position tournerDroite() {
		Orientation newOrientation;
		switch (orientation) {
		case NORTH:
			newOrientation = Orientation.EAST;
			break;
		case EAST:
			newOrientation = Orientation.SOUTH;
			break;
		case SOUTH:
			newOrientation = Orientation.WEST;
			break;
		case WEST:
			newOrientation = Orientation.NORTH;
			break;
		default:
			throw new IllegalStateException();
		}
		return new Position(x, y, newOrientation);
	}

	/**
	 * Tourner la tondeuse a gauche selon l'orientation actuelle
	 * @return newOrientation : nouvelle orientation
	 */
	public Position tournerGauche() {
		Orientation newOrientation;
		switch (orientation) {
		case NORTH:
			newOrientation = Orientation.WEST;
			break;
		case EAST:
			newOrientation = Orientation.NORTH;
			break;
		case SOUTH:
			newOrientation = Orientation.EAST;
			break;
		case WEST:
			newOrientation = Orientation.SOUTH;
			break;
		default:
			throw new IllegalStateException();
		}
		return new Position(x, y, newOrientation);
	}

	/**
	 * Avancer la tondeuse selon l'orientation actuelle
	 * @return Position : nouvelle position
	 */
	public Position avancer() {
		switch (orientation) {
		case NORTH:
			return new Position(x, y + 1, orientation);
		case EAST:
			return new Position(x + 1, y, orientation);
		case SOUTH:
			return new Position(x, y - 1, orientation);
		case WEST:
			return new Position(x - 1, y, orientation);
		default:
			throw new IllegalStateException();
		}
	}

	public String toString() {
		return "[" + x + ";" + y + ";" + orientation.getDirection() + "]";
	}
}
