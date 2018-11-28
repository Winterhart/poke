package org.soen387.app.Domain.POJO.deck;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;

public class Deck extends DomainObject<Long> implements IDeck {
	

	private List<ICard> cards;
	protected Deck(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Deck(Long id, long version, List<ICard> cards) {
		super(id, version);
		this.cards = cards;
	}

	public List<ICard> getCards() {
		return cards;
	}

	public void setCards(List<ICard> cards) {
		this.cards = cards;
	}
	

	
	


}
