package org.soen387.app.DataSource;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	private DatabaseConnector() {};
//	private static String url = "\"jdbc:mysql://\" + url;
	private static String user = "m_hardy_inc";
	private static String pwd = "itypurta";
	private static String url = "jdbc:mysql://localhost:1212/m_hardy_inc";
//	private static String user = "root";
//	private static String pwd = "coco751700";	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:1212/m_hardy_inc"
					+ "?user=m_hardy_inc"
					+ "&password=itypurta"
					+ "&characterEncoding=UTF-8"
					+ "&useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC"
					+ "&autoReconnect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
