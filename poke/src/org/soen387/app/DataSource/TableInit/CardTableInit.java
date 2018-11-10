package org.soen387.app.DataSource.TableInit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import org.soen387.app.DataSource.DatabaseConnector;

public class CardTableInit {
	private static final String tableName = "cards";
	public static void createTable() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE IF NOT EXISTS " + tableName  +  
				" (id BIGINT NOT NULL UNIQUE, "
				+ "version int NOT NULL, "
				+ "deckId BIGINT NOT NULL, "
				+ "cardName NVARCHAR(200) NOT NULL,"
				+ "cardType NVARCHAR(200) NOT NULL);");
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
