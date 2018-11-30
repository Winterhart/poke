package org.soen387.dom.POJO.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.ser.TDG.CardTDG;

public class CardFactory {
	
	public static Card createNew(String name, CardType type, Long deckId, String base) throws SQLException, MapperException {
		Long version = (long)1;
		return createNew(CardTDG.getMaxId(), version, name, type, deckId, base);
	}
	
	public static Card createNew(Long id, Long version, String name, CardType type, Long deckId, String base) throws SQLException, MapperException {
		Card c = new Card(id, version, deckId, name, type, base);
		UoW.getCurrent().registerNew(c);
		return c;
	}
	
	public static Card createClean(Long id, Long version, Long deckId, String name, CardType type, String base) throws SQLException, MapperException {
		Card c = new Card(id, version, deckId, name, type, base);
		UoW.getCurrent().registerClean(c);
		return c;
	}
}
