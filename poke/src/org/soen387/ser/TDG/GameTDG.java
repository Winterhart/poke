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
public class GameTDG {
	private static final String tableName = "game";
	public static final String TABLE = DbRegistry.getTablePrefix()  + tableName;
	
	private final static String CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS " + TABLE + 
			" (id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
			"version int NOT NULL DEFAULT 0, " +
			"challengerId INTEGER NOT NULL, " + 
			"challengeeId INTEGER NOT NULL, " +
			"currentTurn INTEGER NOT NULL, " +
			"numberOfTurn INT NOT NULL DEFAULT 0, " +
			"challengerStatus NVARCHAR(50) NOT NULL, " +
			"challengeeStatus NVARCHAR(50) NOT NULL, "+
			"challengerDeckId BIGINT NOT NULL, " +
			"challengeeDeckId BIGINT NOT NULL, " +
			"CONSTRAINT FK_game_challengerId "
			+ " FOREIGN KEY (challengerId) REFERENCES User(id) " +
			"ON DELETE CASCADE ON UPDATE CASCADE, " +
			"CONSTRAINT FK_game_challengeeId "
			+ "FOREIGN KEY (challengeeId) REFERENCES User(id) " +
			"ON DELETE CASCADE ON UPDATE CASCADE, " +
			"CONSTRAINT FK_currentTurn "
			+ "FOREIGN KEY (currentTurn) REFERENCES User(id) "+
			"ON DELETE CASCADE ON UPDATE CASCADE, " +
			"CONSTRAINT FK_game_challengerDeckId "
			+ "FOREIGN KEY (challengerDeckId) REFERENCES deck(id) " +
			"ON DELETE CASCADE ON UPDATE CASCADE, " +
			"CONSTRAINT FK_game_challengeeDeckId "
			+ "FOREIGN KEY (challengeeDeckId) REFERENCES deck(id) " + 
			"ON DELETE CASCADE ON UPDATE CASCADE)ENGINE=INNODB;";
			
	
	private final static String DROP_TABLE = 
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	private final static String INSERT_QUERY =
			"INSERT INTO " + TABLE + " (id, version, challengerId, challengeeId, "
					+ "currentTurn, numberOfTurn, challengerStatus, challengeeStatus, "
					+ " challengerDeckId, challengeeDeckId) "
					+ "values(?,?,?,?,?,?,?,?,?,?) ;";
	
	private final static String DELETE_QUERY_ID = 
			" DELETE FROM " + TABLE + " WHERE id=? and version=? ;";
	
	private final static String UPDATE_QUERY_ID = 
			"UPDATE " + TABLE + " SET version=version+1, challengerId=?,"
					+ " challengeeId=?, currentTurn=?, numberOfTUrn=?, challengerStatus=?,"
					+ "challengeeStatus=?, challengerDeckId=?, challengeeDeckId=? "
					+ "WHERE id=? and version=?";
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP_TABLE);
	}
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE_TABLE);
	}
	
	public static int insert(Long id, Long version, Long challengerId, Long challengeeId,
			Long currentTurn, int numberOfTurn, String challengerStatus,
			String challengeeStatus, Long challengerDeckId, 
			Long challengeeDeckId) throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setLong(3, challengerId);
		ps.setLong(4, challengeeId);
		ps.setLong(5, currentTurn);
		ps.setInt(6, numberOfTurn);
		ps.setString(7, challengerStatus);
		ps.setString(8, challengerStatus);
		ps.setLong(9, challengerDeckId);
		ps.setLong(10, challengeeDeckId);
		int result = SQLLogger.processUpdate(ps);
		ps.close();
		return result;
	}
	
	public static int update(Long id, Long version, Long challengerId, Long challengeeId,
			Long currentTurn, int numberOfTurn, String challengerStatus,
			String challengeeStatus, Long challengerDeckId, 
			Long challengeeDeckId)throws SQLException {
		Connection conn = DbRegistry.getDbConnection();
		PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY_ID);

		ps.setLong(1, challengerId);
		ps.setLong(2, challengeeId);
		ps.setLong(3, currentTurn);
		ps.setInt(4, numberOfTurn);
		ps.setString(5, challengerStatus);
		ps.setString(6, challengeeStatus);
		ps.setLong(7, challengerDeckId);
		ps.setLong(8, challengeeDeckId);
		ps.setLong(9, id);
		ps.setLong(10, version);
		
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
