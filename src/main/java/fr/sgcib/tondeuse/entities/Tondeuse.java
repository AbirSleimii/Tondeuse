package fr.sgcib.tondeuse.entities;

import java.util.Queue;

import org.apache.commons.lang.Validate;

public class Tondeuse {
	
	private Position coordonneesTondeuse;
	private Orientation orientationTondeuse;
	private Queue<Instruction> instructionsTondeuse;

	public Tondeuse(Position position,
			Queue<Instruction> instructions) {
		Validate.notNull(position, "La position ne peut pas etre nulle.");
		Validate.notNull(instructions, "les instructions ne peuvent pas etre nulles.");
		
		this.coordonneesTondeuse = position;
		this.instructionsTondeuse = instructions;
	}

	public Orientation getOrientationTondeuse() {
		return orientationTondeuse;
	}

	public void setOrientationTondeuse(Orientation pOrientationTondeuse) {
		this.orientationTondeuse = pOrientationTondeuse;
	}

	public Position getCoordonneesTondeuse() {
		return coordonneesTondeuse;
	}

	public void setCoordonneesTondeuse(Position pCoordonneesTondeuse) {
		this.coordonneesTondeuse = pCoordonneesTondeuse;
	}
	


	public Queue<Instruction> getInstructionsTondeuse() {
		return instructionsTondeuse;
	}

	public void setInstructionsTondeuse(Queue<Instruction> instructions) {
		this.instructionsTondeuse = instructions;
	}
	
	public String toString() {
		return "mower : " + coordonneesTondeuse + ", [" + instructionsTondeuse.size() + "] instructions remaining.";
	}
}
