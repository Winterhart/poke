package DevTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.soen387.app.Domain.Helper.CardHelper;
import org.soen387.app.Util.DeckParser;

class TestDeckParser {
	
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
	void testDeckPar() {
		
		List<CardHelper> correctDeck = DeckParser.parseDeck(TEST_DECK1);
		List<CardHelper> correctDeck2 = DeckParser.parseDeck(TEST_DECK2);
		List<CardHelper> NotcorrectDeck = DeckParser.parseDeck(TEST_DECK3_BAD);
		List<CardHelper> NotcorrectDeck2 = DeckParser.parseDeck(TEST_DECK4_BAD);
		
		if(correctDeck.size() != 40) {
			fail("Fail to partse");
		}
		
		if(correctDeck2.size() != 40) {
			fail("Fail to partse");
		}
		
		if(NotcorrectDeck2 != null) {
			fail("Fail to partse");
		}
		
		if(NotcorrectDeck != null) {
			fail("Fail to partse");
		}
		

	}

}
