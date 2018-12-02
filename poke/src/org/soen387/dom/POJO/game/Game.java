package org.soen387.dom.POJO.game;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.annotation.ExternalProducer;
import org.dsrg.soenea.domain.annotation.IDFieldType;
import org.dsrg.soenea.domain.annotation.Interface;

@IDFieldType(Long.class)
@Interface(IGame.class)
@ExternalProducer(GameProxy.class)
public class Game  extends DomainObject<Long>  implements IGame{
	
	private Long challengerId;
	private Long challengeeId;
	private Long currentTurn;
	private int numberOfTurn;
	private GameStatus challengerStatus;
	private GameStatus challengeeStatus;
	private Long challengerDeck;
	private Long challengeeDeck;
	
	
	
	public Game(Long id, long version, Long challengerId, Long challengeeId, Long currentTurn, int numberOfTurn,
			GameStatus challengerStatus, GameStatus challengeeStatus, Long challengerDeck, Long challengeeDeck) {
		super(id, version);
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
		this.currentTurn = currentTurn;
		this.numberOfTurn = numberOfTurn;
		this.challengerStatus = challengerStatus;
		this.challengeeStatus = challengeeStatus;
		this.challengerDeck = challengerDeck;
		this.challengeeDeck = challengeeDeck;
	}



	protected Game(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}



	public Long getChallengerDeck() {
		return challengerDeck;
	}




	public void setChallengerDeck(Long challengerDeck) {
		this.challengerDeck = challengerDeck;
	}




	public Long getChallengeeDeck() {
		return challengeeDeck;
	}




	public void setChallengeeDeck(Long challengeeDeck) {
		this.challengeeDeck = challengeeDeck;
	}




	public Long getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Long currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int getNumberOfTurn() {
		return numberOfTurn;
	}

	public void setNumberOfTurn(int numberOfTurn) {
		this.numberOfTurn = numberOfTurn;
	}

	public GameStatus getChallengerStatus() {
		return challengerStatus;
	}

	public void setChallengerStatus(GameStatus challengerStatus) {
		this.challengerStatus = challengerStatus;
	}

	public GameStatus getChallengeeStatus() {
		return challengeeStatus;
	}

	public void setChallengeeStatus(GameStatus challengeeStatus) {
		this.challengeeStatus = challengeeStatus;
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

	
	
	

}
