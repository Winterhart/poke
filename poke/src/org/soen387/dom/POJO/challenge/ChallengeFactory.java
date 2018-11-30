package org.soen387.dom.POJO.challenge;

import java.sql.SQLException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.ser.TDG.ChallengeTDG;

public class ChallengeFactory {
	public static  Challenge createNew(Long challengerId, Long challengeeId,
			Long initialDec, int status) throws SQLException, MapperException, InvalidChallengeStatusException{
		
		Long version = (long) 1;
		return createNew(ChallengeTDG.getMaxId(), version, challengerId, 
				challengeeId, initialDec, status);
	}
	
	public static  Challenge createNew(Long id, Long version, Long challengerId,
			Long challengeeId, Long initialDec, int status) throws SQLException, MapperException, InvalidChallengeStatusException{
		
		Challenge u = new Challenge(id, version, challengerId, challengeeId, 
				initialDec, status);
		
		UoW.getCurrent().registerNew(u);
		return u;
	}
	
	public static Challenge createClean(Long id, Long version, Long challengerId, 
			Long challengeeId, Long initialDec, int status) throws InvalidChallengeStatusException {
		Challenge u = new Challenge(id, version, challengerId, challengeeId, initialDec, status);
		UoW.getCurrent().registerClean(u);
		return u;
	}
}
