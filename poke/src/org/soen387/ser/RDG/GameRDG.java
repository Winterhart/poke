package org.soen387.app.DataSource.RDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.soen387.app.DataSource.DatabaseConnector;

public class GameRDG {
	
	private Long id;
	private int version;
	private Long challengeId;
	private Long challengerId;
	private Long challengeeId;


	
	private static final String tableName = "games";
	private static long followingId = -1;

	

	public GameRDG(Long id, int version, Long challengeId, Long challengerId, Long challengeeId) {
		super();
		this.id = id;
		this.version = version;
		this.challengeId = challengeId;
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
	}

	public Long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(Long challengeId) {
		this.challengeId = challengeId;
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

	public Long getChallengerId() {
		return challengerId;
	}

	public void setChallengerId(Long challengerId) {
		this.challengerId = challengerId;
	}

	public Long getChallengeeId() {
		return challengeeId;
	}

	public void setChallengeeId(Long challengeeId) {
		this.challengeeId = challengeeId;
	}
	
	public static GameRDG find(long id) throws SQLException {
        GameRDG game = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long chaId = r.getLong("id");
			int v =r.getInt("version");
			Long challengeId = r.getLong("challengeId");
			Long challengerId = r.getLong("challengerId");
			Long challengeeId = r.getLong("challengeeId");
			
			game = new GameRDG(chaId, v, challengeId, challengerId, challengeeId);
		}
		conn.close();
        return game;
    }
    
    public static List<GameRDG> findAll() throws SQLException {
        List<GameRDG> gameList = new ArrayList<GameRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + ";");
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long gameId = r.getLong("id");
			int v =r.getInt("version");
			Long challengeId = r.getLong("challengeId");
			Long challengerId = r.getLong("challengerId");
			Long challengeeId = r.getLong("challengeeId");
			
			GameRDG g = new GameRDG(gameId, v, challengeId, challengerId, challengeeId);
			gameList.add(g);
		}
		
		conn.close();
        return gameList;
    }
    
	public int insert() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version, challengeId, challengerId, challengeeId) VALUES(?,?,?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setLong(3, this.getChallengeId());
		pState.setLong(4, this.getChallengerId());
		pState.setLong(5, this.getChallengeeId());
		int status = pState.executeUpdate();
		conn.close();

		return status;
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
