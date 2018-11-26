package testsDev;

import java.sql.SQLException;

import org.junit.Test;
import org.soen387.app.DataSource.TDG.BenchTDG;
import org.soen387.app.DataSource.TDG.CardTDG;
import org.soen387.app.DataSource.TDG.ChallengeTDG;
import org.soen387.app.DataSource.TDG.DeckTDG;
import org.soen387.app.DataSource.TDG.DiscardTDG;
import org.soen387.app.DataSource.TDG.GameTDG;
import org.soen387.app.DataSource.TDG.HandTDG;
import org.soen387.app.DataSource.TDG.PlayerTDG;
import org.soen387.app.Presentation.FrontController;

public class InitiDB {
	
	@Test
	public void testInitDB() {
		
		try {
			FrontController.prepareDBRegistry("");

			HandTDG.dropTable();
			BenchTDG.dropTable();
			DiscardTDG.dropTable();
			CardTDG.dropTable();
			GameTDG.dropTable();
			ChallengeTDG.dropTable();
			DeckTDG.dropTable();
			PlayerTDG.dropTable();

			
			PlayerTDG.createTable();
			DeckTDG.createTable();
			ChallengeTDG.createTable();
			GameTDG.createTable();
			CardTDG.createTable();
			DiscardTDG.createTable();
			BenchTDG.createTable();
			HandTDG.createTable();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
