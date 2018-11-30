package org.soen387.dom.Mapper.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.game.Discard;
import org.soen387.ser.TDG.DiscardTDG;

public class DiscardOutputMapper extends GenericOutputMapper<Long, Discard> {
	

	@Override
	public void insert(Discard d) throws MapperException {
			try {
				
				int result = DiscardTDG.insert(
						d.getId(), 
						d.getVersion(), 
						d.getCardId(), 
						d.getDeckId(), 
						d.getGameId());
				
				if(result == 0) {
					System.out.println("Not able to add Discard " + d.getId());
					throw new MapperException("Not able to add Discard..." + d.getId());
				}
			}catch(SQLException ee) {
				ee.printStackTrace();
			}	
	}


	@Override
	public void update(Discard d) throws MapperException {

		try {
			int result = DiscardTDG.update(
					d.getId(), 
					d.getVersion(), 
					d.getCardId(), 
					d.getDeckId(), 
					d.getGameId());
			
			if(result == 0) {
				System.out.println("Not able to update Discard " + d.getId());
				throw new LostUpdateException("Not able to update Discard " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Discard d) throws MapperException {
			try {
				int result = DiscardTDG.delete(d.getId(), d.getVersion());
				if(result == 0) {
					System.out.println("Unable to delete Discard at" + d.getId());
					throw new LostUpdateException("Not able to update Discard");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
	}

}
