package org.soen387.dom.POJO.game;

import java.sql.SQLException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.ser.TDG.GameTDG;

public class GameFactory {
	//Quick Builder
	public static Game createNew(Long challengerId,
			Long challengeeId, Long challengerDeckId, Long challengeeDeckId) throws SQLException, MapperException{
		
		Long version = (long) 1;
		return createNew(GameTDG.getMaxId(), version, challengerId, challengeeId, challengerDeckId, challengeeDeckId);
	}
	
	// Full builder 
	public static Game createNew(Long id, long version, Long challengerId,
			Long challengeeId, Long challengerDeckId, Long challengeeDeckId) throws SQLException, MapperException{
		
		Game obj = new Game(id, version, challengerId, challengeeId, challengerDeckId, challengeeDeckId);
		UoW.getCurrent().registerNew(obj);
		return obj;
	}
	
	public static Game createClean(Long id, long version, Long challengerId,
			Long challengeeId, Long challengerDeckId, Long challengeeDeckId) {
		
		Game obj = new Game(id, version, challengerId, challengeeId, challengerDeckId, challengeeDeckId);
		UoW.getCurrent().registerClean(obj);
		return obj;
	}
}
