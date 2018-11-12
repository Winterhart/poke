package DevTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.soen387.app.DataSource.TableInit.BenchTableInit;
import org.soen387.app.DataSource.TableInit.BoardTableInit;
import org.soen387.app.DataSource.TableInit.CardTableInit;
import org.soen387.app.DataSource.TableInit.ChallengeTableInit;
import org.soen387.app.DataSource.TableInit.DeckTableInit;
import org.soen387.app.DataSource.TableInit.GameTableInit;
import org.soen387.app.DataSource.TableInit.HandTableInit;
import org.soen387.app.DataSource.TableInit.UserTableInit;

class InitializeDatabase {

	@Test
	void testInitDB() {
		try {
			GameTableInit.createTable();
			BoardTableInit.createTable();
			BenchTableInit.createTable();
			HandTableInit.createTable();
			ChallengeTableInit.createTable();
			DeckTableInit.createTable();
			CardTableInit.createTable();
			UserTableInit.createTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
