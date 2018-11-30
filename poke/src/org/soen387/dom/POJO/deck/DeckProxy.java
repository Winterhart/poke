package org.soen387.dom.POJO.deck;


import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.deck.DeckInputMapper;

public class DeckProxy extends DomainObjectProxy<Long, Deck> implements IDeck  {

	public DeckProxy(Long id) {
		super(id);
	}

	@Override
	protected Deck getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return DeckInputMapper.find(getId());
		}catch(Exception ee) {
			throw new MapperException(ee.getMessage());
		}
	}
	
	@Override
	public Long getOwnerId() {
		return getInnerObject().getOwnerId();
	}

	@Override
	public void setOwnerId(Long ownerId) {
		getInnerObject().setOwnerId(ownerId);
	}

}
