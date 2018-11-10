package org.soen387.app.DataSource.TableInit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import org.soen387.app.DataSource.DatabaseConnector;

public class DeckTableInit {
	private static final String tableName = "decks";
	public static void createTable() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE IF NOT EXISTS " + tableName  +  
				" (id BIGINT NOT NULL UNIQUE, "
				+ "version int NOT NULL, "
				+ "userId int NOT NULL UNIQUE);");
	}
	
	public static void deleteTable() throws SQLException{
		Connection conn = DatabaseConnector.getConnection();
		Statement state = conn.createStatement();
		try {
			// if table doesn't exists...
			state.execute("TRUNCATE TABLE " + tableName + ";");
		}catch(SQLSyntaxErrorException ee) {
			System.out.println("Oups... table " + tableName + " doesn't exist");
		}

	}
}