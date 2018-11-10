package org.soen387.app.Domain.pojo;

public class Challenge {
	private Long id;
	private int version;
	private int challenger;
	private int challengee;
	private ChallengeStatus challengeStatus;
	public Challenge(Long id, int version, int challenger, int challengee, ChallengeStatus challengeStatus) {
		super();
		this.id = id;
		this.version = version;
		this.challenger = challenger;
		this.challengee = challengee;
		this.challengeStatus = challengeStatus;
	}
	
	
	public void setChallengeStatusWithNumber(int number) {
		
	}
	
	public int getChallengeStatusWithNumber() {
		switch(this.challengeStatus) {
		
		case ChallengeStatus.A:
			
		
		}
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
	public int getChallenger() {
		return challenger;
	}
	public void setChallenger(int challenger) {
		this.challenger = challenger;
	}
	public int getChallengee() {
		return challengee;
	}
	public void setChallengee(int challengee) {
		this.challengee = challengee;
	}
	public ChallengeStatus getChallengeStatus() {
		return challengeStatus;
	}
	public void setChallengeStatus(ChallengeStatus challengeStatus) {
		this.challengeStatus = challengeStatus;
	}
	

	
	
	
	
}
