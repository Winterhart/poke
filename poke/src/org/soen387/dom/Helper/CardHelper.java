package org.soen387.dom.Helper;

public class CardHelper {

	private String cardType;
	private String cardName;
	private String base;
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public CardHelper(String cardName, String cardType, String base) {
		super();
		this.cardType = cardType;
		this.cardName = cardName;
		this.base = base;
	}
	
	
}
