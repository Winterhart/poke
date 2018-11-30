package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;
import org.soen387.dom.POJO.deck.ICard;


@IDFieldType(Long.class)
@Interface(IBench.class)
@ExternalProducer(BenchProxy.class)
public class Bench extends DomainObject<Long> implements IBench {
	private Long cardId;
	private Long gameId;
	private Long deckId;

	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getDeckId() {
		return deckId;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}
	protected Bench(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	public Bench(Long id, long version, Long cardId, Long gameId, Long deckId) {
		super(id, version);
		this.cardId = cardId;
		this.gameId = gameId;
		this.deckId = deckId;
	}


}
