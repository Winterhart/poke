package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.game.HandInputMapper;
import org.soen387.dom.POJO.deck.ICard;

public class HandProxy extends DomainObjectProxy<Long, Hand> implements IHand {

	public HandProxy(Long id) {
		super(id);
	}
	

	@Override
	protected Hand getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return HandInputMapper.find(getId());
		}catch(Exception ee) {
			throw new MapperException(ee.getMessage());
		}
	}


	@Override
	public Long getGameId() {
		return getInnerObject().getGameId();
	}

	@Override
	public void setGameId(Long gameId) {
		getInnerObject().setGameId(gameId);
		
	}

	@Override
	public Long getDeckId() {
		return getInnerObject().getDeckId();
	}

	@Override
	public void setDeckId(Long deckId) {
		getInnerObject().setDeckId(deckId);
		
	}


	@Override
	public Long getCardId() {
		return getInnerObject().getCardId();
	}


	@Override
	public void setCardId(Long cardId) {
		getInnerObject().setCardId(cardId);
	}


}
