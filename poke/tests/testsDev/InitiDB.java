package testsDev;

import java.sql.SQLException;

import org.junit.Test;
import org.soen387.app.DataSource.TDG.UserTDG;

public class InitiDB {
	
	@Test
	public void testInitDB() {
		
		try {
			UserTDG.createTable();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
