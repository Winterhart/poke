import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Board {
	
	DocumentContext dc = null;
	int id;
	
	public Board(DocumentContext dc, int id) {
		super();
		this.dc = dc;
		this.id = id;
	}

	public static Board createBoard(WebClient webClient, int game) throws MalformedURLException, IOException {
		return new Board(JsonPath.parse(GameUtils.viewBoard(webClient, game)), game);
	}

	public int getVersion() {
		return (int)dc.read("game.version");
	}
	
	public int getHandSize(Player player) {
		return (int)dc.read("game.play."+player.getId()+".handsize");
	}

	public int getDeckSize(Player player) {
		return (int)dc.read("game.play."+player.getId()+".decksize");
	}

	public int getDiscardSize(Player player) {
		return (int)dc.read("game.play."+player.getId()+".discardsize");
	}
	
	public String getStatus(Player player) {
		return (String)dc.read("game.play."+player.getId()+".status");
	}
	
	public int getId() {
		return id;
	}
	
	
}
