package org.soen387.app.DataSource.TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import org.dsrg.

import org.soen387.app.DataSource.DatabaseConnector;

public class UserTDG {
	
	
	private static final String tableName = "users";
	
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
		ResultSet r = pState.executeQuery();
		conn.close();
		return r;
	}
	
	// Login function
	public static ResultSet findUserWithCredential(String username, String password) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE username = ? AND password = ?;");
		pState.setString(1, username);
		pState.setString(2, password);
		ResultSet r = pState.executeQuery();
		conn.close();
		return r;
	}
	
	public static ResultSet find(long id) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		conn.close();
		return r;
	}
	
	public static long getFollowingId() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		String query = "SELECT max(id) as id from " + tableName + ";";
		Statement pState = conn.createStatement();
		ResultSet result = pState.executeQuery(query);
		conn.close();
		return result.getLong("id");
	}
	
	public static int insert(Long id, int version, String username, String pass) throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version,username, password) VALUES(?,?,?,?);" );
		pState.setLong(1, id);
		pState.setInt(2, version);
		pState.setString(3, username);
		pState.setString(4, pass);
		int status =pState.executeUpdate();
		conn.close();
		return status;
	}
	
	public static ResultSet findAll() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " ;");
		ResultSet r = pState.executeQuery();
		conn.close();
		return r;
	}
}
