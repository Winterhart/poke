
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class TestSuite {
	String URL_BASE = "http://localhost:12503/poke/";
	
	private final static Logger testLogger = Logger.getLogger(TestSuite.class.getName());
	
	
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
	
	private String register(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"Register"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("user", username));
		requestSettings.getRequestParameters().add(new NameValuePair("pass", password));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String login(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"Login"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("user", username));
		requestSettings.getRequestParameters().add(new NameValuePair("pass", password));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String logout(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"Logout"), HttpMethod.POST);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String listPlayers(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ListPlayers"), HttpMethod.GET);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String challengePlayer(WebClient webClient, long player)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ChallengePlayer"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("player", player+""));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String listChallenges(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ListChallenges"), HttpMethod.GET);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String acceptChallenge(WebClient webClient, long challenge)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"AcceptChallenge"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("challenge", challenge+""));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String refuseChallenge(WebClient webClient, long challenge)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"RefuseChallenge"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("challenge", challenge+""));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String uploadDeck(WebClient webClient, String deck)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"UploadDeck"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("deck", deck));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String viewDeck(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ViewDeck"), HttpMethod.GET);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String drawCard(WebClient webClient, long game)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"DrawCard"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("game", game+""));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String playPokemonToBench(WebClient webClient, long game, long card)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"PlayPokemonToBench"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("game", game+""));
		requestSettings.getRequestParameters().add(new NameValuePair("card", card+""));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String viewBoard(WebClient webClient, long game)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ViewBoard"+"?game="+game), HttpMethod.GET);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String viewHand(WebClient webClient, long game)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ViewHand"+"?game="+game), HttpMethod.GET);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String listGames(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"ListGames"), HttpMethod.GET);

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String retire(WebClient webClient, long game)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"Retire"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("game", game+""));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	@Test
	public void testRegisterNoInfo() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "", "");
			DocumentContext dc = JsonPath.parse(jsonText);
			
			Assert.assertEquals("fail", dc.read("$['status']"));

			jsonText = register(webClient, "", "fred2");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = register(webClient, "bob2", "");
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
			String jsonText = register(webClient, "bob", "fred");
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
			String jsonText = register(webClient, "bob1", "fred1");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "bob1", "fred1");
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
			String jsonText = register(webClient, "bob3", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = login(webClient, "bob3", "fred3");
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
			String jsonText = login(webClient, "bob5", "fred3");
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
			String jsonText = logout(webClient);
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
			String jsonText = register(webClient, "bob6", "fred6");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = logout(webClient);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testListPlayersSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testListPlayersSuccessA", "testListPlayersSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			List<Map<String, Object>> jPathResult = null;
			
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListPlayersSuccessA')].user");
			Assert.assertEquals("testListPlayersSuccessA", jPathResult.get(0));

			jsonText = register(webClient, "testListPlayersSuccessB", "testListPlayersSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = listPlayers(webClient);
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
			String jsonText = listPlayers(webClient);
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
			String jsonText = register(webClient, "testChallengePlayerSuccessA", "testChallengePlayerSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testChallengePlayerSuccessB", "testChallengePlayerSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerSuccessA')].id");
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = challengePlayer(webClient, (Integer)((Object)(jPathResult.get(0))));
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testChallengePlayerFailureNoDeck() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testChallengePlayerFailureNoDeckA", "testChallengePlayerFailureNoDeckAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testChallengePlayerFailureNoDeckB", "testChallengePlayerFailureNoDeckBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerFailureNoDeckA')].id");
			
			jsonText = challengePlayer(webClient, (Integer)((Object)(jPathResult.get(0))));
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
			String jsonText = register(webClient, "testChallengePlayerFailureChallengeSelfA", "testChallengePlayerFailureChallengeSelfAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testChallengePlayerFailureChallengeSelfB", "testChallengePlayerFailureChallengeSelfBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testChallengePlayerFailureChallengeSelfB')].id");
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = challengePlayer(webClient, (Integer)((Object)(jPathResult.get(0))));
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
			String jsonText = register(webClient, "testChallengePlayerFailureChallengeInvalidA", "testChallengePlayerFailureChallengeInvalidAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testChallengePlayerFailureChallengeInvalidB", "testChallengePlayerFailureChallengeInvalidBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = challengePlayer(webClient, -12);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testListChallengesSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testListChallengesSuccessA", "testListChallengesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testListChallengesSuccessB", "testListChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListChallengesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListChallengesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
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
			String jsonText = register(webClient, "testListChallengesFailureNotLoggedInA", "testListChallengesFailureNotLoggedInAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testListChallengesFailureNotLoggedInB", "testListChallengesFailureNotLoggedInBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListChallengesFailureNotLoggedInA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListChallengesFailureNotLoggedInB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			logout(webClient);
			
			jsonText = challengePlayer(webClient, idA);
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
			String jsonText = register(webClient, "testAcceptChallengesSuccessA", "testAcceptChallengesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testAcceptChallengesSuccessB", "testAcceptChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testAcceptChallengesSuccessA", "testAcceptChallengesSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int ChallengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, ChallengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
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
			String jsonText = register(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeA", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeB", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeC", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptSomeoneElsesChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptSomeoneElsesChallengeB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptSomeoneElsesChallengeC')].id");
			int idC = (Integer)((Object)jPathResult.get(0));
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			int ChallengeId = (Integer)((Object)jPathResult.get(0));
			
			logout(webClient);
			
			jsonText = login(webClient, "testAcceptChallengesFailureAcceptSomeoneElsesChallengeB", "testAcceptChallengesFailureAcceptSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = acceptChallenge(webClient, ChallengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
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
			String jsonText = register(webClient, "testAcceptChallengesFailureAcceptOwnChallengeA", "testAcceptChallengesFailureAcceptOwnChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testAcceptChallengesFailureAcceptOwnChallengeB", "testAcceptChallengesFailureAcceptOwnChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
		
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptOwnChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testAcceptChallengesFailureAcceptOwnChallengeB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));

			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			int ChallengeId = (Integer)((Object)jPathResult.get(0));

			jsonText = acceptChallenge(webClient, ChallengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
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
	public void testRefuseChallengesSuccess() {
		try {

			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testRefuseChallengesSuccessA", "testRefuseChallengesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testRefuseChallengesSuccessB", "testRefuseChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testRefuseChallengesSuccessC", "testRefuseChallengesSuccessCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
		
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesSuccessC')].id");
			int idC = (Integer)((Object)jPathResult.get(0));
			
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = challengePlayer(webClient, idB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			int ChallengeIdCvA = (Integer)((Object)jPathResult.get(0));
			query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idB + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			int ChallengeIdCvB = (Integer)((Object)jPathResult.get(0));
			
			//Withdraw and check that it is listed as withdrawn
			jsonText = refuseChallenge(webClient, ChallengeIdCvA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = login(webClient, "testRefuseChallengesSuccessB", "testRefuseChallengesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			//Refuse and check that it is listed as refused
			jsonText = refuseChallenge(webClient, ChallengeIdCvB);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = listChallenges(webClient);
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
			String jsonText = register(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeA", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeB", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeC", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
		
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesFailureRefuseSomeoneElsesChallengeA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRefuseChallengesFailureRefuseSomeoneElsesChallengeC')].id");
			int idC = (Integer)((Object)jPathResult.get(0));
			
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idC + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			int ChallengeIdCvA = (Integer)((Object)jPathResult.get(0));

			
			jsonText = login(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeB", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			//Refuse and check that it is listed as refused
			jsonText = refuseChallenge(webClient, ChallengeIdCvA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));

			
			jsonText = login(webClient, "testRefuseChallengesFailureRefuseSomeoneElsesChallengeC", "testRefuseChallengesFailureRefuseSomeoneElsesChallengeCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
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
			String jsonText = register(webClient, "testListGamesSuccessA", "testListGamesSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testListGamesSuccessB", "testListGamesSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testListGamesSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testListGamesSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testListGamesSuccessA", "testListGamesSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int ChallengeId = (Integer)((Object)jPathResult.get(0));

			//Show that accepting challenges causes games to be listed
			//confirm that the players are the challenger and challengee
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = acceptChallenge(webClient, ChallengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].length()";
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
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testViewBoardSuccessA", "testViewBoardSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testViewBoardSuccessB", "testViewBoardSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testViewBoardSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testViewBoardSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testViewBoardSuccessA", "testViewBoardSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = viewBoard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			query = "board[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int foundGameId = (Integer)((Object)jPathResult.get(0));
			Assert.assertEquals(gameId, foundGameId);
			query = "board.play."+idB+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "board.play."+idA+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "board.play."+idB+".handsize";
			Assert.assertEquals(0, (int)dc.read(query));
			query = "board.play."+idA+".handsize";
			Assert.assertEquals(0, (int)dc.read(query));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewBoardFailureNotMyGame() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testViewBoardFailureNotMyGameA", "testViewBoardFailureNotMyGameAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testViewBoardFailureNotMyGameB", "testViewBoardFailureNotMyGameBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testViewBoardFailureNotMyGameA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testViewBoardFailureNotMyGameB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testViewBoardFailureNotMyGameA", "testViewBoardFailureNotMyGameAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = register(webClient, "testViewBoardFailureNotMyGameC", "testViewBoardFailureNotMyGameCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = viewBoard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testDrawCardSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testDrawCardSuccessA", "testDrawCardSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testDrawCardSuccessB", "testDrawCardSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testDrawCardSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testDrawCardSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testDrawCardSuccessA", "testDrawCardSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = viewBoard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			query = "board.play."+idB+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "board.play."+idA+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "board.play."+idB+".handsize";
			Assert.assertEquals(0, (int)dc.read(query));
			query = "board.play."+idA+".handsize";
			Assert.assertEquals(0, (int)dc.read(query));
			
			//We know the content of the deck used, we know the order of cards, just test two of them... 
			// or do that when we test viewing hands so that this test doesn't depend on the other working.

			//Test that both players can log in and draw cards
			
			//Test that deck size and hand size change on drawing cards
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = viewBoard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			query = "board.play."+idB+".decksize";
			Assert.assertEquals(40, (int)dc.read(query));
			query = "board.play."+idA+".decksize";
			Assert.assertEquals(38, (int)dc.read(query));
			query = "board.play."+idB+".handsize";
			Assert.assertEquals(0, (int)dc.read(query));
			query = "board.play."+idA+".handsize";
			Assert.assertEquals(2, (int)dc.read(query));
			
			jsonText = login(webClient, "testDrawCardSuccessB", "testDrawCardSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = viewBoard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			query = "board.play."+idB+".decksize";
			Assert.assertEquals(39, (int)dc.read(query));
			query = "board.play."+idA+".decksize";
			Assert.assertEquals(38, (int)dc.read(query));
			query = "board.play."+idB+".handsize";
			Assert.assertEquals(1, (int)dc.read(query));
			query = "board.play."+idA+".handsize";
			Assert.assertEquals(2, (int)dc.read(query));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testDrawCardFailureNotMyGame() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testDrawCardFailureNotMyGameA", "testDrawCardFailureNotMyGameAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testDrawCardFailureNotMyGameB", "testDrawCardFailureNotMyGameBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testDrawCardFailureNotMyGameA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testDrawCardFailureNotMyGameB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testDrawCardFailureNotMyGameA", "testDrawCardFailureNotMyGameAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = register(webClient, "testDrawCardFailureNotMyGameC", "testDrawCardFailureNotMyGameCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = drawCard(webClient, gameId);
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
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testViewHandSuccessA", "testViewHandSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testViewHandSuccessB", "testViewHandSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testViewHandSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testViewHandSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testViewHandSuccessA", "testViewHandSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			// Test that drawing cards puts them properly in your hand
			jsonText = viewHand(webClient, gameId);
			dc = JsonPath.parse(jsonText);

			/*
			 * The definition of hand and card ids is poorly formed, so we
			 * don't have enough information to test for specifc ids. Darn.
			query = "hand[?(@==0 || @==1)]";
			jPathResult = dc.read(query);
			Assert.assertEquals(0, jPathResult.size());
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = viewHand(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read(query);
			Assert.assertEquals(2, jPathResult.size());
			*/

			query = "hand.length()";
			Assert.assertEquals(0, (int)dc.read(query));
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = drawCard(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = viewHand(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals(2, (int)dc.read(query));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testViewHandFailureNotMyGame() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testViewHandFailureNotMyGameA", "testViewHandFailureNotMyGameAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testViewHandFailureNotMyGameB", "testViewHandFailureNotMyGameBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testViewHandFailureNotMyGameA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testViewHandFailureNotMyGameB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testViewHandFailureNotMyGameA", "testViewHandFailureNotMyGameAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = register(webClient, "testViewHandFailureNotMyGameC", "testViewHandFailureNotMyGameCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = viewHand(webClient, gameId);
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
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testPlayPokemonToBenchSuccessA", "testPlayPokemonToBenchSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK5);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testPlayPokemonToBenchSuccessB", "testPlayPokemonToBenchSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testPlayPokemonToBenchSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testPlayPokemonToBenchSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK5);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testPlayPokemonToBenchSuccessA", "testPlayPokemonToBenchSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			drawCard(webClient, gameId);
			
			jsonText = playPokemonToBench(webClient, gameId, 0);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			//Test that a pokemon card ends up on bench after being drawn then played

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayPokemonToBenchFailureNotMyGame() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testPlayPokemonToBenchFailureNotMyGameA", "testPlayPokemonToBenchFailureNotMyGameAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK5);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testPlayPokemonToBenchFailureNotMyGameB", "testPlayPokemonToBenchFailureNotMyGameBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testPlayPokemonToBenchFailureNotMyGameA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testPlayPokemonToBenchFailureNotMyGameB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK5);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testPlayPokemonToBenchFailureNotMyGameA", "testPlayPokemonToBenchFailureNotMyGameAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			drawCard(webClient, gameId);
			
			jsonText = register(webClient, "testPlayPokemonToBenchFailureNotMyGameC", "testPlayPokemonToBenchFailureNotMyGameCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = playPokemonToBench(webClient, gameId, 0);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayPokemonToBenchFailurePlayingACardNotInHand() {
		try {
			Assert.assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPlayPokemonToBenchFailurePlayingACardThatIsNotAPokemon() {
		try {
			Assert.assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRetireFromGameSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testRetireFromGameSuccessA", "testRetireFromGameSuccessAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testRetireFromGameSuccessB", "testRetireFromGameSuccessBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testRetireFromGameSuccessA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRetireFromGameSuccessB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testRetireFromGameSuccessA", "testRetireFromGameSuccessAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = retire(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testRetireFromGameFailureNotMyGame() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "testRetireFromGameFailureNotMyGameA", "testRetireFromGameFailureNotMyGameAPass");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "testRetireFromGameFailureNotMyGameB", "testRetireFromGameFailureNotMyGameBPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			List<Map<String, Object>> jPathResult = null;
			jsonText = listPlayers(webClient);
			dc = JsonPath.parse(jsonText);
			jPathResult = dc.read("players[?(@.user=='testRetireFromGameFailureNotMyGameA')].id");
			int idA = (Integer)((Object)jPathResult.get(0));
			jPathResult = dc.read("players[?(@.user=='testRetireFromGameFailureNotMyGameB')].id");
			int idB = (Integer)((Object)jPathResult.get(0));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==" + idB + "  && @['challengee']==" + idA + ")].length()";
			jPathResult = dc.read(query);
			Assert.assertTrue(jPathResult.isEmpty());
			
			jsonText = challengePlayer(webClient, idA);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			logout(webClient);
			
			jsonText = login(webClient, "testRetireFromGameFailureNotMyGameA", "testRetireFromGameFailureNotMyGameAPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listChallenges(webClient);
			dc = JsonPath.parse(jsonText);
			query = "challenges[?(@['challenger']==" + idB + " && @['challengee']==" + idA + " && @['status']==0)].id";
			jPathResult = dc.read(query);
			
			int challengeId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = acceptChallenge(webClient, challengeId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = listGames(webClient);
			dc = JsonPath.parse(jsonText);
			query = "games[?(@['players'][0]==" + idB + "  && @['players'][1]==" + idA + ")].id";
			jPathResult = dc.read(query);
			int gameId = (Integer)((Object)jPathResult.get(0));
			
			jsonText = register(webClient, "testRetireFromGameFailureNotMyGameC", "testRetireFromGameFailureNotMyGameCPass");
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = retire(webClient, gameId);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	/*
	 * Testing Upload Deck
	 *  TestNotLoggedIn
	 *  
	 */

	@Test
	public void testUploadDeckFailNoLogin() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = uploadDeck(webClient, TEST_DECK1);
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckFailSmallDeck() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob7", "fred7");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK3_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}

	@Test
	public void testUploadDeckFailBigDeck() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob8", "fred8");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK4_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob9", "fred9");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testUploadDeckSuccessRecoverFromFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob10", "fred10");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK4_BAD);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
			
			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testViewDeckSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob11", "fred11");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = uploadDeck(webClient, TEST_DECK1);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));
			
			String[] cards = TEST_DECK1.split("\n");
			Assert.assertEquals(40, cards.length);
			for(int i = 0; i < cards.length; i++) {
				String line = cards[i];
				String type = line.substring(0,1);
				String name = line.substring(3,line.length()-1);
				jsonText = viewDeck(webClient);
				dc = JsonPath.parse(jsonText);
				Assert.assertEquals(type, dc.read("$['deck']['cards'][" + i + "]['t']"));
				Assert.assertEquals(name, dc.read("$['deck']['cards'][" + i + "]['n']"));
			}
			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testViewDeckFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob12", "fred12");
			DocumentContext dc = JsonPath.parse(jsonText);
			Assert.assertEquals("success", dc.read("$['status']"));

			jsonText = viewDeck(webClient);
			dc = JsonPath.parse(jsonText);
			Assert.assertEquals("fail", dc.read("$['status']"));
		} catch (IOException e) {
			
		}
	}
	
}
