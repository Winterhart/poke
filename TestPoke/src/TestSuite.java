
import java.io.IOException;
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
			
			jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckFailNoLogin() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK1);
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
			
			jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK3_BAD);
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
			
			jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK4_BAD);
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
			
			jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK4_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK1);
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
			
			jsonText = GameUtils.uploadDeck(webClient, Deck.TEST_DECK1);
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
	
	@Test
	public void testViewDeckSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = "";
			DocumentContext dc;
			
			GameUtils.register(webClient, "testViewDeckSuccess", "testViewDeckSuccessPass");
			
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.viewDeck(webClient, deck);
			dc = JsonPath.parse(jsonText);
			
			String[] cards = Deck.TEST_DECK1.split("\n");
			Assert.assertEquals(40, cards.length);
			Pattern pattern = Pattern.compile("(.) \"([^\"]*)\"(?: \"([^\"]*)\")?");
			for(int i = 0; i < cards.length; i++) {

				String line = cards[i];
				Matcher m = pattern.matcher(line);
				m.find();
				String type = m.group(1);
				String name = m.group(2);
				String basicName = m.group(3);
				Assert.assertEquals(type, dc.read("$['cards'][" + i + "]['t']"));
				Assert.assertEquals(name, dc.read("$['cards'][" + i + "]['n']"));
				if(basicName != null) {
					Assert.assertEquals(basicName, dc.read("$['cards'][" + i + "]['b']"));	
				}
			}
			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testViewDeckFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = "";
			DocumentContext dc;
			
			GameUtils.register(webClient, "testViewDeckFail", "testViewDeckFailPass");
			
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
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
	
	/*
	 * Starting here we can use Player because if the tests before this are passing Player should work
	 * 
	 */
	@Test
	public void testChallengePlayerSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText;
			DocumentContext dc;
			Player playerA = Player.createPlayer(webClient, "testChallengePlayerSuccessA", "testChallengePlayerSuccessAPass");
			GameUtils.logout(webClient);
			
			Player.createPlayer(webClient, "testChallengePlayerSuccessB", "testChallengePlayerSuccessBPass");			
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			jsonText = GameUtils.challengePlayer(webClient, playerA, deck);
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
			String jsonText;
			DocumentContext dc;
			Player playerA = Player.createPlayer(webClient, "testChallengePlayerFailureInvalidDeckA", "testChallengePlayerFailureInvalidDeckAPass");
			GameUtils.logout(webClient);
			
			Player.createPlayer(webClient, "testChallengePlayerFailureInvalidDeckB", "testChallengePlayerFailureInvalidDeckBPass");
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			jsonText = GameUtils.challengePlayer(webClient, playerA.getId(), -deck.getId());
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
			String jsonText;
			DocumentContext dc;
			Player.createPlayer(webClient, "testChallengePlayerFailureChallengeSelfA", "testChallengePlayerFailureChallengeSelfAPass");
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testChallengePlayerFailureChallengeSelfB", "testChallengePlayerFailureChallengeSelfBass");			
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.challengePlayer(webClient, playerB, deck);
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
			String jsonText;
			DocumentContext dc;
			Player.createPlayer(webClient, "testChallengePlayerFailureChallengeInvalidIdA", "testChallengePlayerFailureChallengeInvalidIdAPass");
			GameUtils.logout(webClient);
			
			Player.createPlayer(webClient, "testChallengePlayerFailureChallengeInvalidIdB", "testChallengePlayerFailureChallengeInvalidIdBSPass");			
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.challengePlayer(webClient, -12, deck.getId());
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
			String jsonText;
			DocumentContext dc;
			Player playerA = Player.createPlayer(webClient, "testChallengePlayerFailureChallengeWithSomeoneElsesDeckA", "testChallengePlayerFailureChallengeWithSomeoneElsesDeckAPass");			
			Deck deckA = Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player.createPlayer(webClient, "testChallengePlayerFailureChallengeWithSomeoneElsesDeckB", "testChallengePlayerFailureChallengeWithSomeoneElsesDeckBPass");

			jsonText = GameUtils.challengePlayer(webClient, playerA, deckA);
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testListChallengesSuccessA", "testListChallengesSuccessAPass");
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testListChallengesSuccessB", "testListChallengesSuccessBPass");
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + " && @['deck']==" + deck.getId() + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, playerA, deck);
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testListChallengesFailureNotLoggedInA", "testListChallengesFailureNotLoggedInAPass");
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testListChallengesFailureNotLoggedInB", "testListChallengesFailureNotLoggedInBPass");
			Deck deck = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			GameUtils.challengePlayer(webClient, playerA, deck);
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
			WebClient webClient = new WebClient();
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testAcceptChallengesSuccessA", "testAcceptChallengesSuccessAPass");
			Deck deckA = Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testAcceptChallengesSuccessB", "testAcceptChallengesSuccessBPass");
			Deck deckB = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, playerA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			playerA = Player.loginPlayer(webClient, "testAcceptChallengesSuccessA", "testAcceptChallengesSuccessAPass");
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
			
			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + " && @['status']==3)].length()";
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeA", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeAPass");
			Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeB", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeBPass");
			Deck deckB = Deck.createDeck(webClient, Deck.TEST_DECK1);			
			GameUtils.logout(webClient);
			
			Player playerC = Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeC", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeCPass");
			Deck deckC = Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.challengePlayer(webClient, playerA, deckC);
						
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerC.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
			
			GameUtils.logout(webClient);
			
			Player.loginPlayer(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeB", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeBPass");

			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			GameUtils.logout(webClient);
			
			playerC = Player.loginPlayer(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeC", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeCPass");
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerC.getId() + "  && @['challengee']==" + playerA.getId() + " && @['status']==0)].length()";
			jPathResult = dc.read(query);
			System.out.println(query);
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptOwnChallengeA", "testAcceptChallengesFailureAcceptOwnChallengeAPass");
			Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptOwnChallengeB", "testAcceptChallengesFailureAcceptOwnChallengeBPass");
			Deck deckB = Deck.createDeck(webClient, Deck.TEST_DECK1);


			jsonText = GameUtils.challengePlayer(webClient, playerA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));

			jsonText = GameUtils.acceptChallenge(webClient, challengeId, challengeVersion, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + " && @['status']==0)].length()";
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptAcceptedChallengeA", "testAcceptChallengesFailureAcceptAcceptedChallengeAPass");
			Deck deckA = Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testAcceptChallengesFailureAcceptAcceptedChallengeB", "testAcceptChallengesFailureAcceptAcceptedChallengeBPass");			
			Deck deckB = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, playerA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			playerA = Player.loginPlayer(webClient, "testAcceptChallengesFailureAcceptAcceptedChallengeA", "testAcceptChallengesFailureAcceptAcceptedChallengeAPass");

			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
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
			query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + " && @['status']==3)].length()";
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testRefuseChallengesSuccessA", "testRefuseChallengesSuccessAPass");
			Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testRefuseChallengesSuccessB", "testRefuseChallengesSuccessBPass");
			Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerC = Player.createPlayer(webClient, "testRefuseChallengesSuccessC", "testRefuseChallengesSuccessCPass");
			Deck deckC = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.challengePlayer(webClient, playerA, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = GameUtils.challengePlayer(webClient, playerB, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerC.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeIdCvA = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersionCvA =(Integer)((Object)jPathResult.get(0).get("version"));
			
			query = "challenges[?(@['challenger']==" + playerC.getId() + " && @['challengee']==" + playerB.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeIdCvB = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersionCvB =(Integer)((Object)jPathResult.get(0).get("version"));
			
			//Withdraw and check that it is listed as withdrawn
			jsonText = GameUtils.withdrawChallenge(webClient, challengeIdCvA, challengeVersionCvA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			playerB = Player.loginPlayer(webClient, "testRefuseChallengesSuccessB", "testRefuseChallengesSuccessBPass");
			
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
			query = "challenges[?(@['challenger']==" + playerC.getId() + "  && @['challengee']==" + playerA.getId() + " && @['status']==2)].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(!jPathResult.isEmpty());
			query = "challenges[?(@['challenger']==" + playerC.getId() + "  && @['challengee']==" + playerB.getId() + " && @['status']==1)].length()";
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeA", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeAPass");
			Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player.createPlayer(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeB", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeBPass");
			Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerC = Player.createPlayer(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeC", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeCPass");
			Deck deckC = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.challengePlayer(webClient, playerA, deckC);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerC.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeIdCvA = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersionCvA =(Integer)((Object)jPathResult.get(0).get("version"));
			GameUtils.logout(webClient);
			
			Player.loginPlayer(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeB", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeBPass");
			
			//Refuse and check that it is listed as refused
			jsonText = GameUtils.refuseChallenge(webClient, challengeIdCvA, challengeVersionCvA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));

			GameUtils.logout(webClient);
			playerC = Player.loginPlayer(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeC", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeCPass");

			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerC.getId() + "  && @['challengee']==" + playerA.getId() + " && @['status']==0)].length()";
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
			WebClient webClient = new WebClient();
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClient, "testListGamesSuccessA", "testListGamesSuccessAPass");
			Deck deckA = Deck.createDeck(webClient, Deck.TEST_DECK1);
			GameUtils.logout(webClient);
			
			Player playerB = Player.createPlayer(webClient, "testListGamesSuccessB", "testListGamesSuccessBPass");
			Deck deckB = Deck.createDeck(webClient, Deck.TEST_DECK1);
			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + "  && @['challengee']==" + playerA.getId() + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = GameUtils.challengePlayer(webClient, playerA, deckB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			GameUtils.logout(webClient);
			
			playerA = Player.loginPlayer(webClient, "testListGamesSuccessA", "testListGamesSuccessAPass");			
			jsonText = GameUtils.listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));


			//Show that accepting challenges causes games to be listed
			//confirm that the players are the challenger and challengee
			jsonText = GameUtils.listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?((@.players[0]==" + playerB.getId() + " || @.players[1]==" + playerB.getId() + ") && (@.players[0]==" + playerA.getId() + " || @.players[1]==" + playerA.getId() + ")) ].length()";
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClientA, "testViewBoardSuccessA", "testViewBoardSuccessAPass");
			Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);

			WebClient webClientB = new WebClient();
			Player playerB = Player.createPlayer(webClientB, "testViewBoardSuccessB", "testViewBoardSuccessBPass");
			Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
			GameUtils.challengePlayer(webClientB, playerA, deckB);

			jsonText = GameUtils.listChallenges(webClientA);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));

			jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			
			jsonText = GameUtils.listGames(webClientA);
			dc = JsonPath.parse(jsonText);
			query = "games[?((@.players[0]==" + playerB.getId() + " || @.players[1]==" + playerB.getId() + ") && (@.players[0]==" + playerA.getId() + " || @.players[1]==" + playerA.getId() + ")) ].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = GameUtils.viewBoard(webClientA, gameId);
			dc = JsonPath.parse(jsonText);
			query = "game[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
			jPathResult = dc.read(query);
			int foundGameId = (Integer)((Object)jPathResult.get(0));
			Assert.assertEquals(gameId, foundGameId);
			query = "game.play."+playerB.getId()+".decksize";
			Assert.assertEquals(39, (int)dc.read(query));
			query = "game.play."+playerA.getId()+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "game.play."+playerB.getId()+".handsize";
			Assert.assertEquals(1, (int)dc.read(query));
			query = "game.play."+playerA.getId()+".handsize";
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
			String jsonText;
			DocumentContext dc;
			List<Map<String, Object>> jPathResult;
			Player playerA = Player.createPlayer(webClientA, "testViewBoardFailureNotMyGameA", "testViewBoardFailureNotMyGameAPass");
			Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);

			WebClient webClientB = new WebClient();
			Player playerB = Player.createPlayer(webClientB, "testViewBoardFailureNotMyGameB", "testViewBoardFailureNotMyGameBPass");
			Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
			GameUtils.challengePlayer(webClientB, playerA, deckB);

			jsonText = GameUtils.listChallenges(webClientA);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));

			jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			
			jsonText = GameUtils.listGames(webClientA);
			dc = JsonPath.parse(jsonText);
			query = "games[?((@.players[0]==" + playerB.getId() + " || @.players[1]==" + playerB.getId() + ") && (@.players[0]==" + playerA.getId() + " || @.players[1]==" + playerA.getId() + ")) ].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			WebClient webClientC = new WebClient();
			Player.createPlayer(webClientC, "testViewBoardFailureNotMyGameC", "testViewBoardFailureNotMyGameCPass");
			
			jsonText = GameUtils.viewBoard(webClientC, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testEndTurnSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testEndTurnSuccessA", "testEndTurnSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);

			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testEndTurnSuccessB", "testEndTurnSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);

				
			//Player B
				Board boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.endTurn(webClientB, boardB);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));

				
			//Player A
				Board boardA = Board.createBoard(webClientA, gameId);
				Assert.assertEquals(39, boardA.getDeckSize(playerB));
				Assert.assertEquals(39, boardA.getDeckSize(playerA));
				Assert.assertEquals(1, boardA.getHandSize(playerB));
				Assert.assertEquals(1, boardA.getHandSize(playerA));
				jsonText = GameUtils.endTurn(webClientA, boardA);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testEndTurnCausesDiscardSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testEndTurnCausesDiscardSuccessA", "testEndTurnCausesDiscardSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testEndTurnCausesDiscardSuccessB", "testEndTurnCausesDiscardSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);

			//Player B
				Board boardB = Board.createBoard(webClientB, gameId);
				GameUtils.endTurn(webClientB, boardB);
				
			//Player A
				Board boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, gameId, boardA.getVersion());

				boardB = Board.createBoard(webClientB, gameId);
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				Assert.assertEquals(7, boardB.getHandSize(playerB));
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				Assert.assertEquals(8, boardB.getHandSize(playerB));
				GameUtils.endTurn(webClientB, boardB);
				boardA = Board.createBoard(webClientA, gameId);
				GameUtils.endTurn(webClientA, boardA);
				boardB = Board.createBoard(webClientB, gameId);
				Assert.assertEquals(8, boardB.getHandSize(playerB));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testEndTurnFailNotMyTurn() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testEndTurnFailNotMyTurnA", "testEndTurnFailNotMyTurnAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testEndTurnFailNotMyTurnB", "testEndTurnFailNotMyTurnBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);

				
			//Player B
				Board boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.endTurn(webClientB, boardB);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));
				
			//Player B again
				boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.endTurn(webClientB, boardB);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testEndTurnFailWrongBoardVersion() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testEndTurnFailWrongBoardVersionA", "testEndTurnFailWrongBoardVersionAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testEndTurnFailWrongBoardVersionB", "testEndTurnFailWrongBoardVersionBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
			
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
	
				
			//Player B
				Board boardB = Board.createBoard(webClientB, gameId);
				int initialVersion = boardB.getVersion();
				jsonText = GameUtils.endTurn(webClientB, boardB);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));
				
			//Player A
				Board boardA = Board.createBoard(webClientA, gameId);
				jsonText = GameUtils.endTurn(webClientB, boardA.getId(), initialVersion);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewHandSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testViewHandSuccessA", "testViewHandSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testViewHandSuccessB", "testViewHandSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				

				// Test that drawing cards puts them properly in your hand
				jsonText = GameUtils.viewHand(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				query = "hand.length()";
				Assert.assertEquals(0, (int)dc.read(query));

				jsonText = GameUtils.viewHand(webClientB, gameId);
				dc = JsonPath.parse(jsonText);
				query = "hand.length()";
				Assert.assertEquals(1, (int)dc.read(query));
				
				int card = (int)dc.read("hand[0]");
				
				Assert.assertEquals(deckB.findCard(0).getId(), card);
				

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewHandFailureNotMyGame() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testViewHandFailureNotMyGameA", "testViewHandFailureNotMyGameAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testViewHandFailureNotMyGameB", "testViewHandFailureNotMyGameBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);	
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
			//Player C
				WebClient webClientC = new WebClient();
				Player.createPlayer(webClientC, "testViewHandFailureNotMyGameC", "testViewHandFailureNotMyGameCPass");
				
				jsonText = GameUtils.viewHand(webClientC, gameId);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPlayPokemonToBenchSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayPokemonToBenchSuccessA", "testPlayPokemonToBenchSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayPokemonToBenchSuccessB", "testPlayPokemonToBenchSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK5);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				Board boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.playPokemonToBench(webClientB, boardB, deckB.findCard(0));
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));
				
				boardB = Board.createBoard(webClientB, gameId);
				Assert.assertEquals(0, boardB.getDiscardSize(playerB));
				Assert.assertEquals(0, boardB.getHandSize(playerB));
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayPokemonToBenchFailureNotMyGame() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayPokemonToBenchFailureNotMyGameA", "testPlayPokemonToBenchFailureNotMyGameAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayPokemonToBenchFailureNotMyGameB", "testPlayPokemonToBenchFailureNotMyGameBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK5);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
				//Player C
				WebClient webClientC = new WebClient();
				Player.createPlayer(webClientC, "testPlayPokemonToBenchFailureNotMyGameC", "testPlayPokemonToBenchFailureNotMyGameCPass");
				Deck.createDeck(webClientC, Deck.TEST_DECK5);
				
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				Board boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.playPokemonToBench(webClientC, boardB, deckB.findCard(0));
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				boardB = Board.createBoard(webClientB, gameId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayFailureNotInHand() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayFailureNotInHandA", "testPlayFailureNotInHandAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayFailureNotInHandB", "testPlayFailureNotInHandBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK5);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
				
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				Board boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.playPokemonToBench(webClientB, boardB.getId(), 1000000+deckB.findCard(0).getId(), boardB.getVersion());
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				boardB = Board.createBoard(webClientB, gameId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayFailureEnergyWithNoTarget() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayFailureEnergyWithNoTargetA", "testPlayFailureEnergyWithNoTargetAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayFailureEnergyWithNoTargetB", "testPlayFailureEnergyWithNoTargetBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				Board boardB = Board.createBoard(webClientB, gameId);
				//This is not a basic pokemon, it's an energy and so should explode!
				jsonText = GameUtils.playPokemonToBench(webClientB, boardB, deckB.findCard(0));
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				boardB = Board.createBoard(webClientB, gameId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayFailureStageOneWithNoTarget() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayPokemonToBenchFailurePlayingACardThatIsNotAPokemonA", "testPlayPokemonToBenchFailurePlayingACardThatIsNotAPokemonAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayPokemonToBenchFailurePlayingACardThatIsNotAPokemonB", "testPlayPokemonToBenchFailurePlayingACardThatIsNotAPokemonBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK2);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				Board boardB = Board.createBoard(webClientB, gameId);
				//This is not a bsaic pokemon, it's stage one pokemon and so should explode!
				jsonText = GameUtils.playPokemonToBench(webClientB, boardB, deckB.findCard(0));
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				boardB = Board.createBoard(webClientB, gameId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayTrainerSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayTrainerSuccessA", "testPlayTrainerSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
		
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayTrainerSuccessB", "testPlayTrainerSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
		
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
		
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				Board boardB = Board.createBoard(webClientB, gameId);
				jsonText = GameUtils.playTrainer(webClientB, boardB, deckB.findCard(0));
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));
				
				boardB = Board.createBoard(webClientB, gameId);
				Assert.assertEquals(1, boardB.getDiscardSize(playerB));
				Assert.assertEquals(0, boardB.getHandSize(playerB));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testViewDiscardSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testViewDiscardSuccessA", "testViewDiscardSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK6);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testViewDiscardSuccessB", "testViewDiscardSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				Board boardB = Board.createBoard(webClientB, gameId);
				
				jsonText = GameUtils.viewDiscard(webClientB, boardB, playerA);
				dc = JsonPath.parse(jsonText);
				query = "discard.length()";
				Assert.assertEquals(0, (int)dc.read(query));				
				
				jsonText = GameUtils.viewDiscard(webClientB, boardB, playerB);
				dc = JsonPath.parse(jsonText);
				query = "discard.length()";
				Assert.assertEquals(0, (int)dc.read(query));
				
				GameUtils.playTrainer(webClientB, boardB, deckB.findCard(0));
				
				boardB = Board.createBoard(webClientB, gameId);
				
				//Note that this time we're having webClientA look at the discards
				jsonText = GameUtils.viewDiscard(webClientA, boardB, playerA);
				dc = JsonPath.parse(jsonText);
				query = "discard.length()";
				Assert.assertEquals(0, (int)dc.read(query));				
				
				jsonText = GameUtils.viewDiscard(webClientA, boardB, playerB);
				dc = JsonPath.parse(jsonText);
				query = "discard.length()";
				Assert.assertEquals(1, (int)dc.read(query));
				int card = (int)dc.read("discard[0]");
				Assert.assertEquals(deckB.findCard(0).getId(), card);

				GameUtils.endTurn(webClientB, Board.createBoard(webClientB, gameId));
				
				Board boardA = Board.createBoard(webClientA, gameId);
				GameUtils.playTrainer(webClientA, boardA, deckA.findCard(0));

				boardB = Board.createBoard(webClientB, gameId);
				
				//Note that this time we're going back to having player B look at the discards on player A's turn
				jsonText = GameUtils.viewDiscard(webClientA, boardB, playerA);
				dc = JsonPath.parse(jsonText);
				query = "discard.length()";
				Assert.assertEquals(1, (int)dc.read(query));				
				card = (int)dc.read("discard[0]");
				Assert.assertEquals(deckA.findCard(0).getId(), card);
				
				jsonText = GameUtils.viewDiscard(webClientA, boardB, playerB);
				dc = JsonPath.parse(jsonText);
				query = "discard.length()";
				Assert.assertEquals(1, (int)dc.read(query));
				card = (int)dc.read("discard[0]");
				Assert.assertEquals(deckB.findCard(0).getId(), card);
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewDiscardFailureNotMyGame() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testViewDiscardFailureNotMyGameA", "testViewDiscardFailureNotMyGameAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testViewDiscardFailureNotMyGameB", "testViewDiscardFailureNotMyGameBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);	
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				Board boardA = Board.createBoard(webClientA, gameId);
				
			//Player C
				WebClient webClientC = new WebClient();
				Player.createPlayer(webClientC, "testViewDiscardFailureNotMyGameC", "testViewDiscardFailureNotMyGameCPass");
				
				jsonText = GameUtils.viewDiscard(webClientC, boardA, playerB);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewDiscardFailureViewingDiscardOfSomeoneNotPlaying() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testViewDiscardFailureViewingDiscardOfSomeoneNotPlayingA", "testViewDiscardFailureViewingDiscardOfSomeoneNotPlayingAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
	
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testViewDiscardFailureViewingDiscardOfSomeoneNotPlayingB", "testViewDiscardFailureViewingDiscardOfSomeoneNotPlayingBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK1);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
	
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
	
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);	
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" + playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				Board boardA = Board.createBoard(webClientA, gameId);
				
			//Player C
				WebClient webClientC = new WebClient();
				Player playerC = Player.createPlayer(webClientC, "testViewDiscardFailureViewingDiscardOfSomeoneNotPlayingC", "testViewDiscardFailureViewingDiscardOfSomeoneNotPlayingCPass");
				
				jsonText = GameUtils.viewDiscard(webClientA, boardA, playerC);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayEnergySuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayEnergySuccessA", "testPlayEnergySuccessA");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
		
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayEnergySuccessB", "testPlayEnergySuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
		
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
		
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				
				Board board;
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				board = Board.createBoard(webClientB, gameId);
				//Now it's player B's turn. Let's play that Pikachu and play that lightning on it.
				GameUtils.playPokemonToBench(webClientB, board, deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); //Get that new version!
				GameUtils.attachEnergy(webClientB, board, deckB.findCard(2), deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); //Let's see if it got played properly.
			
				List<Card> attachedEnergy = board.getAtachedEnergy(playerB, deckB, deckB.findCard(1));
				Assert.assertTrue(!attachedEnergy.isEmpty());
				Assert.assertEquals(deckB.findCard(2), attachedEnergy.get(0));
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayEnergySuccessMultipleEnergyOnConsecutiveTurns() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayEnergySuccessMultipleEnergyOnConsecutiveTurnsA", "testPlayEnergySuccessMultipleEnergyOnConsecutiveTurnsAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
		
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayEnergySuccessMultipleEnergyOnConsecutiveTurnsB", "testPlayEnergySuccessMultipleEnergyOnConsecutiveTurnsBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
		
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
		
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				
				Board board;
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				board = Board.createBoard(webClientB, gameId);
				//Now it's player B's turn. Let's play that Pikachu and play that lightning on it.
				GameUtils.playPokemonToBench(webClientB, board, deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); //Get that new version!
				GameUtils.attachEnergy(webClientB, board, deckB.findCard(2), deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); //Let's see if it got played properly.
			
				
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				
				board = Board.createBoard(webClientB, gameId);
				GameUtils.playPokemonToBench(webClientB, board, deckB.findCard(3));
				board = Board.createBoard(webClientB, gameId); 
				GameUtils.attachEnergy(webClientB, board, deckB.findCard(4), deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); 

				List<Card> attachedEnergy = board.getAtachedEnergy(playerB, deckB, deckB.findCard(1));
				//The first Pokemon played got both energy
				Assert.assertTrue(attachedEnergy.contains(deckB.findCard(2)));
				Assert.assertTrue(attachedEnergy.contains(deckB.findCard(4)));
				attachedEnergy = board.getAtachedEnergy(playerB, deckB, deckB.findCard(3));
				//The other Pokemon has no energy
				Assert.assertTrue(attachedEnergy.isEmpty());
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayEnergyFailureMultipleEnergyOnSameTurn() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testPlayEnergyFailureMultipleEnergyOnSameTurnA", "testPlayEnergyFailureMultipleEnergyOnSameTurnAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
		
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testPlayEnergyFailureMultipleEnergyOnSameTurnB", "testPlayEnergyFailureMultipleEnergyOnSameTurnBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
		
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
		
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				jsonText = GameUtils.viewBoard(webClientA, gameId);
				dc = JsonPath.parse(jsonText);
				
				
				Board board;
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				GameUtils.endTurn(webClientB, board = Board.createBoard(webClientB, gameId));
				GameUtils.endTurn(webClientA, board = Board.createBoard(webClientA, gameId));
				board = Board.createBoard(webClientB, gameId);
				//Now it's player B's turn. Let's play that Pikachu and play that lightning on it.
				GameUtils.playPokemonToBench(webClientB, board, deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); //Get that new version!
				GameUtils.attachEnergy(webClientB, board, deckB.findCard(2), deckB.findCard(1));
				board = Board.createBoard(webClientB, gameId); //Let's see if it got played properly.
				GameUtils.playPokemonToBench(webClientB, board, deckB.findCard(3));
				board = Board.createBoard(webClientB, gameId); 
				jsonText=GameUtils.attachEnergy(webClientB, board, deckB.findCard(4), deckB.findCard(1));
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRetireFromGameSuccess() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testRetireFromGameSuccessA", "testRetireFromGameSuccessAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
		
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testRetireFromGameSuccessB", "testRetireFromGameSuccessBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);
		
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
		
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				Board boardA = Board.createBoard(webClientA, gameId);
				
				jsonText = GameUtils.retire(webClientB, boardA);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("success", dc.read("$['status']"));
				
				boardA = Board.createBoard(webClientA, gameId);
				Assert.assertNotEquals("playing", boardA.getStatus(playerB));
				Assert.assertEquals("playing", boardA.getStatus(playerA));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRetireFromGameFailureNotMyGame() {
		try {
			//Player A
				WebClient webClientA = new WebClient();
				String jsonText;
				DocumentContext dc;
				List<Map<String, Object>> jPathResult;
				Player playerA = Player.createPlayer(webClientA, "testRetireFromGameFailureNotMyGameA", "testRetireFromGameFailureNotMyGameAPass");
				Deck deckA = Deck.createDeck(webClientA, Deck.TEST_DECK1);
		
			//Player B
				WebClient webClientB = new WebClient();
				Player playerB = Player.createPlayer(webClientB, "testRetireFromGameFailureNotMyGameB", "testRetireFromGameFailureNotMyGameBPass");
				Deck deckB = Deck.createDeck(webClientB, Deck.TEST_DECK6);
				GameUtils.challengePlayer(webClientB, playerA, deckB);

			//Player C
				WebClient webClientC = new WebClient();
				Player.createPlayer(webClientC, "testRetireFromGameFailureNotMyGameC", "testRetireFromGameFailureNotMyGameCPass");
				
			//Player A
				jsonText = GameUtils.listChallenges(webClientA);
				dc = JsonPath.parse(jsonText);
				String query = "challenges[?(@['challenger']==" + playerB.getId() + " && @['challengee']==" + playerA.getId() + " && @['status']==0)]";
				jPathResult = dc.read(query);
				
				int challengeId = (Integer)((Object)jPathResult.get(0).get("id"));
				int challengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
		
				jsonText = GameUtils.acceptChallenge(webClientA, challengeId, challengeVersion, deckA);
				
				jsonText = GameUtils.listGames(webClientA);
				dc = JsonPath.parse(jsonText);
				query = "games[?(@['players'][0]==" + playerB.getId() + "  && @['players'][1]==" +  playerA.getId() + ")].id";
				jPathResult = dc.read(query);
				int gameId = (Integer)((Object)jPathResult.get(0));
			
				Board boardA = Board.createBoard(webClientA, gameId);
				
				jsonText = GameUtils.retire(webClientC, boardA);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals("fail", dc.read("$['status']"));
				
				boardA = Board.createBoard(webClientA, gameId);
				Assert.assertEquals("playing", boardA.getStatus(playerB));
				Assert.assertEquals("playing", boardA.getStatus(playerA));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	
}
