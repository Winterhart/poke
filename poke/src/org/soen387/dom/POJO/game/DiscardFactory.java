package org.soen387.dom.POJO.game;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.ser.TDG.DiscardTDG;

public class DiscardFactory {
	
	//Quick Builder
	public static Discard createNew(Long cardId, Long gameId, Long deckId, Long linkCId) throws SQLException, MapperException{
		
		Long version = (long) 1;
		return createNew(DiscardTDG.getMaxId(), version, cardId, gameId, deckId, linkCId);
	}
	
	// Full builder 
	public static Discard createNew(Long id, long version, Long cardId, Long gameId, Long deckId, Long linkCId) throws SQLException, MapperException{
		
		Discard obj = new Discard(id, version, cardId, gameId, deckId, linkCId);
		UoW.getCurrent().registerNew(obj);
		return obj;
	}
	
	public static Discard createClean(Long id, long version, Long cardId, Long gameId, Long deckId, Long linkCId) {
		
		Discard obj = new Discard(id, version, cardId, gameId, deckId, linkCId);
		UoW.getCurrent().registerClean(obj);
		return obj;
	}

}
