import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	//Scarey unchecked suppression :\
	@SuppressWarnings("unchecked")
	public List<Card> getAtachedEnergy(Player player, Deck deck, Card card) {
		String query = "game.play['" + player.getId() + "'].bench[?(@['id']==" + card.getId() + ")]";
		List<Map<String, Object>> target = dc.read(query);
		List<Card> energy = new ArrayList<Card>();
		if(target.get(0).get("e")!=null) { 
			for(int i:(List<Integer>)target.get(0).get("e")) {
				for(int j = 0; j < 40; j++) {
					Card c = deck.findCard(j);
					if(c.getId()==i) {
						energy.add(c);
						break;
					}
				}
			}
		}
		return energy;
	}
	
	public Card getAtachedPokemon(Player player, Deck deck, int card) {
		List<Map<String, Object>> target = dc.read("game.play['" + player.getId() + "'].bench[?(@['id']==" + card + ")]");
		for(int j = 0; j < 40; j++) {
			Card c = deck.findCard(j);
			if(card==(int)target.get(0).get("b"))return c;
		}
		return null;
	}
	
	
}
