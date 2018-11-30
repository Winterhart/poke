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
import org.soen387.dom.Mapper.deck.CardInputMapper;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.game.Hand;
import org.soen387.dom.POJO.game.HandFactory;
import org.soen387.dom.POJO.game.HandProxy;
import org.soen387.dom.POJO.game.IHand;
import org.soen387.ser.Finder.HandFinder;

public class HandInputMapper implements IdentityBasedProducer {


	@IdentityBasedProducerMethod
	public static Hand find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Hand.class);
		}catch(DomainObjectNotFoundException ee) {
			System.out.println("Getting data for Hand " + id);
		}
		
		ResultSet rs = HandFinder.find(id);
		if(!rs.next()) {
			throw new MapperException(" Hand ..." + id+ " not found");
		}
		return getHand(rs);
	}
	
	public static List<IHand> findAll() throws SQLException, MapperException {
		List<IHand> diss = new ArrayList<IHand>();
		ResultSet rs = HandFinder.findAll();
		while(rs.next()) {
			try {
				
				diss.add(IdentityMap.get(rs.getLong("id"), Hand.class));
				
			}catch(DomainObjectNotFoundException ee) {
				System.out.println("Domain not found " + ee.getMessage());
				
			}
			
			diss.add(new HandProxy(rs.getLong("id")));
		}
		
		return diss;
		
	}
	
	public static IHand findByGameIdAndDeckId(Long deckId, Long gameId) throws SQLException, MapperException {
		IHand Hand = null;
		ResultSet rs = HandFinder.findAll();
		while(rs.next()) {
			if(rs.getLong("gameId") == gameId && rs.getLong("deckId") == deckId) {
				try {
					return IdentityMap.get(rs.getLong("id"), Hand.class);
					
				}catch(DomainObjectNotFoundException ee) {
					System.out.println("Domain not found " + ee.getMessage());	
				}
				
				return getHand(rs);
			
			}

		}
		
		return Hand;
	}
	
	private static Hand getHand(ResultSet rs) throws SQLException, MapperException {
		List<ICard> generatedList = new ArrayList<ICard>();
		generatedList = findCards(rs.getLong("deckId"), rs.getLong("gameId"));
		try {
			return HandFactory.createClean(
					rs.getLong("id"), 
					rs.getLong("version"), 
					generatedList, 
					rs.getLong("deckId"), 
					rs.getLong("gameId"));
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		
		return null;
	}
	
	private static List<ICard> findCards(Long deckId, Long gameId) throws SQLException, MapperException{
		List<ICard> cards = new ArrayList<ICard>();
		ResultSet rs = HandFinder.findAll();
		while(rs.next()) {
			if(rs.getLong("gameId") == gameId && rs.getLong("deckId") == deckId) {
				Long cardId = rs.getLong("cardId");
				cards.add(CardInputMapper.find(cardId));
			}
		}
		
		return cards;
	}

}
