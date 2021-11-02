package fr.sgcib.tondeuse.services;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sgcib.tondeuse.entities.Pelouse;
import fr.sgcib.tondeuse.entities.Position;
import fr.sgcib.tondeuse.entities.Instruction;
import fr.sgcib.tondeuse.entities.Orientation;
import fr.sgcib.tondeuse.entities.Tondeuse;
import fr.sgcib.tondeuse.exceptions.TondeuseException;

public class InstructionService {
	private static final String PELOUSE_DEFINITION_PATTERN = "^(\\d+) (\\d+)$";
	private static final String TONDEUSE_DEFINITON_PATTERN = "^(\\d+) (\\d+) ([NESW])$";
	private static final String TONDEUSE_INSTRUCTION_PATTERN = "^[AGD]+$";
	private static Logger logger = LoggerFactory.getLogger(InstructionService.class);
	
	//Parser les instructions depuis le fichier en entrees
	public void parseInstructionFichier(PelouseService pelouseService, String filePath) throws TondeuseException {

		InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		if (fileInputStream == null) {
			throw new TondeuseException("le fichier d'instructions est introuvable");
		}

		Scanner scanner = new Scanner(fileInputStream);

		// 1ere ligne : pelouse dimensions
		pelouseService.setPelouse(parsePelouse(scanner.nextLine()));

		// reste des lignes : tondeuses and leurs instructions
		while (scanner.hasNextLine()) {

			Tondeuse tondeuse = parseTondeuse(scanner.nextLine());
			Queue<Instruction> instructions = parseInstructions(scanner.nextLine());
			tondeuse.setInstructionsTondeuse(instructions);

			pelouseService.addTondeuse(tondeuse);
			logger.info("nouvelle tondeuse ajoutée à la pelouse : " + tondeuse);
		}

		scanner.close();
	}
	

	private Pelouse parsePelouse(String pelouseLine) throws TondeuseException {

		// vérifier le pattern de la ligne
		Pattern pelousePattern = Pattern.compile(PELOUSE_DEFINITION_PATTERN);
		Matcher pelouseMatcher = pelousePattern.matcher(pelouseLine);
		if (!pelouseMatcher.matches()) {
			throw new TondeuseException("la première ligne (dimensions pelouse) est mal formatée.");
		}

		// creer pelouse
		int width = Integer.parseInt(pelouseMatcher.group(1));
		int height = Integer.parseInt(pelouseMatcher.group(2));
		return new Pelouse(width, height);
	}

	private Tondeuse parseTondeuse(String mowerLine) throws TondeuseException {

		// vérifier le pattern de la ligne
		Pattern tondeusePattern = Pattern.compile(TONDEUSE_DEFINITON_PATTERN);
		Matcher tondeuseMatcher = tondeusePattern.matcher(mowerLine);
		if (!tondeuseMatcher.matches()) {
			throw new TondeuseException("mower definition is incorrect");
		}

		// creer Tondeuse
		int x = Integer.parseInt(tondeuseMatcher.group(1));
		int y = Integer.parseInt(tondeuseMatcher.group(2));
		Orientation direction = parseOrientation(tondeuseMatcher.group(3));
		return new Tondeuse(new Position(x, y, direction), new LinkedList<Instruction>());

	}

	private Queue<Instruction> parseInstructions(String instructionsLine) throws TondeuseException {

		// tester si la ligne d'instruction est correcte
		Pattern instructionPattern = Pattern.compile(TONDEUSE_INSTRUCTION_PATTERN);
		Matcher instructionMatcher = instructionPattern.matcher(instructionsLine);
		if (!instructionMatcher.matches()) {
			throw new TondeuseException("instruction line is incorrect");
		}

		// créer des instructions
		Queue<Instruction> instructions = new LinkedList<Instruction>();
		for (char c : instructionsLine.toCharArray()) {
			instructions.add(parseInstruction(c));
		}
		return instructions;
	}

	private Orientation parseOrientation(String orientation) {
		switch (orientation) {
		case "N":
			return Orientation.NORTH;
		case "E":
			return Orientation.EAST;
		case "S":
			return Orientation.SOUTH;
		case "W":
			return Orientation.WEST;
		default:
			throw new IllegalArgumentException("Orientation [" + orientation + "] incorrect.");
		}
	}

	private Instruction parseInstruction(char instruction) {
		switch (instruction) {
		case 'D':
			return Instruction.DROITE;
		case 'G':
			return Instruction.GAUCHE;
		case 'A':
			return Instruction.AVANCER;
		default:
			throw new IllegalArgumentException("Instruction [" + instruction + "] incorrect.");
		}
	}
}
