package org.soen387.app.DataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnector {
	private DatabaseConnector() {};
	private static String url = "jdbc:mysql://localhost:3306/poke";
	private static String user = "root";
	private static String pwd = "coco751700";
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
