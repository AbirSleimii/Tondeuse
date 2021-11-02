package fr.sgcib.tondeuse.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sgcib.tondeuse.entities.Instruction;
import fr.sgcib.tondeuse.entities.Pelouse;
import fr.sgcib.tondeuse.entities.Position;
import fr.sgcib.tondeuse.entities.Tondeuse;
import fr.sgcib.tondeuse.exceptions.TondeuseException;

public class PelouseService {
	private List<Tondeuse> tondeuses;
	private Pelouse pelouse;

	private static Logger logger = LoggerFactory.getLogger(PelouseService.class);

	public PelouseService() {
		tondeuses = new ArrayList<>();
	}

	public void startTondeuses() throws TondeuseException {

		// la tondeuse se déplace l'une après l'autre
		for (Tondeuse tondeuse : tondeuses) {

			Queue<Instruction> instructions = tondeuse.getInstructionsTondeuse();

			// les tondeuses exécutent leur instruction de manière séquentielle
			while (!instructions.isEmpty()) {
				Instruction instruction = instructions.poll();
				Position currentPosition = tondeuse.getCoordonneesTondeuse();
				Position newPosition = instruction.deplacer(tondeuse.getCoordonneesTondeuse());

				// en cas d'avancement, il y a différentes possibilités
				if (instruction == Instruction.AVANCER) {

					if (!estDansPelouse(pelouse, newPosition)) { // hors limites : ne rien faire
						continue;
					}

					else if (estOccupee(pelouse, newPosition)) { // déjà occupé : exception
						throw new TondeuseException("Tondeuse new location " + newPosition + " is already occupied.");
					}

					else { // tout semble bon ici, passons à la tondeuse
						tondeuse.setCoordonneesTondeuse(newPosition);
					}
				}
				
				else {
					// toujours appliquer les instructions de rotation
					tondeuse.setCoordonneesTondeuse(newPosition);
				}
			

				logger.info("tondeuse se deplace de : " + currentPosition + " a " + newPosition + ". " + tondeuse.getInstructionsTondeuse().size() + " instructions restantes.");

			}
		}
	}

	private boolean estOccupee(Pelouse pelouse, Position location) {
		for (Tondeuse m : tondeuses) {
			Position tondeusePosition = m.getCoordonneesTondeuse();
			if (tondeusePosition.getX() == location.getX() && tondeusePosition.getY() == location.getY()) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * vérifier si la position de la tondeuse après mouvement est en dehors de la pelouse

	 * @param coordonnees 
	 * @return true si la position de la tondeuse est à l'intérieur de la pelouse
	 */
	public boolean estDansPelouse(Pelouse pelouse, Position coordonnees){
		return coordonnees.getX() >= 0 
				&& coordonnees.getY() >= 0
				&& coordonnees.getX() <= pelouse.getWidth()
				&& coordonnees.getY() <= pelouse.getHeight();
	}

	public void addTondeuse(Tondeuse tondeuse) throws TondeuseException {
		Validate.notNull(pelouse, "ne peut pas ajouter une tondeuse sans avoir une pelouse");

		Position tondeusePosition = tondeuse.getCoordonneesTondeuse();

		if (estOccupee(pelouse, tondeusePosition)) {
			throw new TondeuseException("la position " + tondeusePosition + " est occupee par une autre tondeuse.");
		}
		if (!estDansPelouse(pelouse, tondeusePosition)) {
			throw new TondeuseException("la position " + tondeusePosition + " est en dehors de la pelouse.");
		}

		tondeuses.add(tondeuse); // c'est correcte, ajouter la tondeuse a la liste des tondeuses
	}

	public String getTondeusesPositions() {
		StringBuilder sb = new StringBuilder();

		for (Tondeuse tondeuse : tondeuses) {

			if (sb.length() > 0) {
				sb.append("\n");
			}

			Position tondeusePosition = tondeuse.getCoordonneesTondeuse();
			sb.append(tondeusePosition.getX()).append(" ");
			sb.append(tondeusePosition.getY()).append(" ");
			sb.append(tondeusePosition.getOrientation().getDirection());
		}

		return sb.toString();
	}

	public List<Tondeuse> getTondeuses() {
		return tondeuses;
	}

	public void setTondeuses(List<Tondeuse> tondeuses) {
		this.tondeuses = tondeuses;
	}

	public Pelouse getPelouse() {
		return pelouse;
	}

	public void setPelouse(Pelouse pelouse) {
		this.pelouse = pelouse;
	}
}
