package org.soen387.app.DataSource;

public class GameRDG {
	
	private Long id;
	private int version;
	private Long challengeeId;
	private Long challengerId;

	
	private static final String tableName = "games";

	public GameRDG(Long id, int version, Long challengeeId, Long challengerId) {
		super();
		this.id = id;
		this.version = version;
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
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
	
	
	
}
