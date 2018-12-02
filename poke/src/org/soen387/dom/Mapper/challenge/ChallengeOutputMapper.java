package org.soen387.dom.Mapper.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.challenge.Challenge;
import org.soen387.dom.POJO.challenge.InvalidChallengeStatusException;
import org.soen387.ser.TDG.ChallengeTDG;

public class ChallengeOutputMapper extends GenericOutputMapper<Long, Challenge> {

	@Override
	public void insert(Challenge d) throws MapperException {
		try {
			int result = ChallengeTDG.insert(
					d.getId(), 
					d.getVersion(), 
					d.getChallengerId(), 
					d.getChallengeeId(), 
					d.getStatus(),
					d.getDeckInitializer());
			
			if(result == 0) {
				System.out.println("Not able to add Challenge " + d.getId());
				throw new MapperException("Not able to add Challenge " + d.getId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Challenge d) throws MapperException {
		try {
			int result = ChallengeTDG.update(
					d.getId(), 
					d.getVersion(), 
					d.getChallengerId(), 
					d.getChallengeeId(), 
					d.getStatus(), 
					d.getDeckInitializer());
			
			if(result == 0) {
				System.out.println("Not able to update Challenge " + d.getId());
				throw new LostUpdateException("Not able to update Challenge " + d.getId());
			}
		}catch(SQLException  e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Challenge d) throws MapperException {
		try {
			int result = ChallengeTDG.delete(d.getId(), d.getVersion());
			if(result == 0) {
				System.out.println("Problem while deleting challenge" + d.getId());
				throw new LostUpdateException("Problem while deleting challenge" + d.getId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
