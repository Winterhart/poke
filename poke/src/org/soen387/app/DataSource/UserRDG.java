package org.soen387.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRDG {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	private long id;
	private int version;
	private String username;
	private String password;
	private static final String tableName = "users";
	private static long followingId = -1;
	
	public UserRDG(long id, int version, String username, String passwrd) {
		this.id = id;
		this.version = version;
		this.username = username;
		this.password = passwrd;
	}
	
	


	
	public static UserRDG findByUsername(String username) throws SQLException {
		UserRDG user = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE username = ?;");
		pState.setString(1, username);
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			user = new UserRDG(
					r.getLong("id"), 
					r.getInt("version"), 
					r.getString("username"),
					r.getString("password"));
		}
		return user;
	}
	
	// Login function
	public static UserRDG findUserWithCredential(String username, String password) throws SQLException {
		UserRDG user = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE username = ? AND password = ?;");
		pState.setString(1, username);
		pState.setString(2, password);
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			user = new UserRDG(
					r.getLong("id"), 
					r.getInt("version"), 
					r.getString("username"),
					r.getString("password"));
		}
		return user;
	}
	
	public static UserRDG find(long id) throws SQLException {
		UserRDG user = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			user = new UserRDG(
					r.getLong("id"), 
					r.getInt("version"), 
					r.getString("username"),
					r.getString("password"));
		}
		return user;
	}
	
	public static long getFollowingId() throws SQLException {

		//if(followingId == -1) {
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
			
		//}
		
		return followingId;
	}
	
	public int insert() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version,username, password) VALUES(?,?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setString(3, this.getUsername());
		pState.setString(4, this.getPassword());
		
		return pState.executeUpdate();
	}
	
	public static List<UserRDG> findAll() throws SQLException {
		List<UserRDG> users = new ArrayList<UserRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " ;");
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			UserRDG user = new UserRDG(
					r.getLong("id"), 
					r.getInt("version"),
					r.getString("username"),
					r.getString("password"));
		}
		
		return users;
	}

}
