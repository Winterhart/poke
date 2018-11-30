package org.soen387.dom.POJO.deck;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;

@IDFieldType (Long.class)
@Interface (IDeck.class)
@ExternalProducer (DeckProxy.class)
public class Deck extends DomainObject<Long> implements IDeck {
	

	private List<ICard> cards;
	private Long ownerId;
	
	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	protected Deck(Long id, long version) {
		super(id, version);
	}
	
	public Deck(Long id, long version, List<ICard> cards, Long ownerId) {
		super(id, version);
		this.cards = cards;
		this.ownerId = ownerId;
	}

	public List<ICard> getCards() {
		return cards;
	}

	public void setCards(List<ICard> cards) {
		this.cards = cards;
	}
	

	
	


}
