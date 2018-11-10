package DevTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.soen387.app.DataSource.TableInit.CardTableInit;
import org.soen387.app.DataSource.TableInit.DeckTableInit;
import org.soen387.app.DataSource.TableInit.UserTableInit;

class ResetDatabase {
	
	private boolean resetDbConfirm = true;
	@Test
	void InitializeDatabase() {
		
		if(resetDbConfirm) {
			try {
				//Add all our TDG 
				//TODO: Refactor idea: use Command pattern to reset all table
				UserTableInit.deleteTable();
				CardTableInit.deleteTable();
				DeckTableInit.deleteTable();
				
				DeckTableInit.createTable();
				CardTableInit.createTable();
				UserTableInit.createTable();
				
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
