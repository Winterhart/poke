package org.soen387.dom.POJO.game;

import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.game.DiscardInputMapper;
import org.soen387.dom.POJO.deck.ICard;

public class DiscardProxy extends DomainObjectProxy<Long, Discard> implements IDiscard {

	protected DiscardProxy(Long id) {
		super(id);
	}

	@Override
	protected Discard getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return DiscardInputMapper.find(getId());
		}catch(Exception ee) {
			throw new MapperException(ee.getMessage());
		}
	}



	@Override
	public List<ICard> getCards() {
		return getInnerObject().getCards();
	}

	@Override
	public void setCards(List<ICard> cards) {
		getInnerObject().setCards(cards);
		
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

}
