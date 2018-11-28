package org.soen387.app.Domain.POJO.deck;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IDeck extends IDomainObject<Long>{

	public List<ICard> getCards();

	public void setCards(List<ICard> cards);
}
