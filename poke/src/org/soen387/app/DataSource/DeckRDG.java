package org.soen387.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeckRDG {
	private Long id;
	private int version;
	private List<CardRDG> cards;
	private Long userId; //Owner
	private static final String tableName = "decks";
	private static long followingId = -1;
	public List<CardRDG> getCards() {
		return cards;
	}

	public void setCards(List<CardRDG> cards) {
		this.cards = cards;
	}
	
	public DeckRDG(Long id, int version, List<CardRDG> cards, Long ownerId){
		this.id = id;
		this.version = version;
		this.cards = cards;
		this.userId = ownerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
    
	//TODO: Update method

    public static DeckRDG find(long id) throws SQLException {
        DeckRDG deck = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long deckId = r.getLong("id");
			int v =r.getInt("version");
			Long own = r.getLong("userId");
			List<CardRDG> cardsList =  CardRDG.findAllDeck(deckId);
			deck = new DeckRDG(deckId, v, cardsList, own);
		}
		conn.close();
        return deck;
    }
    public static DeckRDG findByUserId(long Userid) throws SQLException {
        DeckRDG deck = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE userId = ?;");
		pState.setLong(1, Userid);
		ResultSet r = pState.executeQuery();
		
		while(r.next()) {
			Long deckId = r.getLong("id");
			int v =r.getInt("version");
			Long own = r.getLong("userId");
			List<CardRDG> cardsList =  CardRDG.findAllDeck(deckId);
			deck = new DeckRDG(deckId, v, cardsList, own);
		}
		conn.close();
        return deck;
    }
    
	public int insert() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version, userId) VALUES(?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setLong(3, this.getUserId());

		int status =  pState.executeUpdate();
		conn.close();
		
		return status;
	}
	
	public int delete() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("DELETE FROM " + tableName +
				" WHERE id= ? AND ;" );
		pState.setLong(1, this.getId());
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
