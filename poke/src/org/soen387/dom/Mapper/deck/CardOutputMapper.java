package org.soen387.dom.Mapper.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.deck.Card;
import org.soen387.ser.TDG.CardTDG;

public class CardOutputMapper extends GenericOutputMapper<Long, Card> {

	@Override
	public void insert(Card d) throws MapperException  {
		try {		
			int result = CardTDG.insert(
				d.getId(), 
				d.getVersion(), 
				d.getDeckId(), 
				d.getName(), 
				d.getCardType().toString(), 
				d.getBase());
			
			if(result == 0) {
				System.out.println("Not able to add Card with id: " + d.getId());
				throw new MapperException("Not able to add Card with id: " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Card d) throws MapperException {
		try {		
			int result = CardTDG.update(
				d.getId(), 
				d.getVersion(), 
				d.getDeckId(), 
				d.getName(), 
				d.getCardType().toString(), 
				d.getBase());
			
			if(result == 0) {
				System.out.println("Not able to update Card with id: " + d.getId());
				throw new LostUpdateException("Not able to update Card with id: " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Card d) throws MapperException {
		try {		
			int result = CardTDG.delete(d.getId(), d.getVersion());
			
			if(result == 0) {
				System.out.println("Not able to update Card with id: " + d.getId());
				throw new LostUpdateException("Not able to add Card with id: " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
