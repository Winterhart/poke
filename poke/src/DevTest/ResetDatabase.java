package DevTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.soen387.app.DataSource.UserTDG;

class ResetDatabase {
	
	private boolean resetDbConfirm = true;
	@Test
	void InitializeDatabase() {
		
		if(resetDbConfirm) {
			try {
				//Add all our TDG 
				//TODO: Refactor idea: use Command pattern to reset all table
				UserTDG.deleteTable();
				UserTDG.createTable();
				System.out.println("Database Reset");
				
			}catch(Exception ee) {
				System.out.println("Problem while reset database " + ee.getMessage());
				fail("Error while trying to reset " + ee.getMessage());
			}	
		}else {
			fail("Set the value resetDbConfirm to true");
		}
		
		
	}

}
