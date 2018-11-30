package org.soen387.dom.Mapper.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.POJO.game.Bench;
import org.soen387.ser.TDG.BenchTDG;

public class BenchOutputMapper extends GenericOutputMapper<Long, Bench> {

	@Override
	public void insert(Bench d) throws MapperException {
			try {
				
				int result = BenchTDG.insert(
						d.getId(), 
						d.getVersion(), 
						d.getCardId(), 
						d.getDeckId(), 
						d.getGameId());
				
				if(result == 0) {
					System.out.println("Not able to add Bench " + d.getId());
					throw new MapperException("Not able to add Bench..." + d.getId());
				}
			}catch(SQLException ee) {
				ee.printStackTrace();
			}	
	}
	@Override
	public void update(Bench d) throws MapperException {

		try {
			int result = BenchTDG.update(
					d.getId(), 
					d.getVersion(), 
					d.getCardId(), 
					d.getDeckId(), 
					d.getGameId());
			
			if(result == 0) {
				System.out.println("Not able to update Bench " + d.getId());
				throw new LostUpdateException("Not able to update Bench " + d.getId());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Bench d) throws MapperException {
			try {
				int result = BenchTDG.delete(d.getId(), d.getVersion());
				if(result == 0) {
					System.out.println("Unable to delete Bench at" + d.getId());
					throw new LostUpdateException("Not able to update Bench");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
	}

}
