package org.soen387.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardRDG {
	
	private Long id;
	private int version;
	private Long gameId;
	private String challengerStatus;
	private String challengeeStatus;

	
	private static final String tableName = "boards";
	private static long followingId = -1;
	
	
	public BoardRDG(Long id, int version, Long gameId, String challengerStatus, String challengeeStatus) {
		super();
		this.id = id;
		this.version = version;
		this.gameId = gameId;
		this.challengerStatus = challengerStatus;
		this.challengeeStatus = challengeeStatus;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChallengeeStatus() {
		return challengeeStatus;
	}
	public void setChallengeeStatus(String challengeeStatus) {
		this.challengeeStatus = challengeeStatus;
	}
	public String getChallengerStatus() {
		return challengerStatus;
	}
	public void setChallengerStatus(String challengerStatus) {
		this.challengerStatus = challengerStatus;
	}
	
	
	//Insert
	public int insert()  throws SQLException{
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version, gameId, challengerStatus, challengeeStatus) VALUES(?,?,?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setLong(3, this.getGameId());
		pState.setString(4, this.getChallengerStatus());
		pState.setString(5, this.getChallengeeStatus());
		int status = pState.executeUpdate();
		conn.close();

		return status;
	}
	//Update
	public int update() throws SQLException{
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("UPDATE " + tableName +
				" SET version = ?, challengerStatus = ?, challengeeStatus = ?" +
				" WHERE gameId = ? ;");
		pState.setLong(1, (this.getVersion()+ 1));
		pState.setString(2, this.getChallengerStatus());
		pState.setString(3, this.getChallengeeStatus());
		pState.setLong(4, this.getGameId());
		int status = pState.executeUpdate();
		conn.close();
		return status;
	}
	
	
	//Find By Game Id
	public static BoardRDG findByGameId(Long gamId) throws SQLException {
		BoardRDG board = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE gameId = ?;");
		pState.setLong(1, gamId);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long boardId = r.getLong("id");
			int v =r.getInt("version");
			Long gmId = r.getLong("gameId");
			String challengerSta = r.getString("challengerStatus");
			String challengeeSta = r.getString("challengeeStatus");
			
			board = new BoardRDG(boardId, v, gmId, challengerSta, challengeeSta);
		}
		
		conn.close();
		return board;
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
