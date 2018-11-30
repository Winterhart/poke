package org.soen387.dom.Mapper.deck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.soen387.dom.POJO.deck.Card;
import org.soen387.dom.POJO.deck.CardFactory;
import org.soen387.dom.POJO.deck.CardProxy;
import org.soen387.dom.POJO.deck.CardType;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.ser.Finder.CardFinder;

public class CardInputMapper implements IdentityBasedProducer {

	@IdentityBasedProducerMethod
	public static Card find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Card.class);
		}catch(DomainObjectNotFoundException e) {
			System.out.println("Getting data from database for card: " + id);
		}
		
		ResultSet rs = CardFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Card " + id+ " not found...");
		}
		return getCard(rs);
	}


	public static List<ICard> findAll() throws SQLException, MapperException {
		List<ICard> cards = new ArrayList<ICard>();
		ResultSet rs = CardFinder.findAll();
		while(rs.next()) {
			try {
				cards.add(IdentityMap.get(rs.getLong("id"), Card.class));
				continue;
				
			}catch(DomainObjectNotFoundException e) {
				System.out.println("Not found Domain obj error" + e.getMessage());
			}cards.add(new CardProxy(rs.getLong("id")));
		}
		
		return cards;
	}
	
	public static List<ICard> findAllByDeckId(Long deckId) throws SQLException, MapperException {
		List<ICard> cards = new ArrayList<ICard>();
		ResultSet rs = CardFinder.findAll();
		while(rs.next()) {
			if(rs.getLong("deckId") == deckId) {
				try {
					//TODO: later optimize to add a find by deckId on SQL level...
	
						cards.add(IdentityMap.get(rs.getLong("id"), Card.class));
						continue;
					
				}catch(DomainObjectNotFoundException e) {
					System.out.println("Not found Domain obj error" + e.getMessage());
				}
				
				cards.add(new CardProxy(rs.getLong("id")));
			}
		}
		
		return cards;
	}
	
	
	private static Card getCard(ResultSet rs) throws SQLException {
		CardType t = Card.convertToType(rs.getString("cardType"));
		try {
			return CardFactory.createClean(
					rs.getLong("id"),
					rs.getLong("version"),
					rs.getLong("deckId"),
					rs.getString("cardName"),
					t,
					rs.getString("cardBase"));
		} catch (MapperException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	

}
