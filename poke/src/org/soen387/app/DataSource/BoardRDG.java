package org.soen387.app.DataSource;

public class BoardRDG {
	
	private Long id;
	private int version;
	private String challengeeStatus;
	private String challengerStatus;
	private static final String tableName = "boards";
	
	
	public BoardRDG(Long id, int version, String challengeeStatus, String challengerStatus) {
		super();
		this.id = id;
		this.version = version;
		this.challengeeStatus = challengeeStatus;
		this.challengerStatus = challengerStatus;
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
	
	

}
