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
import org.soen387.dom.POJO.deck.Deck;
import org.soen387.dom.POJO.deck.DeckFactory;
import org.soen387.dom.POJO.deck.DeckProxy;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.deck.IDeck;
import org.soen387.ser.Finder.CardFinder;
import org.soen387.ser.Finder.DeckFinder;

public class DeckInputMapper implements IdentityBasedProducer {

	@IdentityBasedProducerMethod
	public static Deck find(Long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Deck.class);
		}catch(DomainObjectNotFoundException e) {
			System.out.println("Getting data from database for card: " + id);
		}
		
		ResultSet rs = CardFinder.find(id);
		if(!rs.next()) {
			throw new MapperException("Card " + id+ " not found...");
		}
		return getDeck(rs);
	}

	public static List<IDeck> findAllForUserId(Long userId) throws SQLException , MapperException{
		List<IDeck> decks = new ArrayList<IDeck>();
		ResultSet rs = DeckFinder.findAll();
		//TODO: Optimize later on...
		while(rs.next()) {
			if(rs.getLong("ownerId") == userId) {
				try {
					decks.add(IdentityMap.get(rs.getLong("id"), Deck.class));
				}catch(DomainObjectNotFoundException e) {
					System.out.println("Not found dom obj for obj..." + e.getMessage());
				}
				decks.add(new DeckProxy(rs.getLong("id")));
			}
		}
		return decks;
		
	}
	
	private static Deck getDeck(ResultSet rs) throws SQLException, MapperException {
		List<ICard> cardFromDeck = new ArrayList<ICard>();
		try {	
			cardFromDeck = CardInputMapper.findAllByDeckId(rs.getLong("id"));
		}catch(Exception e) {
			System.out.println("Can't load card for deck..." + e.getMessage());
		}

		return DeckFactory.createClean(
				rs.getLong("id"),
				rs.getLong("version"),
				cardFromDeck);
	}

}
