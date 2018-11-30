package org.soen387.dom.POJO.game;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.Mapper.game.GameInputMapper;

public class GameProxy extends DomainObjectProxy<Long,Game> implements IGame  {

	public GameProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Game getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return GameInputMapper.find(getId());
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
	public Long getChallengerDeck() {
		return getInnerObject().getChallengerDeck();
	}

	@Override
	public void setChallengerDeck(Long challengerDeck) {
		getInnerObject().setChallengerDeck(challengerDeck);
		
	}

	@Override
	public Long getChallengeeDeck() {
		return getInnerObject().getChallengeeDeck();
	}

	@Override
	public void setChallengeeDeck(Long challengeeDeck) {
		getInnerObject().setChallengeeDeck(challengeeDeck);
		
	}

	@Override
	public Long getCurrentTurn() {
		return getInnerObject().getCurrentTurn();
	}

	@Override
	public void setCurrentTurn(Long currentTurn) {
		getInnerObject().setCurrentTurn(currentTurn);
		
	}

	@Override
	public int getNumberOfTurn() {
		return getInnerObject().getNumberOfTurn();
	}

	@Override
	public void setNumberOfTurn(int numberOfTurn) {
		getInnerObject().setNumberOfTurn(numberOfTurn);
		
	}

	@Override
	public GameStatus getChallengerStatus() {
		return getInnerObject().getChallengerStatus();
	}

	@Override
	public void setChallengerStatus(GameStatus challengerStatus) {
		getInnerObject().setChallengerStatus(challengerStatus);
		
	}

	@Override
	public GameStatus getChallengeeStatus() {
		return getInnerObject().getChallengeeStatus();
	}

	@Override
	public void setChallengeeStatus(GameStatus challengeeStatus) {
		getInnerObject().setChallengeeStatus(challengeeStatus);
		
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



}
