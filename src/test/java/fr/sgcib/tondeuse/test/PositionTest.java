package fr.sgcib.tondeuse.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import fr.sgcib.tondeuse.entities.Orientation;
import fr.sgcib.tondeuse.entities.Position;

public class PositionTest {

	@Test
	public void testDeplacerDroite() {
		Position position = new Position(0, 0, Orientation.NORTH);
		Position newPosition = position.tournerDroite();
		assertEquals(new Position(0, 0, Orientation.EAST), newPosition);
	}

	@Test
	public void testMoveLeft() {
		Position position = new Position(0, 0, Orientation.NORTH);
		Position newPosition = position.tournerGauche();
		assertEquals(new Position(0, 0, Orientation.WEST), newPosition);
	}

	@Test
	public void testMoveForward() {
		Position position = new Position(0, 0, Orientation.NORTH);
		Position newPosition = position.avancer();
		assertEquals(new Position(0, 1, Orientation.NORTH), newPosition);
	}

	@Test(expected = IllegalArgumentException.class)
	public void orientationMustNotBeNull() {
		new Position(0, 0, null);
	}
}
