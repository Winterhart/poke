package org.soen387.dom.POJO.game;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.ser.TDG.DiscardTDG;

public class DiscardFactory {
	
	//Quick Builder
	public static Discard createNew(List<ICard> cards, Long gameId, Long deckId) throws SQLException, MapperException{
		
		Long version = (long) 1;
		return createNew(DiscardTDG.getMaxId(), version, cards, gameId, deckId);
	}
	
	// Full builder 
	public static Discard createNew(Long id, long version, List<ICard> cards, Long gameId, Long deckId) throws SQLException, MapperException{
		
		Discard obj = new Discard(id, version, cards, gameId, deckId);
		UoW.getCurrent().registerNew(obj);
		return obj;
	}
	
	public static Discard createClean(Long id, long version, List<ICard> cards, Long gameId, Long deckId) {
		
		Discard obj = new Discard(id, version, cards, gameId, deckId);
		UoW.getCurrent().registerClean(obj);
		return obj;
	}

}
