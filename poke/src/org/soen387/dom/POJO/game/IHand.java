package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.soen387.dom.POJO.deck.ICard;

public interface IHand extends IDomainObject<Long>  {
	public Long getCardId();
	public void setCardId(Long cardId);
	public Long getGameId(); 
	public void setGameId(Long gameId);
	public Long getDeckId();
	public void setDeckId(Long deckId);

}
