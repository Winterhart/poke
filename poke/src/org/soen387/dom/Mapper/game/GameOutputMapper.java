package org.soen387.dom.Mapper.game;
import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.game.Game;
import org.soen387.ser.TDG.GameTDG;

public class GameOutputMapper extends GenericOutputMapper<Long, Game> {

	@Override
	public void insert(Game d) throws MapperException {
		try {
			int result = GameTDG.insert(
					d.getId(),
					d.getVersion(),
					d.getChallengerId(),
					d.getChallengeeId(),
					d.getCurrentTurn(),
					d.getNumberOfTurn(),
					d.getChallengerStatus().toString(),
					d.getChallengeeStatus().toString(),
					d.getChallengerDeck(),
					d.getChallengeeDeck());
			if(result == 0) {
				System.out.println("Not able to add Game " + d.getId());
				throw new MapperException("Not able to add Game " + d.getId());
			}
			
		}catch(SQLException ee) {
			ee.printStackTrace();
		}
		
	}

	@Override
	public void update(Game d) throws MapperException {
		try {
			int result = GameTDG.update(
					d.getId(),
					d.getVersion(),
					d.getChallengerId(),
					d.getChallengeeId(),
					d.getCurrentTurn(),
					d.getNumberOfTurn(),
					d.getChallengerStatus().toString(),
					d.getChallengeeStatus().toString(),
					d.getChallengerDeck(),
					d.getChallengeeDeck());
			if(result == 0) {
				System.out.println("Not able to update Game " + d.getId());
				throw new LostUpdateException("Not able to update Game " + d.getId());
			}
			
		}catch(SQLException ee) {
			ee.printStackTrace();
		}
	}

	@Override
	public void delete(Game d) throws MapperException {
		try {
			int result =GameTDG.delete(d.getId(), d.getVersion());
			if(result == 0) {
				System.out.println("Problem while deleting game" + d.getId());
				throw new LostUpdateException("Problem while deleting game" + d.getId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
