package testsDev;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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

public class TestTDGs {
	Long id =  (long) 1;
	Long v =  (long) 2;
	
	@BeforeClass
	public static void setupDbConnection() {
		FrontController.prepareDBRegistry("");
	}
	@Test
	public void testInsertUser() {

		try {
			int res = PlayerTDG.insert(id, v, "Bob", "19i2j32ijedj89j8d9dj393jhdjdidjsijdsij");
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testInsertUser2() {

		try {
			int res = PlayerTDG.insert(id++, v, "Alice", "daewd2e2edj89j8d9dj393jhdjdidjsijdsij");
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertDeck() {

		try {
			int res = DeckTDG.insert(id, v, id);
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testInsertDeck2() {

		try {
			int res = DeckTDG.insert(id++, v, id++);
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testInsertCard() {

		try {
			int res = CardTDG.insert(id, v, id, "pikaChose", "p", "");
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertChallenge() {

		try {
			int res = ChallengeTDG.insert(id, v, id, id++,0, id);
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertGame() {

		try {
			int res = GameTDG.insert(id, v, id, id++, id, 0,
					"playing", "playing", id, id++);
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertHand() {

		try {
			int res = HandTDG.insert(id, v, id, id, id);
			if(res != 1) {
				assertTrue(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void ResetDB() {
		try {
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
