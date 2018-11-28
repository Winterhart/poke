package org.soen387.app.Domain.POJO.challenge;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IChallenge extends IDomainObject<Long> {
	
	public Long getChallengerId();

	public void setChallengerId(Long challengerId);

	public Long getChallengeeId();

	public void setChallengeeId(Long challengeeId);

	public ChallengeStatus getStatus();

	public void setStatus(ChallengeStatus status);

	public Long getDeckInitializer();
	
	public int getChallengeStatus() 
			throws InvalidChallengeStatusException;
	
	public void setChallengeStatus(int status) 
			throws InvalidChallengeStatusException;

}
