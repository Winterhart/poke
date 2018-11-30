package org.soen387.dom.POJO.deck;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;

@IDFieldType(Long.class)
@Interface(ICard.class)
@ExternalProducer(CardProxy.class)

public class Card extends DomainObject<Long> implements ICard {

	private String name;
	private CardType cardType;
	private String base;
	private Long deckId;
	
	public Long getDeckId() {
		return deckId;
	}

	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}

	protected Card(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Card(Long id, long version, Long deckId, String name, 
			CardType type, String base) {
		
		super(id, version);
		this.deckId = deckId;
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
