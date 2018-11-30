package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;
import org.soen387.dom.POJO.deck.ICard;

@IDFieldType(Long.class)
@Interface(Discard.class)
@ExternalProducer(DiscardProxy.class)

public class Discard extends DomainObject<Long> implements IDiscard {
	private List<ICard> cards;
	private Long gameId;
	private Long deckId;
	
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
	
	public Discard(Long id, long version, List<ICard> cards, Long gameId, Long deckId) {
		super(id, version);
		this.cards = cards;
		this.gameId = gameId;
		this.deckId = deckId;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getDeckId() {
		return deckId;
	}
	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}



}
