package fr.sgcib.tondeuse.test;

import org.junit.Test;

import fr.sgcib.tondeuse.entities.Orientation;
import fr.sgcib.tondeuse.entities.Position;
import fr.sgcib.tondeuse.entities.Tondeuse;

public class TondeuseTest {
	@Test(expected = IllegalArgumentException.class)
	public void locationMustNotBeNull() {
		new Tondeuse(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void instructionsMustNotBeNull() {
		new Tondeuse(new Position(0, 0, Orientation.NORTH), null);
	}
}
