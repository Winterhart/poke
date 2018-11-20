package org.soen387.app.Domain.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.app.Domain.POJO.deck.ICard;

public class Bench extends DomainObject<Long> implements IBench {
	private List<ICard> cards;

	protected Bench(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	public Bench(Long id, long version, List<ICard> cards) {
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
