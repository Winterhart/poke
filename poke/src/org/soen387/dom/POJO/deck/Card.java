package org.soen387.app.Domain.POJO.deck;

import org.dsrg.soenea.domain.DomainObject;

public class Card extends DomainObject<Long> implements ICard {

	private String name;
	private CardType cardType;
	private String base;
	
	
	protected Card(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Card(Long id, long version, String name, 
			CardType type, String base) {
		
		super(id, version);
		this.name = name;
		this.cardType = type;
		this.base = base;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	
	
	
	
	
}
