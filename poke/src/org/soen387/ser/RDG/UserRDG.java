package org.soen387.app.DataSource.RDG;

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
	
	


	


}
