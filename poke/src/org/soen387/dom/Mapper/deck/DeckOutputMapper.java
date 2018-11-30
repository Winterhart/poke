package org.soen387.dom.Mapper.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.deck.Deck;
import org.soen387.ser.TDG.CardTDG;
import org.soen387.ser.TDG.DeckTDG;

public class DeckOutputMapper extends GenericOutputMapper<Long, Deck> {

	@Override
	public void insert(Deck d) throws MapperException {
		try {
			int result = DeckTDG.insert(
					d.getId(),
					d.getVersion(),
					d.getOwnerId());
			
			if(result == 0) {
				System.out.println("Not able to add Deck...." + d.getId());
				throw new MapperException("Not able to add Deck with id: " + d.getId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Deck d) throws MapperException {
		try {
			int result = DeckTDG.update(
					d.getId(),
					d.getVersion(),
					d.getOwnerId());
			
			if(result == 0) {
				System.out.println("Not able to update deck...." + d.getId());
				throw new LostUpdateException("Not able to update Deck with id: " + d.getId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Deck d) throws MapperException {
		try {		
			int result = DeckTDG.delete(d.getId(), d.getVersion());
			
			if(result == 0) {
				System.out.println("Not able to delete deck with id: " + d.getId());
				throw new LostUpdateException("Not able to delete Card with id: " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
