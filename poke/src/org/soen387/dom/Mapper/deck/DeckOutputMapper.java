package org.soen387.dom.Mapper.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.soen387.dom.POJO.deck.Deck;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Deck d) throws MapperException {
		// TODO Auto-generated method stub
		
	}
	
}
