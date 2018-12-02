package testsDev;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.junit.Test;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;
import org.soen387.dom.POJO.challenge.IChallenge;

public class TestDebugChallenge {

	@Test
	public void testStuff() {
		
		
		try {
			List<IChallenge> chas = ChallengeInputMapper.findAll();
			for(IChallenge c: chas) {
				System.out.println("IIIIIIIDDDDDDDD " + c.getId());
			}
		} catch (SQLException | MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
