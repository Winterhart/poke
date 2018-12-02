package org.soen387.dom.POJO.challenge;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IChallenge extends IDomainObject<Long> {
	
	public Long getChallengerId();

	public void setChallengerId(Long challengerId);

	public Long getChallengeeId();

	public void setChallengeeId(Long challengeeId);

	public Long getDeckInitializer();

	
	public int getStatus();

	public void setStatus(int status);
	public void setDeckInitializer(Long deckInitializer);

}
