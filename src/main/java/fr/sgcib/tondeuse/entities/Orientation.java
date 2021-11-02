package fr.sgcib.tondeuse.entities;

public enum Orientation {
/**
 * Les orientations possibles de la tondeuse Nord(N), Sud(S), Est(E) et Ouest(W)
 */
	NORTH("N"), //
	SOUTH("S"), //
	EAST("E"), //
	WEST("W");

	private String direction;

	public static final String ORIENTATION_INCORRECTE = "orientation incorrecte";
	
	private Orientation(String name) {
		this.direction = name;
	}

	public String getDirection() {
		return direction;
	}

}
