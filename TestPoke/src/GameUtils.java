import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class GameUtils {

	public static String retire(WebClient webClient, Board game)
			throws MalformedURLException, IOException {
		return retire(webClient, game.getId(), game.getVersion());
	}
	public static String retire(WebClient webClient, long game, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/" + game + "/Retire"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String register(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Player/Register"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("user", username));
		requestSettings.getRequestParameters().add(new NameValuePair("pass", password));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String login(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Player/Login"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("user", username));
		requestSettings.getRequestParameters().add(new NameValuePair("pass", password));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String logout(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Player/Logout"), HttpMethod.POST);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String uploadDeck(WebClient webClient, String deck)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Deck"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("deck", deck));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String viewDecks(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Deck"), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String viewDeck(WebClient webClient, Deck deck)
			throws MalformedURLException, IOException {
		return viewDeck(webClient, deck.getId());
	}
	
	public static  String viewDeck(WebClient webClient, long deck)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Deck/"+deck), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String listPlayers(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Player"), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String challengePlayer(WebClient webClient, Player player, Deck deck)
			throws MalformedURLException, IOException {
		return challengePlayer(webClient, player.getId(), deck.getId());
	}
	
	public static  String challengePlayer(WebClient webClient, long player, long deck)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Player/" + player + "/Challenge"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("deck", deck+""));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String listChallenges(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Challenge"), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String withdrawChallenge(WebClient webClient, long challenge, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Challenge/" + challenge + "/Withdraw"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String refuseChallenge(WebClient webClient, long challenge, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Challenge/" + challenge + "/Refuse"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	
	public static  String acceptChallenge(WebClient webClient, long challenge, int version, Deck deck) 
			throws MalformedURLException, IOException {
		return acceptChallenge(webClient, challenge, version, deck.getId());
	}
	
	public static  String acceptChallenge(WebClient webClient, long challenge, int version, long deck)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Challenge/" + challenge + "/Accept"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("deck", deck+""));
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String listGames(WebClient webClient)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game"), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String viewBoard(WebClient webClient, long game)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/"+game), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String viewHand(WebClient webClient, long game)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/"+game+"/Hand"), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String viewDiscard(WebClient webClient, long game, long player)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/"+game+"/Player/" + player + "/Discard"), HttpMethod.GET);
	
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String playPokemonToBench(WebClient webClient, Board game, Card card)
			throws MalformedURLException, IOException {
		return playPokemonToBench(webClient, game.getId(), card.getId(), game.getVersion());
	}
	public static  String playPokemonToBench(WebClient webClient, long game, long card, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/" + game + "/Hand/" + card + "/Play"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String evolvePokemon(WebClient webClient, long game, long card, long target, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/" + game + "/Hand/" + card + "/Play"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("basic", target+""));
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String attachEnergy(WebClient webClient, long game, long card, long target, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/" + game + "/Hand/" + card + "/Play"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("pokemon", target+""));
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static  String playTrainer(WebClient webClient, Board game, Card card)
			throws MalformedURLException, IOException {
		return playPokemonToBench(webClient, game.getId(), card.getId(), game.getVersion());
	}
	public static  String playTrainer(WebClient webClient, long game, long card, int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/" + game + "/Hand/" + card + "/Play"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

	public static String endTurn(WebClient webClient, Board game)
			throws MalformedURLException, IOException {
		return endTurn(webClient, game.getId(), game.getVersion());
	}
	public static String endTurn(WebClient webClient, long game,int version)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(TestSuite.URL_BASE+"Poke/Game/" + game + "/EndTurn"), HttpMethod.POST);
	
		requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
		requestSettings.getRequestParameters().add(new NameValuePair("version", version+""));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		TestSuite.testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}

}
