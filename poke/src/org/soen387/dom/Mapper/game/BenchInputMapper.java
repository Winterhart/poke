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
import org.soen387.dom.POJO.game.Bench;
import org.soen387.dom.POJO.game.BenchFactory;
import org.soen387.dom.POJO.game.BenchProxy;
import org.soen387.dom.POJO.game.IBench;
import org.soen387.ser.Finder.BenchFinder;

public class BenchInputMapper implements IdentityBasedProducer   {

	@IdentityBasedProducerMethod
	public static Bench find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Bench.class);
		}catch(DomainObjectNotFoundException ee) {
			System.out.println("Getting data for Bench " + id);
		}
		
		ResultSet rs = BenchFinder.find(id);
		if(!rs.next()) {
			throw new MapperException(" Bench ..." + id+ " not found");
		}
		return getBench(rs);
	}
	
	public static List<IBench> findAll() throws SQLException, MapperException {
		List<IBench> diss = new ArrayList<IBench>();
		ResultSet rs = BenchFinder.findAll();
		while(rs.next()) {
			try {
				
				diss.add(IdentityMap.get(rs.getLong("id"), Bench.class));
				continue;
				
			}catch(DomainObjectNotFoundException ee) {
				System.out.println("Domain not found " + ee.getMessage());
				
			}
			
			diss.add(new BenchProxy(rs.getLong("id")));
		}
		
		return diss;
		
	}
	
	public static List<IBench> findByGameIdAndDeckId(Long deckId, Long gameId) throws SQLException, MapperException {
		List<IBench> diss = new ArrayList<IBench>();
		ResultSet rs = BenchFinder.findAll();
		while(rs.next()) {
			if(rs.getLong("gameId") == gameId && rs.getLong("deckId") == deckId) {
				try {
					
					diss.add(IdentityMap.get(rs.getLong("id"), Bench.class));
					continue;
				}catch(DomainObjectNotFoundException ee) {
					System.out.println("Domain not found " + ee.getMessage());
					
				}
				
				diss.add(new BenchProxy(rs.getLong("id")));
			}

		}
		
		return diss;
	}
	
	private static Bench getBench(ResultSet rs) throws SQLException, MapperException {
		try {
			return BenchFactory.createClean(
					rs.getLong("id"), 
					rs.getLong("version"), 
					rs.getLong("cardId"),
					rs.getLong("deckId"), 
					rs.getLong("gameId"));
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		
		return null;
	}

}
