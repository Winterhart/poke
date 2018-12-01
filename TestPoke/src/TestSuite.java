
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.soen387.ser.RenewDatabase;

import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class TestSuite {
	public static String URL_BASE = "http://localhost:8080/poke/";
	
	final static Logger testLogger = Logger.getLogger(TestSuite.class.getName());
	
	
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
	
	@BeforeClass
	public static void setup() {
		RenewDatabase.main(null);
	}
	
	@Test
	public void testRegisterNoInfo() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "", "");
			DocumentContext dc = JsonPath.parse(jsonText);
			
			Assert.assertEquals("fail", dc.read("$['status']"));

			jsonText = GameUtils.register(webClient, "", "fred2");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.register(webClient, "bob2", "");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRegisterSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob", "fred");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRegisterDuplicate() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob1", "fred1");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.register(webClient, "bob1", "fred1");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}


	
	
	@Test
	public void testLoginSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob3", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "bob3", "fred3");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testLoginFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.login(webClient, "bob5", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testLogoutFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.logout(webClient);
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testLogoutSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "bob6", "fred6");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.logout(webClient);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	
	@Test
	public void testUploadDeckSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testUploadDeckSuccess", "testUploadDeckSuccessPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckFailNoLogin() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.uploadDeck(webClient, TEST_DECK1);
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckFailSmallDeck() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testUploadDeckFailSmallDeck", "testUploadDeckFailSmallDeckPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.uploadDeck(webClient, TEST_DECK3_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}

	@Test
	public void testUploadDeckFailBigDeck() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testUploadDeckFailBigDeck", "testUploadDeckFailBigDeckPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.uploadDeck(webClient, TEST_DECK4_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckSuccessRecoverFromFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testUploadDeckSuccessRecoverFromFail", "testUploadDeckSuccessRecoverFromFailPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.uploadDeck(webClient, TEST_DECK4_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testViewDecksSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testViewDecksSuccess", "testViewDecksSuccessPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.viewDecks(webClient);
			dc = JsonPath.parse(jsonText);


			
			JSONArray jsonArray = dc.read("decks[*]");
		    int size = jsonArray.size();
			
			jsonText = GameUtils.uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.viewDecks(webClient);
			dc = JsonPath.parse(jsonText);
			jsonArray = dc.read("$.decks[*]");
			Assert.assertEquals(size+1, jsonArray.size());

		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testViewDecksFailureNotLoggedIn() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.viewDecks(webClient);
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}

	private static final String CARD_PATTERN = "(.) \"([^\"]*)\"(?: \"([^\"]*)\")?";
	
	@Test
	public void testViewDeckSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = "";
			DocumentContext dc;
			
			GameUtils.register(webClient, "testViewDeckSuccess", "testViewDeckSuccessPass");
			
			long deck = getNewDeckId(webClient, TEST_DECK1);

			jsonText = GameUtils.viewDeck(webClient, deck);
			dc = JsonPath.parse(jsonText);
			
			String[] cards = TEST_DECK1.split("\n");
			Assert.assertEquals(40, cards.length);
			Pattern pattern = Pattern.compile(CARD_PATTERN);
			for(int i = 0; i < cards.length; i++) {

				String line = cards[i];
				Matcher m = pattern.matcher(line);
				m.find();
				String type = m.group(1);
				String name = m.group(2);
				String basicName = m.group(3);
				Assert.assertEquals(type, dc.read("$['deck']['cards'][" + i + "]['t']"));
				Assert.assertEquals(name, dc.read("$['deck']['cards'][" + i + "]['n']"));
				if(basicName != null) {
					Assert.assertEquals(basicName, dc.read("$['deck']['cards'][" + i + "]['b']"));	
				}
			}
			
		} catch (IOException e) {
			
		}
	}
	
	public long getNewDeckId(WebClient webClient, String deck) throws MalformedURLException, IOException {
		String jsonText = GameUtils.viewDecks(webClient);
		DocumentContext dc = JsonPath.parse(jsonText);
		JSONArray decksBefore = dc.read("$.decks[*]");

		jsonText = GameUtils.uploadDeck(webClient, deck);
		dc = JsonPath.parse(jsonText);

		jsonText = GameUtils.viewDecks(webClient);
		dc = JsonPath.parse(jsonText);
		JSONArray decksAfter = dc.read("$.decks[*]");
		decksAfter.removeAll(decksBefore);
		return (int)decksAfter.get(0);
	}
	
	@Test
	public void testViewDeckFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = "";
			DocumentContext dc;
			
			GameUtils.register(webClient, "testViewDeckFail", "testViewDeckFailPass");
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.viewDeck(webClient, deck);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testListPlayersSuccess() {
		try {
			WebClient webClientA = new WebClient();
			WebClient webClientB = new WebClient();
			String jsonText = GameUtils.register(webClientA, "testListPlayersSuccessA", "testListPlayersSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			List<Map<String, Object>> jPathResult = null;
			
			jsonText = GameUtils.listPlayers(webClientA);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListPlayersSuccessA')].user");
			Assert.assertEquals("testListPlayersSuccessA", jPathResult.get(0));

			jsonText = GameUtils.register(webClientB, "testListPlayersSuccessB", "testListPlayersSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = GameUtils.listPlayers(webClientB);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListPlayersSuccessA')].user");
			Assert.assertEquals("testListPlayersSuccessA", jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListPlayersSuccessB')].user");
			Assert.assertEquals("testListPlayersSuccessB", jPathResult.get(0));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testListPlayersFailure() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.listPlayers(webClient);
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testChallengePlayerSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testChallengePlayerSuccessA", "testChallengePlayerSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
					
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testChallengePlayerSuccessB", "testChallengePlayerSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerSuccessA')].id");

			
			jsonText = GameUtils.challengePlayer(webClient, (Integer)((Object)(jPathResult.get(0))), deck);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testChallengePlayerFailureInvalidDeck() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testChallengePlayerFailureNoDeckA", "testChallengePlayerFailureNoDeckAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testChallengePlayerFailureNoDeckB", "testChallengePlayerFailureNoDeckBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerFailureNoDeckA')].id");
			
			jsonText = GameUtils.challengePlayer(webClient, (Integer)((Object)(jPathResult.get(0))), -deck);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testChallengePlayerFailureChallengeSelf() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testChallengePlayerFailureChallengeSelfA", "testChallengePlayerFailureChallengeSelfAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testChallengePlayerFailureChallengeSelfB", "testChallengePlayerFailureChallengeSelfBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerFailureChallengeSelfB')].id");
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.challengePlayer(webClient, (Integer)((Object)(jPathResult.get(0))), deck);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testChallengePlayerFailureChallengeInvalidId() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testChallengePlayerFailureChallengeInvalidA", "testChallengePlayerFailureChallengeInvalidAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testChallengePlayerFailureChallengeInvalidB", "testChallengePlayerFailureChallengeInvalidBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.challengePlayer(webClient, -12, deck);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testChallengePlayerFailureChallengeWithSomeoneElsesDeck() {
		try {
			//Accept and check that it is listed as accepted
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testChallengePlayerFailureChallengeWithSomeoneElsesDeckA", "testChallengePlayerFailureChallengeWithSomeoneElsesDeckAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testChallengePlayerFailureChallengeWithSomeoneElsesDeckB", "testChallengePlayerFailureChallengeWithSomeoneElsesDeckBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerFailureChallengeWithSomeoneElsesDeckA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));

			jsonText = GameUtils.challengePlayer(webClient, idA, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
	
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testListChallengesSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testListChallengesSuccessA", "testListChallengesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testListChallengesSuccessB", "testListChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListChallengesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListChallengesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + " && @['deck']==" + deck + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deck);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testListChallengesFailureNotLoggedIn() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testListChallengesFailureNotLoggedInA", "testListChallengesFailureNotLoggedInAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testListChallengesFailureNotLoggedInB", "testListChallengesFailureNotLoggedInBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListChallengesFailureNotLoggedInA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListChallengesFailureNotLoggedInB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deck = getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			GameUtils.challengePlayer(webClient, idA, deck);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	

	
	@Test
	public void testAcceptChallengesSuccess() {
		try {
			//Accept and check that it is listed as accepted
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testAcceptChallengesSuccessA", "testAcceptChallengesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testAcceptChallengesSuccessB", "testAcceptChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deckB = getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testAcceptChallengesSuccessA", "testAcceptChallengesSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
			
			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + " && @['status']==3)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testAcceptChallengesFailureAcceptSomeoneElsesChallenge() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeA", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeB", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckB = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeC", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckC = getNewDeckId(webClient, TEST_DECK1);
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptSomeoneElsesChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptSomeoneElsesChallengeB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptSomeoneElsesChallengeC')].id");
			int idC = (Integer)((Object)jPathResult.get(0));
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeB", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeC", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idC + "  && @['challengee']==" + idA + " && @['status']==0)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testAcceptChallengesFailureAcceptOwnChallenge() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptOwnChallengeA", "testAcceptChallengesFailureAcceptOwnChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptOwnChallengeB", "testAcceptChallengesFailureAcceptOwnChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckB = getNewDeckId(webClient, TEST_DECK1);

			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptOwnChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptOwnChallengeB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));

			jsonText = GameUtils.challengePlayer(webClient, idA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));

			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + " && @['status']==0)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testAcceptChallengesFailureAcceptAcceptedChallenge() {
		try {
			//Accept and check that it is listed as accepted
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptAcceptedChallengeA", "testAcceptChallengesFailureAcceptAcceptedChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testAcceptChallengesFailureAcceptAcceptedChallengeB", "testAcceptChallengesFailureAcceptAcceptedChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptAcceptedChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptAcceptedChallengeB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deckB = getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testAcceptChallengesFailureAcceptAcceptedChallengeA", "testAcceptChallengesFailureAcceptAcceptedChallengeAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
			
			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + " && @['status']==3)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRefuseChallengesSuccess() {
		try {

			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testRefuseChallengesSuccessA", "testRefuseChallengesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testRefuseChallengesSuccessB", "testRefuseChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckB = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testRefuseChallengesSuccessC", "testRefuseChallengesSuccessCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckC = getNewDeckId(webClient, TEST_DECK1);

			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesSuccessC')].id");
			int idC = (Integer)((Object)jPathResult.get(0));
			
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.challengePlayer(webClient, idB, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeIdCvA = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersionCvA =(Integer)((Object)jPathResult.get(0).get("version"));
			
			query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idB + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeIdCvB = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersionCvB =(Integer)((Object)jPathResult.get(0).get("version"));
			
			//Withdraw and check that it is listed as withdrawn
			jsonText = GameUtils.withdrawChallenge(webClient, challengeIdCvA, challengeVersionCvA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			jsonText = GameUtils.login(webClient, "testRefuseChallengesSuccessB", "testRefuseChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			//Refuse and check that it is listed as refused
			jsonText = GameUtils.refuseChallenge(webClient, challengeIdCvB, challengeVersionCvB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testRefuseChallengesSuccessC", "testRefuseChallengesSuccessCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idC + "  && @['challengee']==" + idA + " && @['status']==2)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
			query = "challenges[?(@['challenger']==" + idC + "  && @['challengee']==" + idB + " && @['status']==1)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRefuseChallengesFailureRefuseSomeoneElsesChallenge() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeA", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA = getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeB", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckB= getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeC", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckC= getNewDeckId(webClient, TEST_DECK1);

			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesFailureRefuseSomeoneElsesChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesFailureRefuseSomeoneElsesChallengeC')].id");
			int idC = (Integer)((Object)jPathResult.get(0));
			
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeIdCvA = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersionCvA =(Integer)((Object)jPathResult.get(0).get("version"));

			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeB", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			//Refuse and check that it is listed as refused
			jsonText = GameUtils.refuseChallenge(webClient, challengeIdCvA, challengeVersionCvA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));

			GameUtils.logout(webClient);
			jsonText = GameUtils.login(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeC", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idC + "  && @['challengee']==" + idA + " && @['status']==0)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	

	@Test
	public void testListGamesSuccess() {
		try {
			//Accept and check that it is listed as accepted
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.register(webClient, "testListGamesSuccessA", "testListGamesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA= getNewDeckId(webClient, TEST_DECK1);
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.register(webClient, "testListGamesSuccessB", "testListGamesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListGamesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListGamesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deckB= getNewDeckId(webClient, TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, idA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			jsonText = GameUtils.login(webClient, "testListGamesSuccessA", "testListGamesSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));


			//Show that accepting challenges causes games to be listed
			//confirm that the players are the challenger and challengee
			jsonText = GameUtils.listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?((@.players[0]==" + idB + " || @.players[1]==" + idB + ") && (@.players[0]==" + idA + " || @.players[1]==" + idA + ")) ].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listGames(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testViewBoardSuccess() {
		try {
			WebClient webClientA = new WebClient();
			String jsonText = GameUtils.register(webClientA, "testViewBoardSuccessA", "testViewBoardSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA= getNewDeckId(webClientA, TEST_DECK1);
			
			WebClient webClientB = new WebClient();
			jsonText = GameUtils.register(webClientB, "testViewBoardSuccessB", "testViewBoardSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClientB);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testViewBoardSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testViewBoardSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deckB= getNewDeckId(webClientB, TEST_DECK1);

			
			jsonText = GameUtils.challengePlayer(webClientB, idA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClientA);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));

			jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listGames(webClientA);
			dc = JsonPath.parse(jsonText);
			query = "games[?((@.players[0]==" + idB + " || @.players[1]==" + idB + ") && (@.players[0]==" + idA + " || @.players[1]==" + idA + ")) ].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = GameUtils.viewBoard(webClientA, gameId);
			dc = JsonPath.parse(jsonText);
			query = "game[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int foundGameId = (Integer)((Object)jPathResult.get(0));
			Assert.assertEquals(gameId, foundGameId);
			query = "game.play."+idB+".decksize";
			Assert.assertEquals(39, (int)dc.read(query));
			query = "game.play."+idA+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "game.play."+idB+".handsize";
			Assert.assertEquals(1, (int)dc.read(query));
			query = "game.play."+idA+".handsize";
			Assert.assertEquals(0, (int)dc.read(query));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewBoardFailureNotMyGame() {
		try {
			WebClient webClientA = new WebClient();
			String jsonText = GameUtils.register(webClientA, "testViewBoardFailureNotMyGameA", "testViewBoardFailureNotMyGameAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			long deckA= getNewDeckId(webClientA, TEST_DECK1);
			
			WebClient webClientB = new WebClient();
			jsonText = GameUtils.register(webClientB, "testViewBoardFailureNotMyGameB", "testViewBoardFailureNotMyGameBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = GameUtils.listPlayers(webClientB);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testViewBoardFailureNotMyGameA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testViewBoardFailureNotMyGameB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			long deckB= getNewDeckId(webClientB, TEST_DECK1);
					
			jsonText = GameUtils.challengePlayer(webClientB, idA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClientA);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));

			jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listGames(webClientA);
			dc = JsonPath.parse(jsonText);
			query = "games[?((@.players[0]==" + idB + " || @.players[1]==" + idB + ") && (@.players[0]==" + idA + " || @.players[1]==" + idA + ")) ].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			WebClient webClientC = new WebClient();
			jsonText = GameUtils.register(webClientC, "testViewBoardFailureNotMyGameC", "testViewBoardFailureNotMyGameCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.viewBoard(webClientC, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	


	
}
