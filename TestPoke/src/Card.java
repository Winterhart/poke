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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Card) return this.equals((Card)obj);
		return super.equals(obj);
	}
	
	public boolean equals(Card obj) {
		if(obj==null) return false;
		return this.getId() == obj.getId();
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
}
