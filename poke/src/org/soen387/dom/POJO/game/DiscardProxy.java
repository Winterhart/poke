package org.soen387.dom.POJO.game;


import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.game.DiscardInputMapper;

public class DiscardProxy extends DomainObjectProxy<Long, Discard> implements IDiscard {

	public DiscardProxy(Long id) {
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
	public Long getCardId() {
		return getInnerObject().getCardId();
	}


	@Override
	public void setCardId(Long cardId) {
		getInnerObject().setCardId(cardId);
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
	public Long getLinkCId() {
		return getInnerObject().getLinkCId();
	}

	@Override
	public void setLinkCId(Long linkCId) {
		getInnerObject().setLinkCId(linkCId);
		
	}

}
