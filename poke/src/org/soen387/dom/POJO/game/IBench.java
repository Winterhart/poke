package org.soen387.dom.POJO.game;


import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IBench extends IDomainObject<Long>  {
	public Long getCardId();
	public void setCardId(Long cardId);
	public Long getGameId(); 
	public void setGameId(Long gameId);
	public Long getDeckId();
	public void setDeckId(Long deckId);
}
