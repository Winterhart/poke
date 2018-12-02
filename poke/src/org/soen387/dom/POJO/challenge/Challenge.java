package org.soen387.dom.POJO.challenge;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;

@IDFieldType(Long.class)
@Interface(IChallenge.class)
@ExternalProducer(ChallengeProxy.class)
public class Challenge extends DomainObject<Long> implements IChallenge {

	private Long challengerId;
	private Long challengeeId;
	private int status;
	private Long deckInitializer;
	
	protected Challenge(Long id, long version) {
		super(id, version);
	}
	
	public Challenge(Long id, long version, Long challengerId, 
			Long challengeeId, Long initialDeck, int challengeStatus)  {
		super(id, version);
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
		this.status = challengeStatus;
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

	public Long getDeckInitializer() {
		return deckInitializer;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDeckInitializer(Long deckInitializer) {
		this.deckInitializer = deckInitializer;
	}
	
}
