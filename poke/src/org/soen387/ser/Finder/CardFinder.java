package org.soen387.app.DataSource.Finder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.app.DataSource.TDG.CardTDG;

/**
 * Inspired by Stuart Thiel's Thesis : 
 * 'Enterprise Application Design Patterns: Improved and Applied'
 */
public class CardFinder {
	private static String SELECT_BY_ID_SQL = 
			"SELECT * FROM " + CardTDG.TABLE + " AS u WHERE u.id=? ;";
	private static String SELECT_ALL_SQL = 
			"SELECT * FROM " + CardTDG.TABLE + " ;";
	
	public static ResultSet find(Long id) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
		
	}
	
	public static ResultSet findAll() throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
		return SQLLogger.processQuery(ps);
	}

}
