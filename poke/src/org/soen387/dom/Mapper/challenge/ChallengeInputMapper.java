package org.soen387.dom.Mapper.challenge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.soen387.dom.POJO.challenge.Challenge;
import org.soen387.dom.POJO.challenge.ChallengeFactory;
import org.soen387.dom.POJO.challenge.ChallengeProxy;
import org.soen387.dom.POJO.challenge.IChallenge;
import org.soen387.dom.POJO.challenge.InvalidChallengeStatusException;
import org.soen387.ser.Finder.ChallengeFinder;


public class ChallengeInputMapper implements IdentityBasedProducer {

	@IdentityBasedProducerMethod
	public static Challenge find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Challenge.class);
		}catch(DomainObjectNotFoundException ee) {
			System.out.println("Getting data for " + id + " " + ee.getMessage());
		}
		
		ResultSet rs = ChallengeFinder.find(id);
		if(!rs.next()) {
			throw new MapperException(" Challenge... " + id + " not found ");
		}
		
		return getChallenge(rs);
	}
	
	public static List<IChallenge> findAll() throws SQLException, MapperException {
		//TODO: Optimise this query l
		ArrayList<IChallenge> chas = new ArrayList<IChallenge>();
		ResultSet rs = ChallengeFinder.findAll();
		
		while(rs.next()) {
			try {
				Challenge a  = new Challenge(rs.getLong("id"), 
						rs.getLong("version"), 
						rs.getLong("challengerId"), 
						rs.getLong("challengeeId"), 
						rs.getLong("deckInit"),
						rs.getInt("challengeStatus"));
				chas.add(a);
			}catch(Exception ee) {
				System.out.println("Object not found" + ee.getMessage());
			}
		}
		return chas;
	}
	
//	public static List<IChallenge> findAllByUser(Long userId) throws SQLException, MapperException {
//		//TODO: Refactor to make it more efficient....
//		List<IChallenge> chas= new ArrayList<IChallenge>();
//		ResultSet rs = ChallengeFinder.findAll();
//		
//		while(rs.next()) {
//			if(rs.getLong("challengerId") == userId || rs.getLong("challengeeId") == userId) {
//				try {
//					chas.add(IdentityMap.get(rs.getLong("id"), Challenge.class));
//					continue;
//				}catch(DomainObjectNotFoundException ee) {
//					System.out.println("Object not found");
//				}
//				
//				chas.add(new ChallengeProxy(rs.getLong("id")));
//			}
//			
//		}
//		return chas;
//	}
	
	
	private static Challenge getChallenge(ResultSet rs) throws SQLException {
			return ChallengeFactory.createClean(
					rs.getLong("id"), 
					rs.getLong("version"), 
					rs.getLong("challengerId"), 
					rs.getLong("challengeeId"), 
					rs.getLong("deckInit"),
					rs.getInt("challengeStatus"));
	}

}
