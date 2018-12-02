package org.soen387.dom.POJO.game;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IGame extends IDomainObject<Long> {
	

	public Long getChallengerDeck();

	public void setChallengerDeck(Long challengerDeck);

	public Long getChallengeeDeck();
	
	public void setChallengeeDeck(Long challengeeDeck);

	public Long getCurrentTurn();

	public void setCurrentTurn(Long currentTurn);

	public int getNumberOfTurn();

	public void setNumberOfTurn(int numberOfTurn);

	public String getChallengerStatus();

	public void setChallengerStatus(String challengerStatus);

	public String getChallengeeStatus();

	public void setChallengeeStatus(String challengeeStatus);

	
	public Long getChallengerId();
	
	public void setChallengerId(Long challengerId);
	
	public Long getChallengeeId();
	
	public void setChallengeeId(Long challengeeId);

}
