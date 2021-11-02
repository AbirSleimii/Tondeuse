package fr.sgcib.tondeuse.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sgcib.tondeuse.exceptions.TondeuseException;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(PelouseService.class);

	public static void main(String... args) throws TondeuseException, IOException {
		//le nom du fichier est renseigné dans args en entrée
		if (args.length == 1) {
			String file = args[0];
			InstructionService instance = new InstructionService();

			PelouseService pelouseService= new PelouseService();
			instance.parseInstructionFichier(pelouseService, file);
			
			pelouseService.startTondeuses();
			
			logger.info("\n" + pelouseService.getTondeusesPositions());
			System.out.println(pelouseService.getTondeusesPositions());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
