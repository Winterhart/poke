import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class TestJSONPath {

	public static void main(String[] args) {
		try {
			/*
			String jsonText = "";
			BufferedReader br = new BufferedReader(new FileReader("challenge.json"));
			String line = "";
			while((line = br.readLine()) != null) {
				jsonText += line + "\n";
			}
			
			System.out.println(jsonText);
			
			DocumentContext dc = JsonPath.parse(jsonText);
			String query = "challenges[?(@['challenger']==1 && @['challengee']==2 && @['status']==3)]";
			List<Map<String, Object>> jPathResult = dc.read(query);
			
			int ChallengeId = (Integer)((Object)jPathResult.get(0).get("id"));
			int ChallengeVersion =(Integer)((Object)jPathResult.get(0).get("version"));
			
			System.out.println(ChallengeId);
			System.out.println(ChallengeVersion);
			*/
			
			/*
			String jsonText = "";
			BufferedReader br = new BufferedReader(new FileReader("emptyDecks.json"));
			String line = "";
			while((line = br.readLine()) != null) {
				jsonText += line + "\n";
			}
			
			System.out.println(jsonText);
			
			DocumentContext dc = JsonPath.parse(jsonText);
			String query = "decks[*]";
			List<Map<String, Object>> jPathResult = dc.read(query);
			System.out.println(jPathResult.size());
			*/
			
			/*
			String jsonText = "";
			BufferedReader br = new BufferedReader(new FileReader("listGames.json"));
			String line = "";
			while((line = br.readLine()) != null) {
				jsonText += line + "\n";
			}
			
			System.out.println(jsonText);
			
			DocumentContext dc = JsonPath.parse(jsonText);
			String query = "games[?((@.players[0]==1 || @.players[1]==1) && (@.players[0]==2 || @.players[1]==2)) ]";
			List<Map<String, Object>> jPathResult = dc.read(query);
			System.out.println(jPathResult);
			*/
			
			String jsonText = "";
			BufferedReader br = new BufferedReader(new FileReader("board.json"));
			String line = "";
			while((line = br.readLine()) != null) {
				jsonText += line + "\n";
			}
			
			System.out.println(jsonText);
			DocumentContext dc = JsonPath.parse(jsonText);
			List<Map<String, Object>> target = dc.read("game.play['2'].bench[?(@['id']==42)]");
			System.out.println(target.get(0));
			for(int i:(List<Integer>)target.get(0).get("e")) {
				System.out.print(i + " ");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
