package org.soen387.dom.POJO.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.ser.TDG.CardTDG;

public class CardFactory {
	public static Card createNew(String name, CardType type) throws SQLException, MapperException {
		Card c = new Card(CardTDG.getMaxId(), 1, name, type, "");
		UoW.getCurrent().registerNew(c);
		return c;
	}
	
	public static Card createClean(Long id, Long version, String name, CardType type) throws SQLException, MapperException {
		Card c = new Card(id, version, name, type, "");
		UoW.getCurrent().registerClean(c);
		return c;
	}
}
