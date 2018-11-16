package org.soen387.app.Util;


import org.soen387.app.DataSource.TableInit.BenchTableInit;
import org.soen387.app.DataSource.TableInit.BoardTableInit;
import org.soen387.app.DataSource.TableInit.CardTableInit;
import org.soen387.app.DataSource.TableInit.ChallengeTableInit;
import org.soen387.app.DataSource.TableInit.DeckTableInit;
import org.soen387.app.DataSource.TableInit.GameTableInit;
import org.soen387.app.DataSource.TableInit.HandTableInit;
import org.soen387.app.DataSource.TableInit.UserTableInit;

public class RestartDatabase {

	public static void RestartDB() {
		
		try {
			UserTableInit.deleteTable();
			CardTableInit.deleteTable();
			DeckTableInit.deleteTable();
			ChallengeTableInit.deleteTable();
			HandTableInit.deleteTable();
			BenchTableInit.deleteTable();
			BoardTableInit.deleteTable();
			GameTableInit.deleteTable();
			
			GameTableInit.createTable();
			BoardTableInit.createTable();
			BenchTableInit.createTable();
			HandTableInit.createTable();
			ChallengeTableInit.createTable();
			DeckTableInit.createTable();
			CardTableInit.createTable();
			UserTableInit.createTable();
			
			System.out.println("Database Reset");
			
		}catch(Exception ee) {
			System.out.println("Problem while reset database " + ee.getMessage());
		}	
	}
	
}
