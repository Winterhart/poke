package org.soen387.dom.POJO.game;

import java.sql.SQLException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.ser.TDG.GameTDG;

public class GameFactory {
	//Quick Builder
	public static Game createNew(Long challengerId, Long challengeeId, Long currentTurn, int numberOfTurn,
			String challengerStatus, String challengeeStatus, Long challengerDeck, Long challengeeDeck) throws SQLException, MapperException{
		
		Long version = (long) 1;
		return createNew(GameTDG.getMaxId(), version,  challengerId, challengeeId, currentTurn, numberOfTurn,
				challengerStatus, challengeeStatus, challengerDeck, challengeeDeck);
	}
	
	// Full builder 
	public static Game createNew(Long id, long version, Long challengerId, Long challengeeId, Long currentTurn, int numberOfTurn,
			String challengerStatus, String challengeeStatus, Long challengerDeck, Long challengeeDeck) throws SQLException, MapperException{
		
		Game obj = new Game(id, version, challengerId, challengeeId, currentTurn, numberOfTurn, challengerStatus, challengeeStatus,
				challengerDeck, challengeeDeck);
		UoW.getCurrent().registerNew(obj);
		return obj;
	}
	
	public static Game createClean(Long id, long version, Long challengerId, Long challengeeId, Long currentTurn, int numberOfTurn,
			String challengerStatus, String challengeeStatus, Long challengerDeck, Long challengeeDeck) {
		
		Game obj = new Game(id, version, challengerId, challengeeId, currentTurn, numberOfTurn, challengerStatus, challengeeStatus,
				challengerDeck, challengeeDeck);
		UoW.getCurrent().registerClean(obj);
		return obj;
	}
}
