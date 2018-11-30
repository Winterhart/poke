package org.soen387.dom.POJO.deck;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.deck.CardInputMapper;

public class CardProxy extends DomainObjectProxy<Long, Card> implements ICard {

public CardProxy(Long id) {
	super(id);
}

@Override
protected Card getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
	try {
		return CardInputMapper.find(getId());
	}catch(Exception ee) {
		throw new MapperException(ee);
	}
}

@Override
public String getName() {
	return getInnerObject().getName();
}

@Override
public void setName(String name) {
	getInnerObject().setName(name);
	
}

@Override
public CardType getCardType() {
	return getInnerObject().getCardType();
}

@Override
public void setCardType(CardType cardType) {
	getInnerObject().setCardType(cardType);
	
}

@Override
public String getBase() {
	return getInnerObject().getBase();
}

@Override
public void setBase(String base) {
	getInnerObject().setBase(base);
	
}

@Override
public Long getDeckId() {
	return getInnerObject().getDeckId();
}

@Override
public void setDeckId(Long deckId) {
	getInnerObject().setDeckId(deckId);
	
}
	
}
