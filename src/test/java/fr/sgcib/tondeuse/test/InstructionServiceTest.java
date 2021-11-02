package fr.sgcib.tondeuse.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.sgcib.tondeuse.entities.Orientation;
import fr.sgcib.tondeuse.entities.Position;
import fr.sgcib.tondeuse.exceptions.TondeuseException;
import fr.sgcib.tondeuse.services.InstructionService;
import fr.sgcib.tondeuse.services.PelouseService;

public class InstructionServiceTest {
	@Test(expected = TondeuseException.class)
	public void fichierDoitExister() throws TondeuseException {
		new InstructionService().parseInstructionFichier(new PelouseService(),"c:/wrongpath.txt");
	}

	@Test(expected = TondeuseException.class)
	public void fichierDoitAvoirFormatCorrect() throws TondeuseException {
		new InstructionService().parseInstructionFichier(new PelouseService(),"wrongFormat.txt");
	}

	@Test
	public void parsingDoitEtreCorrect() throws TondeuseException {
		PelouseService pelouseService = new PelouseService();
		new InstructionService().parseInstructionFichier(pelouseService,"instructions.txt");

		// Pelouse
		assertEquals(pelouseService.getPelouse().getHeight(), 5);
		assertEquals(pelouseService.getPelouse().getWidth(), 5);

		// mowers
		assertEquals(pelouseService.getTondeuses().size(), 2);
		assertEquals(pelouseService.getTondeuses().get(0).getCoordonneesTondeuse(), new Position(1, 2, Orientation.NORTH));
		assertEquals(pelouseService.getTondeuses().get(1).getCoordonneesTondeuse(), new Position(3, 3, Orientation.EAST));
	}
}
