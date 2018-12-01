import java.util.Map;

public class Card {
	Map<String, Object> jPathResult = null;
	
	public Card(Map<String, Object> jPathResult) {
		super();
		this.jPathResult = jPathResult;
	}
	public int getId() {
		return (int)jPathResult.get("id");
	}
	public String getType() {
		return (String)jPathResult.get("t");
	}
	public String getName() {
		return (String)jPathResult.get("n");
	}
	public String getBasicName() {
		return (String)jPathResult.get("b");
	}
}
