import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Board {
	
	DocumentContext dc = null;
	
	public Board(DocumentContext dc) {
		super();
		this.dc = dc;
	}

	public static Board createBoard(WebClient webClient, int game) throws MalformedURLException, IOException {
		return new Board(JsonPath.parse(GameUtils.viewBoard(webClient, game)));
	}

	public int getVersion() {
		return (int)dc.read("game.version");
	}
	
	
	
}
