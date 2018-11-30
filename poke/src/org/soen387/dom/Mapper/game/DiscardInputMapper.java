package org.soen387.dom.Mapper.game;

import org.soen387.dom.Mapper.deck.CardInputMapper;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.game.Discard;
import org.soen387.dom.POJO.game.DiscardFactory;
import org.soen387.dom.POJO.game.DiscardProxy;
import org.soen387.dom.POJO.game.IDiscard;
import org.soen387.ser.Finder.DiscardFinder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;

public class DiscardInputMapper implements IdentityBasedProducer {

	@IdentityBasedProducerMethod
	public static Discard find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Discard.class);
		}catch(DomainObjectNotFoundException ee) {
			System.out.println("Getting data for Discard " + id);
		}
		
		ResultSet rs = DiscardFinder.find(id);
		if(!rs.next()) {
			throw new MapperException(" Discard ..." + id+ " not found");
		}
		return getDiscard(rs);
	}
	
	public static List<IDiscard> findAll() throws SQLException, MapperException {
		List<IDiscard> diss = new ArrayList<IDiscard>();
		ResultSet rs = DiscardFinder.findAll();
		while(rs.next()) {
			try {
				
				diss.add(IdentityMap.get(rs.getLong("id"), Discard.class));
				
			}catch(DomainObjectNotFoundException ee) {
				System.out.println("Domain not found " + ee.getMessage());
				
			}
			
			diss.add(new DiscardProxy(rs.getLong("id")));
		}
		
		return diss;
		
	}
	
	public static IDiscard findByGameIdAndDeckId(Long deckId, Long gameId) throws SQLException, MapperException {
		IDiscard discard = null;
		ResultSet rs = DiscardFinder.findAll();
		while(rs.next()) {
			if(rs.getLong("gameId") == gameId && rs.getLong("deckId") == deckId) {
				try {
					return IdentityMap.get(rs.getLong("id"), Discard.class);
					
				}catch(DomainObjectNotFoundException ee) {
					System.out.println("Domain not found " + ee.getMessage());	
				}
				
				return getDiscard(rs);
			
			}

		}
		
		return discard;
	}
	
	private static Discard getDiscard(ResultSet rs) throws SQLException, MapperException {
		List<ICard> generatedList = new ArrayList<ICard>();
		generatedList = findCards(rs.getLong("deckId"), rs.getLong("gameId"));
		try {
			return DiscardFactory.createClean(
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
		ResultSet rs = DiscardFinder.findAll();
		while(rs.next()) {
			if(rs.getLong("gameId") == gameId && rs.getLong("deckId") == deckId) {
				Long cardId = rs.getLong("cardId");
				cards.add(CardInputMapper.find(cardId));
			}
		}
		
		return cards;
	}

}
