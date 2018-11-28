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

	public GameStatus getChallengerStatus();

	public void setChallengerStatus(GameStatus challengerStatus);

	public GameStatus getChallengeeStatus();

	public void setChallengeeStatus(GameStatus challengeeStatus);

	public IHand getChallengerHand();

	public void setChallengerHand(IHand challengerHand);

	public IHand getChallengeeHand();

	public void setChallengeeHand(IHand challengeeHand);

	public IBench getChallengerBench();

	public void setChallengerBench(IBench challengerBench);

	public IBench getChallengeeBench();

	public void setChallengeeBench(IBench challengeeBench);

	public IDiscard getChallengerDiscard();

	public void setChallengerDiscard(IDiscard challengerDiscard);

	public IDiscard getChallengeeDiscard();

	public void setChallengeeDiscard(IDiscard challengeeDiscard);
	
	public Long getChallengerId();
	
	public void setChallengerId(Long challengerId);
	
	public Long getChallengeeId();
	
	public void setChallengeeId(Long challengeeId);

}
