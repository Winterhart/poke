package org.soen387.app.Domain.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.soen387.app.Domain.POJO.deck.ICard;

public interface IHand extends IDomainObject<Long>  {
	
	public List<ICard> getCards();
	public void setCards(List<ICard> cards);

}
