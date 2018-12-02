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
public class ChallengeTDG {
	private static final String tableName = "challenge";
	public static final String TABLE = DbRegistry.getTablePrefix()  + tableName;
	
	private final static String CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS " + TABLE + 
			" (id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
			"version int NOT NULL DEFAULT 0, " +
			"challengerId INTEGER NOT NULL, " +
			"challengeeId INTEGER NOT NULL, " +
			"challengeStatus INT NOT NULL, " +
			"deckInit BIGINT NOT NULL, " +
			"CONSTRAINT FK_challengerId FOREIGN KEY (challengerId) REFERENCES User(id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE, "
			+ "CONSTRAINT FK_challengeeId FOREIGN KEY (challengeeId) REFERENCES User(id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE, "
			+ "CONSTRAINT FK_challenge_deckId FOREIGN KEY (deckInit) REFERENCES deck(id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE )ENGINE=INNODB; ";
	
	private final static String DROP_TABLE = 
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	private final static String INSERT_QUERY =
			"INSERT INTO " + TABLE + " (id, version, challengerId, "
					+ "challengeeId, challengeStatus, deckInit)"
					+ " values(?,?,?,?,?,?) ;";
	
	private final static String DELETE_QUERY_ID = 
			" DELETE FROM " + TABLE + " WHERE id=? and version=? ;";
	
	private final static String UPDATE_QUERY_ID = 
			"UPDATE " + TABLE + " SET version=version+1, challengerId=?, "
					+ "challengeeId=?, challengeStatus=?, deckInit=? " +
					"WHERE id=? and version=?";
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP_TABLE);
	}
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE_TABLE);
	}
	
	public static int insert(Long id, Long version, Long challengerId,
			Long challengeeId, int challengeStatus, Long deckId) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, challengerId);
		ps.setLong(4, challengeeId);
		ps.setInt(5, challengeStatus);
		ps.setLong(6, deckId);
		int result = SQLLogger.processUpdate(ps);
		ps.close();
		return result;
	}
	
	public static int update(Long id, Long version, Long challengerId,
			Long challengeeId, int challengeStatus, Long deckId) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY_ID);
		ps.setLong(1, challengerId);
		ps.setLong(2, challengeeId);
		ps.setInt(3, challengeStatus);
		ps.setLong(4, deckId);
		ps.setLong(5, id);
		ps.setLong(6, version);
		
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
		long item =UniqueIdFactory.getMaxId(TABLE, "id");
		return item;
	}

}
