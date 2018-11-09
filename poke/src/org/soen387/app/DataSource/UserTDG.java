package org.soen387.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class UserTDG {
	
	private static final String tableName = "users";
	private static long followingId = -1;
	public static void createTable() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE IF NOT EXISTS " + tableName  +  
				" (id BIGINT NOT NULL UNIQUE, "
				+ "version int NOT NULL, "
				+ "username NVARCHAR(200) NOT NULL UNIQUE, "
				+ "password NVARCHAR(500) NOT NULL);");
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
	
	public static ResultSet findByUsername(String username) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE username = ?;");
		pState.setString(1, username);
		return pState.executeQuery();
	}
	
	// Login function
	public static ResultSet findUserWithCredential(String username, String password) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE username = ? AND password = ?;");
		pState.setString(1, username);
		pState.setString(2, password);
		return pState.executeQuery();
	}
	
	public static ResultSet find(long id) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		return pState.executeQuery();
	}
	
	public static long getFollowingId() throws SQLException {

		if(followingId == -1) {
			Connection conn = DatabaseConnector.getConnection();
			String query = "SELECT max(id) as id from " + tableName + ";";
			Statement pState = conn.createStatement();
			ResultSet result = pState.executeQuery(query);
			
			while(result.next()) {
				followingId = result.getLong("id");
				followingId++;
				return followingId;
				//Prevent iterating multiple times...
			}
			
		}
		
		return followingId;
	}
	
	public static int insert(long id, int version, String username, String pwd) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version,username, password) VALUES(?,?,?,?);" );
		pState.setLong(1, id);
		pState.setInt(2, version);
		pState.setString(3, username);
		pState.setString(4, pwd);
		
		return pState.executeUpdate();
	}

}
