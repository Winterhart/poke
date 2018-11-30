package org.soen387.dom.POJO.deck;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.ser.TDG.ChallengeTDG;

public class DeckFactory {
	
	//Quick Builder
	public static Deck createNew(List<ICard> cards) throws SQLException, MapperException{
		Long version = (long) 1;
		return createNew(ChallengeTDG.getMaxId(), version, cards);
	}
	
	// Full builder 
	public static Deck createNew(Long id, long version, List<ICard> cards) throws SQLException, MapperException{
		Deck obj = new Deck(id, version, cards);
		UoW.getCurrent().registerNew(obj);
		return obj;
	}
	
	public static Deck createClean(Long id, long version, List<ICard> cards) {
		Deck obj = new Deck(id, version, cards);
		UoW.getCurrent().registerClean(obj);
		return obj;
	}

}
