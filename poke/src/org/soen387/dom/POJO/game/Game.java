package org.soen387.dom.POJO.game;

import org.dsrg.soenea.domain.DomainObject;

public class Game  extends DomainObject<Long>  implements IGame{
	
	private Long challengerId;
	private Long challengeeId;
	private Long currentTurn;
	private int numberOfTurn;
	private GameStatus challengerStatus;
	private GameStatus challengeeStatus;
	private Long challengerDeck;
	private Long challengeeDeck;
	private IHand challengerHand;
	private IHand challengeeHand;
	private IBench challengerBench;
	private IBench challengeeBench;
	private IDiscard challengerDiscard;
	private IDiscard challengeeDiscard;
	
	
	
	protected Game(Long id, long version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}

	
	public Game(Long id, long version, Long challengerId,
			Long challengeeId, Long challengerDeckId, Long challengeeDeckId) {
		super(id, version);
		this.challengerId = challengerId;
		this.challengeeId = challengeeId;
		this.challengerDeck = challengerDeckId;
		this.challengeeDeck = challengeeDeckId;
		this.currentTurn = challengerId;
		numberOfTurn = 0;
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

	public IHand getChallengerHand() {
		return challengerHand;
	}

	public void setChallengerHand(IHand challengerHand) {
		this.challengerHand = challengerHand;
	}

	public IHand getChallengeeHand() {
		return challengeeHand;
	}

	public void setChallengeeHand(IHand challengeeHand) {
		this.challengeeHand = challengeeHand;
	}

	public IBench getChallengerBench() {
		return challengerBench;
	}

	public void setChallengerBench(IBench challengerBench) {
		this.challengerBench = challengerBench;
	}

	public IBench getChallengeeBench() {
		return challengeeBench;
	}

	public void setChallengeeBench(IBench challengeeBench) {
		this.challengeeBench = challengeeBench;
	}

	public IDiscard getChallengerDiscard() {
		return challengerDiscard;
	}

	public void setChallengerDiscard(IDiscard challengerDiscard) {
		this.challengerDiscard = challengerDiscard;
	}

	public IDiscard getChallengeeDiscard() {
		return challengeeDiscard;
	}

	public void setChallengeeDiscard(IDiscard challengeeDiscard) {
		this.challengeeDiscard = challengeeDiscard;
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
