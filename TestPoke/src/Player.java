import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Player {
	int id;
	String name;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public static Player createPlayer(WebClient webClient, String username, String password) throws MalformedURLException, IOException {
		String jsonText = GameUtils.register(webClient, username, password);
		DocumentContext dc = JsonPath.parse(jsonText);
		Assert.assertEquals("success", dc.read("$['status']"));

		int id = findPlayerId(webClient, username);
		return new Player(id, username);
	}
	
	private static int findPlayerId(WebClient webClient, String username) throws MalformedURLException, IOException {
		List<Map<String, Object>> jPathResult = null;
		String jsonText = GameUtils.listPlayers(webClient);
		DocumentContext dc = JsonPath.parse(jsonText);
		jPathResult = dc.read("players[?(@.user=='" + username + "')].id");
		return (Integer)((Object)jPathResult.get(0));
		
	}
	
	public static Player loginPlayer(WebClient webClient, String username, String password) throws MalformedURLException, IOException {
		String jsonText = GameUtils.login(webClient, username, password);
		DocumentContext dc = JsonPath.parse(jsonText);
		Assert.assertEquals("success", dc.read("$['status']"));
		
		int id = findPlayerId(webClient, username);
		return new Player(id, username);
	}
	
}
