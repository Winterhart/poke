package org.soen387.app.Domain.pojo;

public class Challenge {
	private Long id;
	private int version;
	private User challenger;
	private User challengee;
	private ChallengeStatus challengeStatus;
	
	public Challenge(Long id, int version, User challenger, User challengee, ChallengeStatus challengeStatus) {
		this.id = id;
		this.version = version;
		this.challenger = challenger;
		this.challengee = challengee;
		this.challengeStatus = challengeStatus;
	}
	
	public User getChallenger() {
		return challenger;
	}

	public void setChallenger(User challenger) {
		this.challenger = challenger;
	}

	public User getChallengee() {
		return challengee;
	}

	public void setChallengee(User challengee) {
		this.challengee = challengee;
	}

	public Challenge(Long id, int version, User challenger, User challengee,  int challengeStatusNumber) {
		this.id = id;
		this.version = version;
		this.challenger = challenger;
		this.challengee = challengee;
		this.setChallengeStatusWithNumber(challengeStatusNumber);
	}
	
	
	public void setChallengeStatusWithNumber(int number) {
		switch(number) {
		case 0:
			this.challengeStatus = ChallengeStatus.A;
			break;
		case 1:
			this.challengeStatus = ChallengeStatus.B;
			break;
		case 2:
			this.challengeStatus = ChallengeStatus.C;
			break;
		case 3:
			this.challengeStatus = ChallengeStatus.D;
		}
	}
	
	public int getChallengeStatusWithNumber() {
		switch(this.getChallengeStatus()) {
		case A:
			return 0;
		case B:
			return 1;
		case C:
			return 2;
		case D:
			return 3;
		default:
			return 404;
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

	public ChallengeStatus getChallengeStatus() {
		return challengeStatus;
	}
	public void setChallengeStatus(ChallengeStatus challengeStatus) {
		this.challengeStatus = challengeStatus;
	}
	

	
	
	
	
}
