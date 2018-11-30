package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;
import org.soen387.dom.POJO.deck.ICard;

@IDFieldType(Long.class)
@Interface(IHand.class)
@ExternalProducer(HandProxy.class)
public class Hand extends DomainObject<Long> implements IHand {
	private List<ICard> cards;
	protected Hand(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Hand(Long id, long version, List<ICard> cards) {
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
