package org.soen387.ser.TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
/**
 * Inspired by Stuart Thiel's Thesis : 
 * 'Enterprise Application Design Patterns: Improved and Applied'
 */
public class DeckTDG {
	private static final String tableName = "deck";
	public static final String TABLE = DbRegistry.getTablePrefix()  + tableName;
	
	private final static String CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS " + TABLE + 
			"(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
			"version int NOT NULL DEFAULT 0, " +
			"ownerId INTEGER NOT NULL, " +
			"CONSTRAINT FK_OwnerId FOREIGN KEY (ownerId) REFERENCES User(id) "
			+ "ON UPDATE CASCADE "
			+ "ON DELETE CASCADE )ENGINE=INNODB; ";
	
	private final static String DROP_TABLE = 
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	private final static String INSERT_QUERY =
			"INSERT INTO " + TABLE + " (id, version, ownerId) values(?,?,?) ;";
	
	private final static String DELETE_QUERY_ID = 
			" DELETE FROM " + TABLE + " WHERE id=? and version=? ;";
	
	private final static String UPDATE_QUERY_ID = 
			"UPDATE " + TABLE + " SET version=version+1, owerId=? " +
					"WHERE id=? and version=?";
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP_TABLE);
	}
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE_TABLE);
	}
	
	public static int insert(Long id, Long version, Long ownerId) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, ownerId);
		int result = SQLLogger.processUpdate(ps);
		ps.close();
		return result;
	}
	
	public static int update(Long id, Long version, Long ownerId) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY_ID);
		ps.setLong(1, ownerId);
		ps.setLong(2, id);
		ps.setLong(3, version);
		
		int result = SQLLogger.processUpdate(ps);
		ps.close();
		return result;
		
	}
	
	public static int delete(Long id, Long version) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(DELETE_QUERY_ID);
		ps.setLong(1, id);
		ps.setLong(2, version);
		int result = SQLLogger.processUpdate(ps);
		ps.close();
		return result;
	}
	
	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(TABLE, "id");
	}

}
