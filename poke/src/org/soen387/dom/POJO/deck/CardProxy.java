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
public String getName() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setName(String name) {
	// TODO Auto-generated method stub
	
}

@Override
public CardType getCardType() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setCardType(CardType cardType) {
	// TODO Auto-generated method stub
	
}

@Override
public String getBase() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setBase(String base) {
	// TODO Auto-generated method stub
	
}

@Override
protected Card getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
	try {
		return CardInputMapper.find(getId());
	}catch(Exception ee) {
		throw new MapperException(ee);
	}
}
	
}
