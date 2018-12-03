package org.soen387.dom.POJO.game;


import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;

@IDFieldType(Long.class)
@Interface(Discard.class)
@ExternalProducer(DiscardProxy.class)

public class Discard extends DomainObject<Long> implements IDiscard {
	private Long cardId;
	private Long gameId;
	private Long linkCId;
	
	protected Discard(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Discard(Long id, long version, Long cardId, Long gameId, Long deckId, Long linkCId) {
		super(id, version);
		this.cardId = cardId;
		this.gameId = gameId;
		this.deckId = deckId;
		this.linkCId = linkCId;
	}
	
	public Long getLinkCId() {
		return linkCId;
	}

	public void setLinkCId(Long linkCId) {
		this.linkCId = linkCId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
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
	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}



}
