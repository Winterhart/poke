package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.dom.POJO.deck.ICard;

public class Discard extends DomainObject<Long> implements IDiscard {
	private List<ICard> cards;
	
	public List<ICard> getCards() {
		return cards;
	}
	public void setCards(List<ICard> cards) {
		this.cards = cards;
	}
	protected Discard(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Discard(Long id, long version, List<ICard> cards) {
		super(id, version);
		this.cards = cards;
	}



}
