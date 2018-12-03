import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class Deck {
	DocumentContext dc = null;
	long id;
	public static String TEST_DECK7 =
			"p \"Raichu\" \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"p \"Meowth\"\n" +
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
	public static String TEST_DECK6 =
	"t \"Tierno\"\n" +
	"p \"Pikachu\"\n" +
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
	
	public static String TEST_DECK5 =
	"p \"Pikachu\"\n" +
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
	public static String TEST_DECK2 =
	"p \"Raichu\" \"Pikachu\"\n" +
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
	"p \"Raichu\" \"Pikachu\"\n" +
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
	
	public Deck(DocumentContext dc, long id) {
		super();
		this.dc = dc;
		this.id = id;
	}
	
	
	
	public static Deck createDeck(WebClient webClient, String deck) throws MalformedURLException, IOException {
		String jsonText = GameUtils.viewDecks(webClient);
		DocumentContext dc = JsonPath.parse(jsonText);
		JSONArray decksBefore = dc.read("$.decks[*]");

		jsonText = GameUtils.uploadDeck(webClient, deck);
		dc = JsonPath.parse(jsonText);

		jsonText = GameUtils.viewDecks(webClient);
		dc = JsonPath.parse(jsonText);
		JSONArray decksAfter = dc.read("$.decks[*]");
		decksAfter.removeAll(decksBefore);
		long id = (int)decksAfter.get(0);
		
		jsonText = GameUtils.viewDeck(webClient,id);
		dc = JsonPath.parse(jsonText);
		return new Deck(dc, id);
	}

	public Card findCard(int i) {
		return new Card(dc.read("$.cards[" + i + "]"));
	}


	public long getId() {
		return id;
	}



	public DocumentContext getDc() {
		return dc;
	}
}
