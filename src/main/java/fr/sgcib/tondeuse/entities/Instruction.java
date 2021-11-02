package fr.sgcib.tondeuse.entities;

public enum Instruction {
/**
 * Les deplacements possibles de la tondeuse: Tourner a gauche/droite ou avancer
 */
		DROITE {
			public Position deplacer(Position currentLocation) {
				return currentLocation.tournerDroite();
			}
		},
		GAUCHE {
			public Position deplacer(Position currentLocation) {
				return currentLocation.tournerGauche();
			}
		},
		AVANCER {
			public Position deplacer(Position currentLocation) {
				return currentLocation.avancer();
			}
		};

		public abstract Position deplacer(Position location);
		public static final String INSTRUCTION_INCORRECTE = "instruction incorrecte";



}