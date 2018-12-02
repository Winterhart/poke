package org.soen387.dom.Mapper.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.GameFactory;
import org.soen387.dom.POJO.game.GameProxy;
import org.soen387.dom.POJO.game.IGame;
import org.soen387.ser.Finder.GameFinder;

public class GameInputMapper implements IdentityBasedProducer {

	@IdentityBasedProducerMethod
	public static Game find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Game.class);
		}catch(DomainObjectNotFoundException ee) {
			System.out.println("Getting data for " + id + " " + ee.getMessage());
		}
		
		ResultSet rs = GameFinder.find(id);
		if(!rs.next()) {
			throw new MapperException(" Game... " + id + " not found ");
		}
		
		return getGame(rs);
	}
	
	public static List<IGame> findAll() throws SQLException, MapperException{
		List<IGame> games = new ArrayList<IGame>();
		ResultSet rs = GameFinder.findAll();
		
		while(rs.next()) {
			try {
				games.add(IdentityMap.get(rs.getLong("id"), Game.class));
				continue;
			}catch(DomainObjectNotFoundException ee) {
				System.out.println("Domain Exception " + ee.getMessage());
			}
			
			games.add(new GameProxy(rs.getLong("id")));
			
			
		}
		return games;
	}
	
	public static List<IGame> findAllForUser(Long userId) throws SQLException, MapperException{
		//TODO: Refactor later to make it more efficient (SELECT WHERE...)
		List<IGame> games = new ArrayList<IGame>();
		ResultSet rs = GameFinder.findAll();
		
		while(rs.next()) {
			if(rs.getLong("challengerId") == userId || rs.getLong("challengeeId") == userId) {
				try {
					games.add(IdentityMap.get(rs.getLong("id"), Game.class));
					continue;
				}catch(DomainObjectNotFoundException ee) {
					System.out.println("Domain Exception " + ee.getMessage());
				}
				
				games.add(new GameProxy(rs.getLong("id")));	
			}

		}
		return games;
	}

	private static Game getGame(ResultSet rs) throws SQLException {
		try {
			return GameFactory.createClean(
					rs.getLong("id"), 
					rs.getLong("version"), 
					rs.getLong("challengerId"), 
					rs.getLong("challengeeId"), 
					rs.getLong("challengerDeckId"), 
					rs.getLong("challengeeDeckId"));
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		
		return null;
	}

}
