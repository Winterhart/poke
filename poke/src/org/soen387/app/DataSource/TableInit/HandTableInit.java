package org.soen387.app.DataSource.TableInit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import org.soen387.app.DataSource.DatabaseConnector;

public class HandTableInit {
	private static final String tableName = "hands";
	public static void createTable() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE IF NOT EXISTS " + tableName  +  
				" (id BIGINT NOT NULL UNIQUE, "
				+ "version int NOT NULL, "
				+ "userId BIGINT NOT NULL, "
				+ "gameId BIGINT NOT NULL,"
				+ "cardId BIGINT NOT NULL);");
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
