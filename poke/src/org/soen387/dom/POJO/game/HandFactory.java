package org.soen387.dom.POJO.game;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.ser.TDG.DiscardTDG;

public class HandFactory {
	
	//Quick Builder
	public static Hand createNew(Long cardId, Long gameId, Long deckId) throws SQLException, MapperException{
		
		Long version = (long) 1;
		return createNew(DiscardTDG.getMaxId(), version, cardId, gameId, deckId);
	}
	
	// Full builder 
	public static Hand createNew(Long id, long version, Long cardId, Long gameId, Long deckId) throws SQLException, MapperException{
		
		Hand obj = new Hand(id, version, cardId, gameId, deckId);
		UoW.getCurrent().registerNew(obj);
		return obj;
	}
	
	public static Hand createClean(Long id, long version, Long cardId, Long gameId, Long deckId) {
		
		Hand obj = new Hand(id, version, cardId, gameId, deckId);
		UoW.getCurrent().registerClean(obj);
		return obj;
	}

}
