package org.soen387.app.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardRDG {
	private Long id;
	private int version;
	private String type;
	private String name;
	private int deckId;
	public int getDeckId() {
		return deckId;
	}

	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}

	private static final String tableName = "cards";
	private static long followingId = -1;
	
	public CardRDG(Long id, int version, int deckId, String name, String type) {
		this.id = id;
		this.version =version; 
		this.type = type;
		this.name = name;
		this.deckId = deckId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public static CardRDG find(long id) throws SQLException {
		CardRDG card = null;
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
		pState.setLong(1, id);
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			card = new CardRDG(
					r.getLong("id"), 
					r.getInt("version"), 
					r.getInt("deckId"),
					r.getString("username"),
					r.getString("password"));
		}
		return card;
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
			
		
		return followingId;
	}
	
	public int insert() throws SQLException {
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("INSERT INTO " + tableName +
				"(id, version, deckId, cardName, cardType) VALUES(?,?,?,?);" );
		pState.setLong(1, this.getId());
		pState.setInt(2, this.getVersion());
		pState.setString(3, this.getName());
		pState.setInt(4, this.getDeckId());
		pState.setString(5, this.getType());
		
		return pState.executeUpdate();
	}
	
	public static List<CardRDG> findAllDeck(Long deckId) throws SQLException {
		List<CardRDG> cards = new ArrayList<CardRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE deckId = ? ;");
		pState.setLong(1, deckId);
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			CardRDG card = new CardRDG(
					r.getLong("id"), 
					r.getInt("version"),
					r.getInt("deckId"),
					r.getString("cardName"),
					r.getString("cardType"));
			
			cards.add(card);
					
		}
		
		return cards;
	}
	
	public static List<CardRDG> findAll() throws SQLException {
		List<CardRDG> cards = new ArrayList<CardRDG>();
		Connection conn = DatabaseConnector.getConnection();
		PreparedStatement pState = conn.prepareStatement("SELECT * FROM " + tableName + " ;");
		ResultSet r = pState.executeQuery();
		while(r.next()) {
			CardRDG card = new CardRDG(
					r.getLong("id"), 
					r.getInt("version"),
					r.getInt("deckId"),
					r.getString("cardName"),
					r.getString("cardType"));
			
			cards.add(card);
					
		}
		
		return cards;
	}
	
}
