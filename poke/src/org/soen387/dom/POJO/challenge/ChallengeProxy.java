package org.soen387.dom.POJO.challenge;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;

public class ChallengeProxy extends DomainObjectProxy<Long, Challenge> implements IChallenge {

	protected ChallengeProxy(Long id) {
		super(id);
	}


	@Override
	protected Challenge getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return ChallengeInputMapper.find(getId());
		}catch(Exception ee) {
			throw new MapperException(ee.getMessage());
		}
	}
	@Override
	public Long getId() {
		return getInnerObject().getId();
	}

	@Override
	public long getVersion() {
		return getInnerObject().getVersion();
	}

	@Override
	public void setVersion(long new_version) {
		getInnerObject().setVersion(new_version);
		
	}

	@Override
	public Long getChallengerId() {
		return getInnerObject().getChallengerId();
	}

	@Override
	public void setChallengerId(Long challengerId) {
		getInnerObject().setChallengerId(challengerId);
		
	}

	@Override
	public Long getChallengeeId() {
		return getInnerObject().getChallengeeId();
	}

	@Override
	public void setChallengeeId(Long challengeeId) {
		getInnerObject().setChallengeeId(challengeeId);
		
	}

	@Override
	public ChallengeStatus getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(ChallengeStatus status) {
		getInnerObject().setStatus(status);
		
	}

	@Override
	public Long getDeckInitializer() {
		return getInnerObject().getDeckInitializer();
	}

	@Override
	public int getChallengeStatus() throws InvalidChallengeStatusException {
		return getInnerObject().getChallengeStatus();
	}

	@Override
	public void setChallengeStatus(int status) throws InvalidChallengeStatusException {
		getInnerObject().setChallengeStatus(status);
		
	}


}
