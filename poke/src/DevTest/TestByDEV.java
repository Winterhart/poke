package DevTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.soen387.app.DataSource.DatabaseConnector;

class TestByDEV {

	@Test
	void testReadAllTables() {
		Connection aCon;
		int success = 0;
		aCon = DatabaseConnector.getConnection();
		ResultSet results = null;
		try {
			Statement statement = aCon.createStatement();
			results = statement.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
			System.out.println(results.toString());

			while(results.next()) {
				System.out.println(results.getString("TABLE_NAME"));
				success++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success < 3) {
			fail("Not yet implemented");
		}

		
	}

}
