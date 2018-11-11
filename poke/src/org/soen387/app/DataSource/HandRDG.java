package org.soen387.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// It should be called HandCard because it's only one card in one hand in one game
public class HandRDG {

	private Long id;
	private int version;
	private Long userId;
	private Long gameId;
	private Long cardId;
	private static final String tableName = "hands";
	private static long followingId = -1;
	
	public HandRDG(Long id, int version, Long userId, Long gameId, Long cardId) {
		super();
		this.id = id;
		this.version = version;
		this.userId = userId;
		this.gameId = gameId;
		this.cardId = cardId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	// Insert New Card
	public int insert()  throws SQLException{
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version, userId, gameId, cardId) VALUES(?,?,?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setLong(3, this.getUserId());
		pState.setLong(4, this.getGameId());
		pState.setLong(5, this.getCardId());
		int status = pState.executeUpdate();
		conn.close();
		return status;
	}
	
	// Delete a by cardId, when you play to bench
	public int delete() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("DELETE FROM " + tableName +
				" WHERE id=? ;" );
		pState.setLong(1, this.getId());
		int status = pState.executeUpdate();
		conn.close();
		return status;
	}
	
	// Find all by gameId and UserId
	public static List<HandRDG> findAllByGameIdUserId(Long usId, Long gId) throws SQLException {
		List<HandRDG> hands = new ArrayList<HandRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE gameId = ? AND userId = ?;");
		pState.setLong(1, gId);
		pState.setLong(2, usId);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long handId = r.getLong("id");
			int v =r.getInt("version");
			Long useId = r.getLong("userId");
			Long gamId = r.getLong("gameId");
			Long cadId = r.getLong("cardId");
			
			HandRDG hand = new HandRDG(handId, v, useId, gamId, cadId);
			hands.add(hand);
		}
		
		conn.close();
		return hands;
	}
	
	// Normal Find...
	public static HandRDG find(Long id) throws SQLException {
		HandRDG hand = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE gameId = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long handId = r.getLong("id");
			int v =r.getInt("version");
			Long useId = r.getLong("userId");
			Long gamId = r.getLong("gameId");
			Long cadId = r.getLong("cardId");
			
			hand = new HandRDG(handId, v, useId, gamId, cadId);
		}
		
		conn.close();
		return hand;
	}
	
	public static long getFollowingId() throws SQLException {
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
	conn.close();
	return followingId;
	}
	
}
