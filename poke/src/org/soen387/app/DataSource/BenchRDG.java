package org.soen387.app.DataSource;

public class BenchRDG {

	private Long id;
	private int version;
	private Long userId;
	private Long gameId;
	private Long cardId;
	private static final String tableName = "benchs";
	
	
	public BenchRDG(Long id, int version, Long userId, Long gameId, Long cardId) {
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
	
	
}
