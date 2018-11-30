package testsDev;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.soen387.dom.Helper.CardHelper;
import org.soen387.util.DeckParser;

public class TestDeckParser {
	
	public static String TEST_DECK1 =
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"p \"Meowth\"\n" +
			"e \"Fire\"\n" +
			"t \"Misty\"\n" +
			"t \"Misty\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"p \"Meowth\"\n" +
			"e \"Fire\"\n" +
			"t \"Misty\"\n" +
			"t \"Misty\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"p \"Meowth\"\n" +
			"e \"Fire\"\n" +
			"t \"Misty\"\n" +
			"t \"Misty\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n";

	public static String TEST_DECK2 =
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n";
	
	//Too Few
	public static String TEST_DECK3_BAD =
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n";
	
	//Too Many
	public static String TEST_DECK4_BAD =
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n";
	@Test
	public void testDeckPar() {
		
		List<CardHelper> correctDeck = DeckParser.parseDeck(TEST_DECK1);
		List<CardHelper> correctDeck2 = DeckParser.parseDeck(TEST_DECK2);
		List<CardHelper> notCorrectDeck = DeckParser.parseDeck(TEST_DECK3_BAD);
		List<CardHelper> notCorrectDeck2 = DeckParser.parseDeck(TEST_DECK4_BAD);

		assertEquals(correctDeck.size(), 40);
		assertEquals(correctDeck2.size(), 40);
		assertEquals(notCorrectDeck, null);
		assertEquals(notCorrectDeck2, null);
		
		

	}

}