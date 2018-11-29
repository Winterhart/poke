package testsDev;

import java.sql.SQLException;

import org.junit.Test;
import org.soen387.ser.TDG.BenchTDG;
import org.soen387.ser.TDG.CardTDG;
import org.soen387.ser.TDG.ChallengeTDG;
import org.soen387.ser.TDG.DeckTDG;
import org.soen387.ser.TDG.DiscardTDG;
import org.soen387.ser.TDG.GameTDG;
import org.soen387.ser.TDG.HandTDG;
import org.soen387.app.PokeFC;;

public class InitiDB {
	
	@Test
	public void testInitDB() {
		
		try {
			PokeFC.prepareDbRegistry("");

			HandTDG.dropTable();
			BenchTDG.dropTable();
			DiscardTDG.dropTable();
			CardTDG.dropTable();
			GameTDG.dropTable();
			ChallengeTDG.dropTable();
			DeckTDG.dropTable();

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
