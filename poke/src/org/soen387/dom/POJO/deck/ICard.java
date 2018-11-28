package org.soen387.dom.POJO.deck;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface ICard extends IDomainObject<Long> {
	
	public String getName();
	public void setName(String name);
	public CardType getCardType();
	public void setCardType(CardType cardType);
	public String getBase();
	public void setBase(String base);

}
