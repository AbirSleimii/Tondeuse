package fr.sgcib.tondeuse.test;

import org.junit.Test;

import fr.sgcib.tondeuse.entities.Pelouse;

public class PelouseTest {
	@Test(expected = IllegalArgumentException.class)
	public void widthMustBePositive() {
		new Pelouse(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void heightMustBePositive() {
		new Pelouse(0, -1);
	}
}
