package org.soen387.app.Domain.Helper;

public class CardHelper {

	private String cardType;
	private String cardName;
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
	public CardHelper(String cardType, String cardName) {
		super();
		this.cardType = cardType;
		this.cardName = cardName;
	}
	
	
}
