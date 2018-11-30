package org.soen387.dom.Mapper.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.game.Hand;
import org.soen387.ser.TDG.HandTDG;

public class HandOutputMapper extends GenericOutputMapper<Long, Hand> {

	@Override
	public void insert(Hand d) throws MapperException {
			try {
				
				int result = HandTDG.insert(
						d.getId(), 
						d.getVersion(), 
						d.getCardId(), 
						d.getDeckId(), 
						d.getGameId());
				
				if(result == 0) {
					System.out.println("Not able to add Hand " + d.getId());
					throw new MapperException("Not able to add Hand..." + d.getId());
				}
			}catch(SQLException ee) {
				ee.printStackTrace();
			}	
	}


	@Override
	public void update(Hand d) throws MapperException {

		try {
			int result = HandTDG.update(
					d.getId(), 
					d.getVersion(), 
					d.getCardId(), 
					d.getDeckId(), 
					d.getGameId());
			
			if(result == 0) {
				System.out.println("Not able to update Hand " + d.getId());
				throw new LostUpdateException("Not able to update Hand " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Hand d) throws MapperException {
			try {
				int result = HandTDG.delete(d.getId(), d.getVersion());
				if(result == 0) {
					System.out.println("Unable to delete Hand at" + d.getId());
					throw new LostUpdateException("Not able to update Hand");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
	}

}
