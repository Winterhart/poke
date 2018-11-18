package org.soen387.app.DataSource.RDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.soen387.app.DataSource.DatabaseConnector;

public class ChallengeRDG {
	private Long id;
	private int version;
	private Long challenger; //Owner
	private Long challengee;
	private int challengeStatus;
	private static final String tableName = "challenges";
	private static long followingId = -1;
	

	public ChallengeRDG(Long id, int version, Long challenger, Long challengee, int challengeStatus) {
		super();
		this.id = id;
		this.version = version;
		this.challenger = challenger;
		this.challengee = challengee;
		this.challengeStatus = challengeStatus;
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
	public Long getChallenger() {
		return challenger;
	}
	public void setChallenger(Long challenger) {
		this.challenger = challenger;
	}
	public Long getChallengee() {
		return challengee;
	}
	public void setChallengee(Long challengee) {
		this.challengee = challengee;
	}
	public int getChallengeStatus() {
		return challengeStatus;
	}
	public void setChallengeStatus(int challengeStatus) {
		this.challengeStatus = challengeStatus;
	}
	public static ChallengeRDG find(long id) throws SQLException {
        ChallengeRDG cha = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long chaId = r.getLong("id");
			int v =r.getInt("version");
			Long challengerId = r.getLong("challengerId");
			Long challengeeId = r.getLong("challengeeId");
			int status = r.getInt("challengeStatus");
			
			cha = new ChallengeRDG(chaId, v, challengerId, challengeeId, status);
		}
		conn.close();
        return cha;
    }
    public static List<ChallengeRDG> findByUserId(long userId) throws SQLException {
        List<ChallengeRDG> chaList = new ArrayList<ChallengeRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE challengerId = ?;");
		pState.setLong(1, userId);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long chaId = r.getLong("id");
			int v =r.getInt("version");
			Long challengerId = r.getLong("challengerId");
			Long challengeeId = r.getLong("challengeeId");
			int status = r.getInt("challengeStatus");
			
			ChallengeRDG c = new ChallengeRDG(chaId, v, challengerId, challengeeId, status);
			chaList.add(c);
		}
		conn.close();
        return chaList;
    }
    
    public static List<ChallengeRDG> findAll() throws SQLException {
        List<ChallengeRDG> chaList = new ArrayList<ChallengeRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + ";");
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long chaId = r.getLong("id");
			int v =r.getInt("version");
			Long challengerId = r.getLong("challengerId");
			Long challengeeId = r.getLong("challengeeId");
			int status = r.getInt("challengeStatus");
			
			ChallengeRDG c = new ChallengeRDG(chaId, v, challengerId, challengeeId, status);
			chaList.add(c);
		}
		
		conn.close();
        return chaList;
    }
    
	public int insert() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version, challengerId, challengeeId, challengeStatus) VALUES(?,?,?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setLong(3, this.getChallenger());
		pState.setLong(4, this.getChallengee());
		pState.setLong(5, this.getChallengeStatus());
		int status = pState.executeUpdate();
		conn.close();

		return status;
	}
	
	public int update() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("UPDATE " + tableName +
				" SET version = ?, challengeStatus = ?" +
				" WHERE id = ? ;");
		pState.setLong(1, (this.getVersion()+ 1));
		pState.setInt(2, this.challengeStatus);
		pState.setLong(3, this.getId());
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
