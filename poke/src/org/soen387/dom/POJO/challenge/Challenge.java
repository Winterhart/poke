package org.soen387.app.Domain.POJO.challenge;

import org.dsrg.soenea.domain.DomainObject;

public class Challenge extends DomainObject<Long> implements IChallenge {

	private Long challengerId;
	private Long challengeeId;
	private ChallengeStatus status;
	private Long deckInitializer;
	
	protected Challenge(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}
	
	public Challenge(Long id, Long version, Long challengerId, 
			Long challengeeId, Long initialDeck) {
		super(id, version);
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
		this.status = ChallengeStatus.A;
		this.deckInitializer = initialDeck;
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

	public ChallengeStatus getStatus() {
		return status;
	}

	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}

	public Long getDeckInitializer() {
		return deckInitializer;
	}
	
	public int getChallengeStatus() 
			throws InvalidChallengeStatusException {
		
		int convertedToIntStatus = 0;
		switch(this.status) {
		case A:
			convertedToIntStatus = 0;
			break;
		case B:
			convertedToIntStatus = 1;
			break;
		case C:
			convertedToIntStatus = 2;
			break;
		case D:
			convertedToIntStatus = 3;
			break;
		default:
			throw new InvalidChallengeStatusException();
		}
		
		return convertedToIntStatus;
	}
	
	public void setChallengeStatus(int status) 
			throws InvalidChallengeStatusException {
		
		switch(status) {
		case 0:
			this.status = ChallengeStatus.A;
			break;
		case 1:
			this.status = ChallengeStatus.B;
			break;
		case 2:
			this.status = ChallengeStatus.C;
			break;
		case 3:
			this.status = ChallengeStatus.D;
			break;
		default:
			throw new InvalidChallengeStatusException();
		}
		
	}
	
}
