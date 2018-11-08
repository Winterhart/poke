package org.soen387.app.DataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnector {
	private DatabaseConnector() {};
	private static String url = "";
	private static String user = "";
	private static String pwd = "";
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream("connection.properties"));
			url = pr.getProperty("databaseURL");
			user = pr.getProperty("user");
			pwd = pr.getProperty("pwd");
			
			
		}catch(Exception ee) {
			System.out.println("Can't get properties file " + ee.getMessage() );
		}
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
