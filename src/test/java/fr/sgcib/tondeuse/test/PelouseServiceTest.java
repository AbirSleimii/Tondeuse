package fr.sgcib.tondeuse.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import fr.sgcib.tondeuse.entities.Instruction;
import fr.sgcib.tondeuse.entities.Orientation;
import fr.sgcib.tondeuse.entities.Pelouse;
import fr.sgcib.tondeuse.entities.Position;
import fr.sgcib.tondeuse.entities.Tondeuse;
import fr.sgcib.tondeuse.exceptions.TondeuseException;
import fr.sgcib.tondeuse.services.PelouseService;

public class PelouseServiceTest {
	@Test(expected = IllegalArgumentException.class)
	public void tondeuseCannotBeAddedWithoutPelouse() throws TondeuseException  {
		new PelouseService().addTondeuse(new Tondeuse(new Position(0, 0, Orientation.EAST), null));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tondeuseCannotBeInOccupiedPosition() throws TondeuseException  {
		PelouseService pelouseService = new PelouseService();
		pelouseService.setPelouse(new Pelouse(5, 5));
		pelouseService.addTondeuse(new Tondeuse(new Position(0, 0, Orientation.EAST), null));
		pelouseService.addTondeuse(new Tondeuse(new Position(0, 0, Orientation.NORTH), null));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tondeuseCannotBeOutOfBounds() throws TondeuseException  {
		PelouseService pelouseService = new PelouseService();
		pelouseService.setPelouse(new Pelouse(5, 5));
		pelouseService.addTondeuse(new Tondeuse(new Position(50, 0, Orientation.EAST), null));
	}
	
	@Test
	public void tondeusesMustMoveCorrectly() throws TondeuseException {
		
		Pelouse pelouse = new Pelouse(5, 5);

		Queue<Instruction> instructions = new LinkedList<>();
		instructions.add(Instruction.GAUCHE);
		instructions.add(Instruction.AVANCER);
		instructions.add(Instruction.GAUCHE);
		instructions.add(Instruction.AVANCER);
		instructions.add(Instruction.GAUCHE);
		instructions.add(Instruction.AVANCER);
		instructions.add(Instruction.GAUCHE);
		instructions.add(Instruction.AVANCER);
		instructions.add(Instruction.AVANCER);
		Tondeuse tondeuse1 = new Tondeuse(new Position(1, 2, Orientation.NORTH), instructions);

		Queue<Instruction> instructions2 = new LinkedList<>();
		instructions2.add(Instruction.AVANCER);
		instructions2.add(Instruction.AVANCER);
		instructions2.add(Instruction.DROITE);
		instructions2.add(Instruction.AVANCER);
		instructions2.add(Instruction.AVANCER);
		instructions2.add(Instruction.DROITE);
		instructions2.add(Instruction.AVANCER);
		instructions2.add(Instruction.DROITE);
		instructions2.add(Instruction.DROITE);
		instructions2.add(Instruction.AVANCER);
		Tondeuse tondeuse2 = new Tondeuse(new Position(3, 3, Orientation.EAST), instructions2);

		PelouseService service = new PelouseService();

		service.setPelouse(pelouse);
		service.addTondeuse(tondeuse1);
		service.addTondeuse(tondeuse2);

		service.startTondeuses();
		
		String tondeusePositions = service.getTondeusesPositions();

		assertEquals(tondeusePositions, "1 3 N\n5 1 E");
	}
}
